package eu.circletouch.shuntingconn.controllers;

import eu.circletouch.commons.beans.BrowseRequest;
import eu.circletouch.commons.beans.BrowseResponse;
import eu.circletouch.shuntigconn.beans.dt.SimulationRequest;
import eu.circletouch.shuntingconn.configs.JwtTokenProvider;
import eu.circletouch.shuntingconn.services.SimulationRequestService;
import eu.circletouch.users.beans.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/simulationrequest")
@RequiredArgsConstructor
public class SimulationRequestController {
    private final JwtTokenProvider jwtTokenProvider;
    private final SimulationRequestService simulationRequestService;

    @GetMapping("/{id}")
    public SimulationRequest get(@PathVariable("id") Integer simulationRequestId, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return simulationRequestService.get(simulationRequestId, req);
    }

    @PostMapping("/")
    public SimulationRequest create(@RequestBody SimulationRequest simulationRequest, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return simulationRequestService.create(simulationRequest, req);
    }

    @PostMapping("/{id}")
    public SimulationRequest update(@RequestBody SimulationRequest simulationRequest, @RequestHeader("Authorization") String authHeader){
        User req = jwtTokenProvider.getUser(authHeader);
        return simulationRequestService.update(simulationRequest, req);
    }

    @PostMapping(value = "/browse")
    public BrowseResponse<SimulationRequest> browse(@RequestBody BrowseRequest browseRequest, @RequestHeader("Authorization") String authHeader) {
        User req = jwtTokenProvider.getUser(authHeader);
        return simulationRequestService.browse(browseRequest, req);
    }
}
