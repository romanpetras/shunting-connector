package eu.circletouch.shuntingconn.controllers;

import eu.circletouch.commons.beans.BrowseRequest;
import eu.circletouch.commons.beans.BrowseResponse;
import eu.circletouch.shuntigconn.beans.dt.MissionTask;
import eu.circletouch.shuntingconn.configs.JwtTokenProvider;
import eu.circletouch.shuntingconn.services.MissionTaskService;
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
@RequestMapping("/missionTask")
@RequiredArgsConstructor
public class MissionTaskController {
    private final JwtTokenProvider jwtTokenProvider;
    private final MissionTaskService missionTaskService;

    @GetMapping("/{id}")
    public MissionTask get(@PathVariable("id") Integer missionTaskId, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return missionTaskService.get(missionTaskId, req);
    }

    @PostMapping("/")
    public MissionTask create(@RequestBody MissionTask missionTask, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return missionTaskService.create(missionTask, req);
    }

    @PostMapping("/{id}")
    public MissionTask update(@RequestBody MissionTask missionTask, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return missionTaskService.update(missionTask, req);
    }

    @PostMapping(value = "/browse")
    public BrowseResponse<MissionTask> browse(@RequestBody BrowseRequest browseRequest, @RequestHeader("Authorization") String authHeader) {
        User req = jwtTokenProvider.getUser(authHeader);
        return missionTaskService.browse(browseRequest, req);
    }
}
