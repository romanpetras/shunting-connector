package eu.circletouch.shuntingconn.controllers;

import eu.circletouch.shuntigconn.beans.dt.Locomotor;
import eu.circletouch.shuntingconn.configs.JwtTokenProvider;
import eu.circletouch.shuntingconn.services.LocomotorService;
import eu.circletouch.users.beans.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/locomotor")
@RequiredArgsConstructor
public class LocomotorController {
    private final JwtTokenProvider jwtTokenProvider;
    private final LocomotorService locomotorService;

    @GetMapping("/{id}")
    public Locomotor get(@PathVariable("id") Integer locomotorId, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return locomotorService.get(locomotorId, req);
    }

    @PostMapping("/")
    public Locomotor create(@RequestBody Locomotor locomotor, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return locomotorService.create(locomotor, req);
    }

    @PostMapping("/{id}")
    public Locomotor update(@RequestBody Locomotor locomotor, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return locomotorService.update(locomotor, req);
    }

    @GetMapping(value = "/search")
    public List<Locomotor> search(@RequestParam(name = "hint", required = false) String hint, @RequestHeader("Authorization") String authHeader) {
        User req = jwtTokenProvider.getUser(authHeader);
        return locomotorService.search(hint, req);
    }
}
