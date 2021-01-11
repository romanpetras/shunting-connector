package eu.circletouch.shuntingconn.services;

import eu.circletouch.commons.beans.BrowseRequest;
import eu.circletouch.commons.beans.BrowseResponse;
import eu.circletouch.shuntigconn.beans.dt.MissionTask;
import eu.circletouch.shuntingconn.entities.MissionTaskEntity;
import eu.circletouch.shuntingconn.exceptions.CustomException;
import eu.circletouch.shuntingconn.mappers.MissionTaskMapper;
import eu.circletouch.shuntingconn.repositories.MissionTaskRepository;
import eu.circletouch.users.beans.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionTaskService {
    private final MissionTaskRepository missionTaskRepository;
    private final MissionTaskMapper missionTaskMapper = MissionTaskMapper.INSTANCE;

    public MissionTask create(MissionTask missionTask, User user){
        MissionTaskEntity missionTaskEntity = missionTaskMapper.beanToEntity(missionTask);
        missionTaskEntity.setCreatedById(user.getId());
        missionTaskEntity.setCreatedByUser(user.getUsername());
        return missionTaskMapper.entityToBean(missionTaskRepository.save(missionTaskEntity));
    }

    public MissionTask update(MissionTask missionTask, User user) {
        MissionTaskEntity existingEntity = missionTaskRepository.findById(missionTask.getId())
                .orElseThrow(() -> new CustomException("MissionTask with id " + missionTask.getId() + " not found! ", HttpStatus.NOT_FOUND));

        MissionTaskEntity missionTaskEntity = missionTaskMapper.beanToEntity(missionTask);
        missionTaskEntity.setCreatedById(existingEntity.getCreatedById());
        missionTaskEntity.setCreatedByUser(existingEntity.getCreatedByUser());
        missionTaskEntity.setCreatedAt(existingEntity.getCreatedAt());
        missionTaskEntity.setUpdatedById(user.getId());
        missionTaskEntity.setUpdatedByUser(user.getUsername());

        return missionTaskMapper.entityToBean(missionTaskRepository.save(missionTaskEntity));
    }

    public MissionTask get(Integer id, User user){
        return missionTaskRepository.findById(id)
                .map(missionTaskMapper::entityToBean)
                .orElseThrow(() -> new CustomException("MissionTask with id " + id + " not found! ", HttpStatus.NOT_FOUND));
    }

    public BrowseResponse<MissionTask> browse(BrowseRequest browseRequest, User req) {
        BrowseResponse<MissionTask> browseResponse = new BrowseResponse<>();
        browseResponse.setResultList(missionTaskRepository.browseList(browseRequest)
                .stream().map(MissionTaskMapper.INSTANCE::entityToBean)
                .collect(Collectors.toList()));
        if (browseResponse.getResultList().size() > 0)
            browseResponse.setTotalCount(missionTaskRepository.browseCount(browseRequest));
        return browseResponse;
    }
}
