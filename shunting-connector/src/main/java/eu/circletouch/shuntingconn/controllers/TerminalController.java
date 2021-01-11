package eu.circletouch.shuntingconn.controllers;

import eu.circletouch.shuntigconn.beans.dt.Terminal;
import eu.circletouch.shuntingconn.configs.JwtTokenProvider;
import eu.circletouch.shuntingconn.services.TerminalService;
import eu.circletouch.users.beans.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/terminal")
@RequiredArgsConstructor
public class TerminalController {
    private final JwtTokenProvider jwtTokenProvider;
    private final TerminalService terminalService;

    @GetMapping("/{id}")
    public Terminal get(@PathVariable("id") Integer terminalId, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return terminalService.get(terminalId, req);
    }

    @GetMapping("/")
    public List<Terminal> getAll(@RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return terminalService.getAll(req);
    }

    @PostMapping("/")
    public Terminal create(@RequestBody Terminal terminal, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return terminalService.create(terminal, req);
    }

    @PostMapping("/{id}")
    public Terminal update(@RequestBody Terminal terminal, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return terminalService.update(terminal, req);
    }
}
