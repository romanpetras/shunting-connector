package eu.circletouch.shuntingconn.controllers;

import eu.circletouch.commons.beans.BrowseRequest;
import eu.circletouch.commons.beans.BrowseResponse;
import eu.circletouch.shuntigconn.beans.dt.Mission;
import eu.circletouch.shuntingconn.configs.JwtTokenProvider;
import eu.circletouch.shuntingconn.services.MissionService;
import eu.circletouch.users.beans.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mission")
@RequiredArgsConstructor
public class MissionController {
    private final JwtTokenProvider jwtTokenProvider;
    private final MissionService missionService;

    @GetMapping("/{id}")
    public Mission get(@PathVariable("id") Integer missionId, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return missionService.get(missionId, req);
    }

    @PostMapping("/")
    public Mission create(@RequestBody Mission mission, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return missionService.create(mission, req);
    }

    @PostMapping("/{id}")
    public Mission update(@RequestBody Mission mission, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return missionService.update(mission, req);
    }

    @PostMapping("/browse")
    public BrowseResponse<Mission> browse(@RequestBody BrowseRequest browseRequest, @RequestHeader("Authorization") String authHeader) {
        User req = jwtTokenProvider.getUser(authHeader);
        return missionService.browse(browseRequest, req);
    }
}
