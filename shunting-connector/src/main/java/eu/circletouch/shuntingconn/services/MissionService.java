package eu.circletouch.shuntingconn.services;

import eu.circletouch.commons.beans.BrowseRequest;
import eu.circletouch.commons.beans.BrowseResponse;
import eu.circletouch.shuntigconn.beans.dt.Mission;
import eu.circletouch.shuntingconn.entities.MissionEntity;
import eu.circletouch.shuntingconn.exceptions.CustomException;
import eu.circletouch.shuntingconn.mappers.MissionMapper;
import eu.circletouch.shuntingconn.repositories.MissionRepository;
import eu.circletouch.users.beans.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MissionService {
    private final MissionRepository missionRepository;
    private final MissionMapper missionMapper = MissionMapper.INSTANCE;

    public Mission create(Mission mission, User user){
        MissionEntity missionEntity = missionMapper.beanToEntity(mission);
        missionEntity.setCreatedById(user.getId());
        missionEntity.setCreatedByUser(user.getUsername());
        return missionMapper.entityToBean(missionRepository.save(missionEntity));
    }

    public Mission update(Mission mission, User user) {
        MissionEntity existingEntity = missionRepository.findById(mission.getId())
                .orElseThrow(() -> new CustomException("Mission with id " + mission.getId() + " not found! ", HttpStatus.NOT_FOUND));

        MissionEntity missionEntity = missionMapper.beanToEntity(mission);
        missionEntity.setCreatedById(existingEntity.getCreatedById());
        missionEntity.setCreatedByUser(existingEntity.getCreatedByUser());
        missionEntity.setCreatedAt(existingEntity.getCreatedAt());
        missionEntity.setUpdatedById(user.getId());
        missionEntity.setUpdatedByUser(user.getUsername());

        return missionMapper.entityToBean(missionRepository.save(missionEntity));
    }

    public Mission get(Integer id, User user){
        return missionRepository.findById(id)
                .map(missionMapper::entityToBean)
                .orElseThrow(() -> new CustomException("Mission with id " + id + " not found! ", HttpStatus.NOT_FOUND));
    }

    public BrowseResponse<Mission> browse(BrowseRequest browseRequest, User req) {
        BrowseResponse<Mission> browseResponse = new BrowseResponse<>();
        browseResponse.setResultList(missionRepository.browseList(browseRequest)
                .stream().map(MissionMapper.INSTANCE::entityToBean)
                .collect(Collectors.toList()));
        if (browseResponse.getResultList().size() > 0)
            browseResponse.setTotalCount(missionRepository.browseCount(browseRequest));
        return browseResponse;
    }
}
