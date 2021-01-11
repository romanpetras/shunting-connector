package eu.circletouch.shuntingconn.services;

import eu.circletouch.commons.beans.BrowseRequest;
import eu.circletouch.commons.beans.BrowseResponse;
import eu.circletouch.shuntigconn.beans.dt.Locomotor;
import eu.circletouch.shuntigconn.beans.dt.Mission;
import eu.circletouch.shuntigconn.beans.dt.ManeuverType;
import eu.circletouch.shuntigconn.beans.dt.MissionTask;
import eu.circletouch.shuntigconn.beans.dt.Point;
import eu.circletouch.shuntigconn.beans.dt.SimulationRequest;
import eu.circletouch.shuntigconn.beans.dt.Terminal;
import eu.circletouch.shuntigconn.beans.dt.WagonType;
import eu.circletouch.shuntingconn.entities.LocomotorEntity;
import eu.circletouch.shuntingconn.entities.MissionEntity;
import eu.circletouch.shuntingconn.entities.MissionTaskEntity;
import eu.circletouch.shuntingconn.entities.PointEntity;
import eu.circletouch.shuntingconn.entities.TerminalEntity;
import eu.circletouch.shuntingconn.entities.WagonTypeEntity;
import eu.circletouch.shuntingconn.exceptions.CustomException;
import eu.circletouch.shuntingconn.mappers.MissionMapper;
import eu.circletouch.shuntingconn.repositories.MissionRepository;
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
import java.util.Arrays;
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
class MissionServiceTest {
    @Mock
    private MissionRepository missionRepository;
    MissionMapper missionMapper = MissionMapper.INSTANCE;
    Mission missionBean;
    MissionEntity missionEntity;
    User testUser;
    BrowseRequest browseRequest;

    MissionService missionService;
    ArgumentCaptor<MissionEntity> entityArgumentCaptor;

    @BeforeEach
    public void init(){
        missionService = new MissionService(missionRepository);
        entityArgumentCaptor = ArgumentCaptor.forClass(MissionEntity.class);
        testUser = new User();
        browseRequest = new BrowseRequest();

        browseRequest.setFilter(new HashMap<>());
        browseRequest.setLimit(50);
        browseRequest.setOffset(25);
        browseRequest.setSortingList(new LinkedList<>());

        testUser.setId((long)12);
        testUser.setUsername("testUserName");

        missionBean = new Mission();
        MissionTask missionTask = new MissionTask();
        missionTask.setId(13);
        MissionTask missionTask1 = new MissionTask();
        missionTask1.setId(14);
        Locomotor locomotor = new Locomotor();
        locomotor.setId(5);
        Terminal terminalBean = new Terminal();
        terminalBean.setId(2);
        Point pointBean1 = new Point();
        pointBean1.setId(9);
        Point pointBean2 = new Point();
        pointBean2.setId(8);
        Point pointBean3 = new Point();
        pointBean3.setId(7);
        Point pointBean4 = new Point();
        pointBean3.setId(6);
        WagonType wagonTypeBean = new WagonType();
        wagonTypeBean.setId(15);
        SimulationRequest simulationRequestBean = new SimulationRequest();
        simulationRequestBean.setId(12);
        missionBean.setId(12321);
        missionBean.setStartDateTime(LocalDateTime.of(2001, 8, 2, 5, 46));
        missionBean.setLocomotor(locomotor);
        missionBean.setMissionTaskList(Arrays.asList(missionTask1, missionTask));
        missionBean.setTraceNumber("4B34U7YFU775AC3NUMB3R");
        missionBean.setLocomotorStartingPoint(pointBean1);
        missionBean.setTrainStartingPoint(pointBean2);
        missionBean.setTrainEndingPoint(pointBean3);
        missionBean.setTrainSplit(true);
        missionBean.setCutNumber(54);
        missionBean.setWagonCutEndingPoint(pointBean4);

        missionEntity = new MissionEntity();
        LocomotorEntity locomotorEntity = new LocomotorEntity();
        locomotorEntity.setId(5);
        MissionTaskEntity missionTaskEntity1 = new MissionTaskEntity();
        missionTaskEntity1.setId(13);
        MissionTaskEntity missionTaskEntity2 = new MissionTaskEntity();
        missionTaskEntity2.setId(14);
        TerminalEntity terminal = new TerminalEntity();
        terminal.setId(2);
        PointEntity point1 = new PointEntity();
        point1.setId(9);
        PointEntity point2 = new PointEntity();
        point2.setId(8);
        PointEntity point3 = new PointEntity();
        point3.setId(7);
        PointEntity point4 = new PointEntity();
        point3.setId(6);
        missionEntity.setId(12321);
        missionEntity.setStartDateTime(Timestamp.valueOf(LocalDateTime.of(2001, 8, 2, 5, 46)));
        missionEntity.setLocomotor(locomotorEntity);
        missionEntity.setMissionTaskList(Arrays.asList(missionTaskEntity2, missionTaskEntity1));
        missionEntity.setTraceNumber("4B34U7YFU775AC3NUMB3R");
        missionEntity.setLocomotorStartingPoint(point1);
        missionEntity.setTrainStartingPoint(point2);
        missionEntity.setTrainEndingPoint(point3);
        missionEntity.setTrainSplit(true);
        missionEntity.setCutNumber(54);
        missionEntity.setWagonCutEndingPoint(point4);
    }

    @Test
    public void whenCreateCalledWithBeanParameterThenRepoSaveMethodShouldBeCalledWithMappedEntity() {
        missionService.create(missionBean, testUser);

        verify(missionRepository).save(entityArgumentCaptor.capture());
        verify(missionRepository, times(1)).save(any());
        assertEquals(missionMapper.entityToBean(entityArgumentCaptor.getValue()), missionBean);
        assertEquals(entityArgumentCaptor.getValue().getCreatedById(), testUser.getId());
        assertEquals(entityArgumentCaptor.getValue().getCreatedByUser(), testUser.getUsername());
    }

    @Test
    public void whenUpdateCalledWithBeanParameterThenRepoSaveMethodShouldBeCalledWithMappedEntity() {
        when(missionRepository.findById(12321)).thenReturn(Optional.of(missionEntity));

        missionService.update(missionBean, testUser);

        verify(missionRepository).save(entityArgumentCaptor.capture());
        verify(missionRepository, times(1)).save(any());
        assertEquals(missionMapper.entityToBean(entityArgumentCaptor.getValue()), missionBean);
        assertEquals(entityArgumentCaptor.getValue().getUpdatedById(), testUser.getId());
        assertEquals(entityArgumentCaptor.getValue().getUpdatedByUser(), testUser.getUsername());
    }

    @Test
    public void whenUpdateCalledWithIdThatDoesNotExistInDBThenAnExceptionShouldBeThrown() {
        when(missionRepository.findById(12321)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> missionService.update(missionBean, testUser));
        verify(missionRepository, times(1)).findById(any());
    }

    @Test
    public void whenUpdateCalledOnEntityThenTheCreateInformationShouldBeKept() {
        missionEntity.setCreatedById(32L);
        missionEntity.setCreatedByUser("createdByUser");
        missionEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.of(2001, 8, 2, 5, 46)));
        when(missionRepository.findById(12321)).thenReturn(Optional.of(missionEntity));

        missionService.update(missionBean, testUser);

        verify(missionRepository).save(entityArgumentCaptor.capture());
        assertEquals(entityArgumentCaptor.getValue().getCreatedById(), missionEntity.getCreatedById());
        assertEquals(entityArgumentCaptor.getValue().getCreatedByUser(), missionEntity.getCreatedByUser());
        assertEquals(entityArgumentCaptor.getValue().getCreatedAt(), missionEntity.getCreatedAt());
    }

    @Test
    public void whenGetCalledWithSpecificIdThenRepoFindByIdShouldBeCalledWithSameIdOnlyOnce() {
        when(missionRepository.findById(any())).thenReturn(Optional.of(missionEntity));

        missionService.get(15, testUser);

        verify(missionRepository).findById(15);
        verify(missionRepository, times(1)).findById(any());
    }

    @Test
    public void whenGetCalledThenReturnedValueShouldBeMappedBeanOfEntityReturnedByRepoFindByIdMethod() {
        when(missionRepository.findById(any())).thenReturn(Optional.of(missionEntity));

        Mission mission = missionService.get(any(), testUser);

        assertEquals(missionBean, mission);
    }

    @Test
    public void whenGetCalledWithIdThatNotExistInDBThenThrowCustomException() {
        when(missionRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> missionService.get(any(), testUser));
    }

    @Test
    public void whenBrowseCalledBrowseRequestParameterThenTheRepoBrowseListMethodShouldBeCalledWithSameObjectOnlyOnce() {
        when(missionRepository.browseList(browseRequest)).thenReturn(new ArrayList<>());

        missionService.browse(browseRequest, testUser);

        verify(missionRepository).browseList(browseRequest);
        verify(missionRepository, times(1)).browseList(any());
    }

    @Test
    public void whenResultListContainsOneElementThenTheListInBrowseResponseShouldContainObjectsThatMatchEntitiesInResultList() {
        List<MissionEntity> resultList = Collections.singletonList(missionEntity);
        when(missionRepository.browseList(browseRequest)).thenReturn(resultList);
        when(missionRepository.browseCount(browseRequest)).thenReturn((long) resultList.size());
        List<Mission> mappedResultList = resultList.stream().map(missionMapper::entityToBean).collect(Collectors.toList());

        BrowseResponse<Mission> browseResponse = missionService.browse(browseRequest, testUser);

        assertEquals(mappedResultList, browseResponse.getResultList());
        assertEquals(1, browseResponse.getTotalCount());
    }
}