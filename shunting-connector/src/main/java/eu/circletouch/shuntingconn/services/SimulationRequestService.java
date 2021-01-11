package eu.circletouch.shuntingconn.services;

import eu.circletouch.commons.beans.BrowseRequest;
import eu.circletouch.commons.beans.BrowseResponse;
import eu.circletouch.shuntigconn.beans.dt.SimulationRequest;
import eu.circletouch.shuntigconn.beans.dt.SimulationRequestStatus;
import eu.circletouch.shuntingconn.entities.SimulationRequestEntity;
import eu.circletouch.shuntingconn.exceptions.CustomException;
import eu.circletouch.shuntingconn.mappers.SimulationRequestMapper;
import eu.circletouch.shuntingconn.repositories.SimulationRequestRepository;
import eu.circletouch.users.beans.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimulationRequestService {
    private final SimulationRequestRepository simulationRequestRepository;
    private final SimulationRequestMapper simulationRequestMapper = SimulationRequestMapper.INSTANCE;

    public SimulationRequest create(SimulationRequest simulationRequest, User user){
        SimulationRequestEntity simulationRequestEntity = simulationRequestMapper.beanToEntity(simulationRequest);
        simulationRequestEntity.setCreatedById(user.getId());
        simulationRequestEntity.setCreatedByUser(user.getUsername());
        simulationRequestEntity.setUpdatedById(user.getId());
        simulationRequestEntity.setUpdatedByUser(user.getUsername());
        return simulationRequestMapper.entityToBean(simulationRequestRepository.save(simulationRequestEntity));
    }

    public SimulationRequest update(SimulationRequest simulationRequest, User user) {
        SimulationRequestEntity existingEntity = simulationRequestRepository.findById(simulationRequest.getId())
                .orElseThrow(() -> new CustomException("SimulationRequest with id " + simulationRequest.getId() + " not found! ", HttpStatus.NOT_FOUND));

        SimulationRequestEntity simulationRequestEntity = simulationRequestMapper.beanToEntity(simulationRequest);
        simulationRequestEntity.setCreatedById(existingEntity.getCreatedById());
        simulationRequestEntity.setCreatedByUser(existingEntity.getCreatedByUser());
        simulationRequestEntity.setCreatedAt(existingEntity.getCreatedAt());
        simulationRequestEntity.setUpdatedById(user.getId());
        simulationRequestEntity.setUpdatedByUser(user.getUsername());

        return simulationRequestMapper.entityToBean(simulationRequestRepository.save(simulationRequestEntity));
    }

    public SimulationRequest get(Integer id, User user){
        return simulationRequestRepository.findById(id)
                .map(simulationRequestMapper::entityToBean)
                .orElseThrow(() -> new CustomException("SimulationRequest with id " + id + " not found! ", HttpStatus.NOT_FOUND));
    }

    public BrowseResponse<SimulationRequest> browse(BrowseRequest browseRequest, User req) {
        BrowseResponse<SimulationRequest> browseResponse = new BrowseResponse<>();
        browseResponse.setResultList(simulationRequestRepository.browseList(browseRequest)
                .stream().map(SimulationRequestMapper.INSTANCE::entityToBean)
                .collect(Collectors.toList()));
        if (browseResponse.getResultList().size() > 0)
            browseResponse.setTotalCount(simulationRequestRepository.browseCount(browseRequest));
        return browseResponse;
    }

    public SimulationRequest getNextSimulationRequest(User user){
        SimulationRequestEntity simulationRequestEntity = simulationRequestRepository
                .findFirstByStatusOrderByUpdatedAt(SimulationRequestStatus.PENDING)
                .orElseThrow(() -> new CustomException("No pending SimulationRequests . . . ", HttpStatus.NO_CONTENT));
        SimulationRequest simulationRequest = simulationRequestMapper.entityToBean(simulationRequestEntity);

        simulationRequestEntity.setStatus(SimulationRequestStatus.SUBMITTED);
        simulationRequestEntity.setUpdatedById(user.getId());
        simulationRequestEntity.setUpdatedByUser(user.getUsername());
        simulationRequestRepository.save(simulationRequestEntity);

        return simulationRequest;
    }
}
