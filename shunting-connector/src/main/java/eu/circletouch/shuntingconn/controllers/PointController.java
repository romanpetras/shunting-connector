package eu.circletouch.shuntingconn.controllers;

import eu.circletouch.shuntigconn.beans.dt.Point;
import eu.circletouch.shuntigconn.beans.dt.Terminal;
import eu.circletouch.shuntingconn.configs.JwtTokenProvider;
import eu.circletouch.shuntingconn.services.PointService;
import eu.circletouch.users.beans.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/point")
@RequiredArgsConstructor
public class PointController {
    private final JwtTokenProvider jwtTokenProvider;
    private final PointService pointService;

    @GetMapping("/{id}")
    public Point get(@PathVariable("id") Integer pointId, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return pointService.get(pointId, req);
    }

    @PostMapping("/")
    public Point create(@RequestBody Point point, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return pointService.create(point, req);
    }

    @PostMapping("/{id}")
    public Point update(@RequestBody Point point, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return pointService.update(point, req);
    }

    @GetMapping("/")
    public List<Point> getAll(@RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return pointService.getAll(req);
    }

    @GetMapping(value = "/search")
    public List<Point> search(@RequestParam(name = "hint", required = false) String hint, @RequestHeader("Authorization") String authHeader) {
        User req = jwtTokenProvider.getUser(authHeader);
        return pointService.search(hint, req);
    }
}
