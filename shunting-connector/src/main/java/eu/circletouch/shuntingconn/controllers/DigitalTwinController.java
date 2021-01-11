package eu.circletouch.shuntingconn.controllers;

import eu.circletouch.shuntigconn.beans.dt.SimulationRequest;
import eu.circletouch.shuntingconn.configs.JwtTokenProvider;
import eu.circletouch.shuntingconn.services.SimulationRequestService;
import eu.circletouch.users.beans.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/dt")
@RequiredArgsConstructor
public class DigitalTwinController {

    private final SimulationRequestService simulationRequestService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/nextrequest")
    public SimulationRequest getNextSimulationRequest(@RequestHeader("Authorization") String authHeader) {
        User req = jwtTokenProvider.getUser(authHeader);
        return simulationRequestService.getNextSimulationRequest(req);
    }

}
