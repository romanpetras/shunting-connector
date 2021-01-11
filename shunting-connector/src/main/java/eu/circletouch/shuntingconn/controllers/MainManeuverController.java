package eu.circletouch.shuntingconn.controllers;

import eu.circletouch.commons.beans.BrowseRequest;
import eu.circletouch.commons.beans.BrowseResponse;
import eu.circletouch.shuntigconn.beans.dt.MainManeuver;
import eu.circletouch.shuntingconn.configs.JwtTokenProvider;
import eu.circletouch.shuntingconn.services.MainManeuverService;
import eu.circletouch.users.beans.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/mainManeuver")
@RequiredArgsConstructor
public class MainManeuverController {
    private final JwtTokenProvider jwtTokenProvider;
    private final MainManeuverService mainManeuverService;

    @GetMapping("/{id}")
    public MainManeuver get(@PathVariable("id") Integer mainManeuverId, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return mainManeuverService.get(mainManeuverId, req);
    }

    @PostMapping("/")
    public MainManeuver create(@RequestBody MainManeuver mainManeuver, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return mainManeuverService.create(mainManeuver, req);
    }

    @PostMapping("/{id}")
    public MainManeuver update(@RequestBody MainManeuver mainManeuver, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return mainManeuverService.update(mainManeuver, req);
    }

    @PostMapping(value = "/browse")
    public BrowseResponse<MainManeuver> browse(@RequestBody BrowseRequest browseRequest, @RequestHeader("Authorization") String authHeader) {
        User req = jwtTokenProvider.getUser(authHeader);
        return mainManeuverService.browse(browseRequest, req);
    }
}
