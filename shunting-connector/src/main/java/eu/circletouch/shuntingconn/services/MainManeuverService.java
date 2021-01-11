package eu.circletouch.shuntingconn.services;

import eu.circletouch.commons.beans.BrowseRequest;
import eu.circletouch.commons.beans.BrowseResponse;
import eu.circletouch.shuntigconn.beans.dt.MainManeuver;
import eu.circletouch.shuntingconn.entities.MainManeuverEntity;
import eu.circletouch.shuntingconn.exceptions.CustomException;
import eu.circletouch.shuntingconn.mappers.MainManeuverMapper;
import eu.circletouch.shuntingconn.repositories.MainManeuverRepository;
import eu.circletouch.users.beans.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainManeuverService {
    private final MainManeuverRepository mainManeuverRepository;
    private final MainManeuverMapper mainManeuverMapper = MainManeuverMapper.INSTANCE;

    public MainManeuver create(MainManeuver mainManeuver, User user){
        MainManeuverEntity mainManeuverEntity = mainManeuverMapper.beanToEntity(mainManeuver);
        mainManeuverEntity.setCreatedById(user.getId());
        mainManeuverEntity.setCreatedByUser(user.getUsername());
        return mainManeuverMapper.entityToBean(mainManeuverRepository.save(mainManeuverEntity));
    }

    public MainManeuver update(MainManeuver mainManeuver, User user) {
        MainManeuverEntity existingEntity = mainManeuverRepository.findById(mainManeuver.getId())
                .orElseThrow(() -> new CustomException("MainManeuver with id " + mainManeuver.getId() + " not found! ", HttpStatus.NOT_FOUND));

        MainManeuverEntity mainManeuverEntity = mainManeuverMapper.beanToEntity(mainManeuver);
        mainManeuverEntity.setCreatedById(existingEntity.getCreatedById());
        mainManeuverEntity.setCreatedByUser(existingEntity.getCreatedByUser());
        mainManeuverEntity.setCreatedAt(existingEntity.getCreatedAt());
        mainManeuverEntity.setUpdatedById(user.getId());
        mainManeuverEntity.setUpdatedByUser(user.getUsername());

        return mainManeuverMapper.entityToBean(mainManeuverRepository.save(mainManeuverEntity));
    }

    public MainManeuver get(Integer id, User user){
        return mainManeuverRepository.findById(id)
                .map(mainManeuverMapper::entityToBean)
                .orElseThrow(() -> new CustomException("MainManeuver with id " + id + " not found! ", HttpStatus.NOT_FOUND));
    }

    public BrowseResponse<MainManeuver> browse(BrowseRequest browseRequest, User req) {
        BrowseResponse<MainManeuver> browseResponse = new BrowseResponse<>();
        browseResponse.setResultList(mainManeuverRepository.browseList(browseRequest)
                .stream().map(MainManeuverMapper.INSTANCE::entityToBean)
                .collect(Collectors.toList()));
        if (browseResponse.getResultList().size() > 0)
            browseResponse.setTotalCount(mainManeuverRepository.browseCount(browseRequest));
        return browseResponse;
    }
}
