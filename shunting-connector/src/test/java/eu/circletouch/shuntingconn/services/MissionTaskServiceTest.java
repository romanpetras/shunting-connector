package eu.circletouch.shuntingconn.services;

import eu.circletouch.commons.beans.BrowseRequest;
import eu.circletouch.commons.beans.BrowseResponse;
import eu.circletouch.shuntigconn.beans.dt.Direction;
import eu.circletouch.shuntigconn.beans.dt.MissionTask;
import eu.circletouch.shuntigconn.beans.dt.ManeuverType;
import eu.circletouch.shuntigconn.beans.dt.Point;
import eu.circletouch.shuntigconn.beans.dt.SimulationRequest;
import eu.circletouch.shuntigconn.beans.dt.SplitPosition;
import eu.circletouch.shuntigconn.beans.dt.Terminal;
import eu.circletouch.shuntigconn.beans.dt.WagonType;
import eu.circletouch.shuntingconn.entities.MainManeuverEntity;
import eu.circletouch.shuntingconn.entities.MissionTaskEntity;
import eu.circletouch.shuntingconn.entities.PointEntity;
import eu.circletouch.shuntingconn.entities.TerminalEntity;
import eu.circletouch.shuntingconn.entities.WagonTypeEntity;
import eu.circletouch.shuntingconn.exceptions.CustomException;
import eu.circletouch.shuntingconn.mappers.MissionTaskMapper;
import eu.circletouch.shuntingconn.repositories.MissionTaskRepository;
import eu.circletouch.users.beans.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MissionTaskServiceTest {
    @Mock
    private MissionTaskRepository missionTaskRepository;
    MissionTaskMapper missionTaskMapper = MissionTaskMapper.INSTANCE;
    MissionTask missionTaskBean;
    MissionTaskEntity missionTaskEntity;
    User testUser;
    BrowseRequest browseRequest;

    MissionTaskService missionTaskService;
    ArgumentCaptor<MissionTaskEntity> entityArgumentCaptor;

    @BeforeEach
    public void init(){
        missionTaskService = new MissionTaskService(missionTaskRepository);
        entityArgumentCaptor = ArgumentCaptor.forClass(MissionTaskEntity.class);
        testUser = new User();
        browseRequest = new BrowseRequest();

        browseRequest.setFilter(new HashMap<>());
        browseRequest.setLimit(50);
        browseRequest.setOffset(25);
        browseRequest.setSortingList(new LinkedList<>());

        testUser.setId((long)12);
        testUser.setUsername("testUserName");

        missionTaskBean = new MissionTask();
        Point pointBean1 = new Point();
        pointBean1.setId(9);
        Point pointBean2 = new Point();
        pointBean2.setId(8);
        missionTaskBean.setId(5);
        missionTaskBean.setSequence(112358);
        missionTaskBean.setMainManeuverId(2);
        missionTaskBean.setTrainPart(457);
        missionTaskBean.setDeparturePoint(pointBean1);
        missionTaskBean.setArrivalPoint(pointBean2);
        missionTaskBean.setDirection(Direction.LEFT_TO_RIGHT);
        missionTaskBean.setCouple(true);
        missionTaskBean.setDecouple(false);
        missionTaskBean.setSplit(true);
        missionTaskBean.setSplitPart(1254);
        missionTaskBean.setSplitPosition(SplitPosition.HEAD);
        missionTaskBean.setSplitNumber(6589);

        missionTaskEntity = new MissionTaskEntity();
        PointEntity point1 = new PointEntity();
        point1.setId(9);
        PointEntity point2 = new PointEntity();
        point2.setId(8);
        MainManeuverEntity mainManeuverEntity = new MainManeuverEntity();
        mainManeuverEntity.setId(2);
        missionTaskEntity.setId(5);
        missionTaskEntity.setSequence(112358);
        missionTaskEntity.setMainManeuver(mainManeuverEntity);
        missionTaskEntity.setTrainPart(457);
        missionTaskEntity.setDeparturePoint(point1);
        missionTaskEntity.setArrivalPoint(point2);
        missionTaskEntity.setDirection(Direction.LEFT_TO_RIGHT);
        missionTaskEntity.setCouple(true);
        missionTaskEntity.setDecouple(false);
        missionTaskEntity.setSplit(true);
        missionTaskEntity.setSplitPart(1254);
        missionTaskEntity.setSplitPosition(SplitPosition.HEAD);
        missionTaskEntity.setSplitNumber(6589);
    }

    @Test
    public void whenCreateCalledWithBeanParameterThenRepoSaveMethodShouldBeCalledWithMappedEntity() {
        missionTaskService.create(missionTaskBean, testUser);

        verify(missionTaskRepository).save(entityArgumentCaptor.capture());
        verify(missionTaskRepository, times(1)).save(any());
        assertEquals(missionTaskMapper.entityToBean(entityArgumentCaptor.getValue()), missionTaskBean);
        assertEquals(entityArgumentCaptor.getValue().getCreatedById(), testUser.getId());
        assertEquals(entityArgumentCaptor.getValue().getCreatedByUser(), testUser.getUsername());
    }

    @Test
    public void whenUpdateCalledWithBeanParameterThenRepoSaveMethodShouldBeCalledWithMappedEntity() {
        when(missionTaskRepository.findById(5)).thenReturn(Optional.of(missionTaskEntity));

        missionTaskService.update(missionTaskBean, testUser);

        verify(missionTaskRepository).save(entityArgumentCaptor.capture());
        verify(missionTaskRepository, times(1)).save(any());
        assertEquals(missionTaskMapper.entityToBean(entityArgumentCaptor.getValue()), missionTaskBean);
        assertEquals(entityArgumentCaptor.getValue().getUpdatedById(), testUser.getId());
        assertEquals(entityArgumentCaptor.getValue().getUpdatedByUser(), testUser.getUsername());
    }

    @Test
    public void whenUpdateCalledWithIdThatDoesNotExistInDBThenAnExceptionShouldBeThrown() {
        when(missionTaskRepository.findById(5)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> missionTaskService.update(missionTaskBean, testUser));
        verify(missionTaskRepository, times(1)).findById(any());
    }

    @Test
    public void whenUpdateCalledOnEntityThenTheCreateInformationShouldBeKept() {
        missionTaskEntity.setCreatedById(32L);
        missionTaskEntity.setCreatedByUser("createdByUser");
        missionTaskEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.of(2001, 8, 2, 5, 46)));
        when(missionTaskRepository.findById(5)).thenReturn(Optional.of(missionTaskEntity));

        missionTaskService.update(missionTaskBean, testUser);

        verify(missionTaskRepository).save(entityArgumentCaptor.capture());
        assertEquals(entityArgumentCaptor.getValue().getCreatedById(), missionTaskEntity.getCreatedById());
        assertEquals(entityArgumentCaptor.getValue().getCreatedByUser(), missionTaskEntity.getCreatedByUser());
        assertEquals(entityArgumentCaptor.getValue().getCreatedAt(), missionTaskEntity.getCreatedAt());
    }

    @Test
    public void whenGetCalledWithSpecificIdThenRepoFindByIdShouldBeCalledWithSameIdOnlyOnce() {
        when(missionTaskRepository.findById(any())).thenReturn(Optional.of(missionTaskEntity));

        missionTaskService.get(15, testUser);

        verify(missionTaskRepository).findById(15);
        verify(missionTaskRepository, times(1)).findById(any());
    }

    @Test
    public void whenGetCalledThenReturnedValueShouldBeMappedBeanOfEntityReturnedByRepoFindByIdMethod() {
        when(missionTaskRepository.findById(any())).thenReturn(Optional.of(missionTaskEntity));

        MissionTask missionTask = missionTaskService.get(any(), testUser);

        assertEquals(missionTaskBean, missionTask);
    }

    @Test
    public void whenGetCalledWithIdThatNotExistInDBThenThrowCustomException() {
        when(missionTaskRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> missionTaskService.get(any(), testUser));
    }

    @Test
    public void whenBrowseCalledBrowseRequestParameterThenTheRepoBrowseListMethodShouldBeCalledWithSameObjectOnlyOnce() {
        when(missionTaskRepository.browseList(browseRequest)).thenReturn(new ArrayList<>());

        missionTaskService.browse(browseRequest, testUser);

        verify(missionTaskRepository).browseList(browseRequest);
        verify(missionTaskRepository, times(1)).browseList(any());
    }

    @Test
    public void whenResultListContainsOneElementThenTheListInBrowseResponseShouldContainObjectsThatMatchEntitiesInResultList() {
        List<MissionTaskEntity> resultList = Collections.singletonList(missionTaskEntity);
        when(missionTaskRepository.browseList(browseRequest)).thenReturn(resultList);
        when(missionTaskRepository.browseCount(browseRequest)).thenReturn((long) resultList.size());
        List<MissionTask> mappedResultList = resultList.stream().map(missionTaskMapper::entityToBean).collect(Collectors.toList());

        BrowseResponse<MissionTask> browseResponse = missionTaskService.browse(browseRequest, testUser);

        assertEquals(mappedResultList, browseResponse.getResultList());
        assertEquals(1, browseResponse.getTotalCount());
    }
}