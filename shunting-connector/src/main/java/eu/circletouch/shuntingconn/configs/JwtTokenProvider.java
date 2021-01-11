package eu.circletouch.shuntingconn.configs;

import eu.circletouch.shuntingconn.exceptions.CustomException;
import eu.circletouch.users.beans.Company;
import eu.circletouch.users.beans.CompanyRole;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;

    public JwtTokenProvider() {}

    public Authentication getAuthentication(String token) {
        String username = getUsername(token);

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("PROFILE_");

        UserDetails userDetails = User
                .withUsername(username)
                .password("")
                .authorities(grantedAuthorities)
                .disabled(false)
                .build();

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(jwtSecret.getBytes()))
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(Base64.getEncoder().encodeToString(jwtSecret.getBytes()))
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException("Expired or invalid JWT token", HttpStatus.UNAUTHORIZED);
        }
    }

    public eu.circletouch.users.beans.User getUser(String authHeader) {
        Map<String, Object> userMap =  Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(jwtSecret.getBytes()))
                .parseClaimsJws(authHeader.substring(7))
                .getBody()
                .get("user", Map.class);

        eu.circletouch.users.beans.User user = new eu.circletouch.users.beans.User();
        user.setId(Long.valueOf((Integer) userMap.get("id")));
        user.setUsername((String) userMap.get("username"));
        user.setFirstName((String) userMap.get("firstName"));
        user.setLastName((String) userMap.get("lastName"));
        user.setEmail((String) userMap.get("email"));

        Map<String, Object> companyMap = (Map<String, Object>) userMap.get("company");
        Company company = new Company();
//        company.setId(Long.valueOf((Integer) companyMap.get("id")));
//        company.setCode((String) companyMap.get("code"));
//        company.setVatNumber((String) companyMap.get("vatNumber"));
//        company.setName((String) companyMap.get("name"));

//        List<Map<String, Object>> rolesList = (List<Map<String, Object>>) companyMap.get("companyRoles");
//        for (Map<String, Object> roleMap: rolesList) {
//            CompanyRole companyRole = new CompanyRole();
//            //companyRole.setId(Long.valueOf((Integer) roleMap.get("id")));
//            companyRole.setDescription((String) roleMap.get("description"));
//            company.getCompanyRoles().add(companyRole);
//        }
//        user.setCompany(company);

        return user;
    }
}
