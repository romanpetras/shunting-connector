package eu.circletouch.shuntingconn.controllers;

import eu.circletouch.shuntigconn.beans.dt.WagonType;
import eu.circletouch.shuntingconn.configs.JwtTokenProvider;
import eu.circletouch.shuntingconn.services.WagonTypeService;
import eu.circletouch.users.beans.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/wagonType")
@RequiredArgsConstructor
public class WagonTypeController {
    private final JwtTokenProvider jwtTokenProvider;
    private final WagonTypeService wagonTypeService;

    @GetMapping("/{id}")
    public WagonType get(@PathVariable("id") Integer wagonTypeId, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return wagonTypeService.get(wagonTypeId, req);
    }

    @GetMapping("/")
    public List<WagonType> getAll(@RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return wagonTypeService.getAll(req);
    }

    @PostMapping("/")
    public WagonType create(@RequestBody WagonType wagonType, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return wagonTypeService.create(wagonType, req);
    }

    @PostMapping("/{id}")
    public WagonType update(@RequestBody WagonType wagonType, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return wagonTypeService.update(wagonType, req);
    }
}
