package eu.circletouch.shuntingconn.services;

import eu.circletouch.commons.beans.BrowseRequest;
import eu.circletouch.commons.beans.BrowseResponse;
import eu.circletouch.shuntigconn.beans.dt.MainManeuver;
import eu.circletouch.shuntigconn.beans.dt.Mission;
import eu.circletouch.shuntigconn.beans.dt.SimulationRequest;
import eu.circletouch.shuntigconn.beans.dt.ManeuverType;
import eu.circletouch.shuntigconn.beans.dt.Point;
import eu.circletouch.shuntigconn.beans.dt.SimulationRequest;
import eu.circletouch.shuntigconn.beans.dt.SimulationRequestStatus;
import eu.circletouch.shuntigconn.beans.dt.Terminal;
import eu.circletouch.shuntigconn.beans.dt.WagonType;
import eu.circletouch.shuntingconn.entities.MainManeuverEntity;
import eu.circletouch.shuntingconn.entities.MissionEntity;
import eu.circletouch.shuntingconn.entities.SimulationRequestEntity;
import eu.circletouch.shuntingconn.entities.PointEntity;
import eu.circletouch.shuntingconn.entities.TerminalEntity;
import eu.circletouch.shuntingconn.entities.WagonTypeEntity;
import eu.circletouch.shuntingconn.exceptions.CustomException;
import eu.circletouch.shuntingconn.mappers.SimulationRequestMapper;
import eu.circletouch.shuntingconn.repositories.SimulationRequestRepository;
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
class SimulationRequestServiceTest {
    @Mock
    private SimulationRequestRepository simulationRequestRepository;
    SimulationRequestMapper simulationRequestMapper = SimulationRequestMapper.INSTANCE;
    SimulationRequest simulationRequestBean;
    SimulationRequestEntity simulationRequestEntity;
    User testUser;
    BrowseRequest browseRequest;

    SimulationRequestService simulationRequestService;
    ArgumentCaptor<SimulationRequestEntity> entityArgumentCaptor;

    @BeforeEach
    public void init(){
        simulationRequestService = new SimulationRequestService(simulationRequestRepository);
        entityArgumentCaptor = ArgumentCaptor.forClass(SimulationRequestEntity.class);
        testUser = new User();
        browseRequest = new BrowseRequest();

        browseRequest.setFilter(new HashMap<>());
        browseRequest.setLimit(50);
        browseRequest.setOffset(25);
        browseRequest.setSortingList(new LinkedList<>());

        testUser.setId((long)12);
        testUser.setUsername("testUserName");

        simulationRequestBean = new SimulationRequest();
        MainManeuver mainManeuver1 = new MainManeuver();
        mainManeuver1.setId(1542);
        MainManeuver mainManeuver2 = new MainManeuver();
        mainManeuver2.setId(2364);
        Mission mission1 = new Mission();
        mission1.setId(4580);
        Mission mission2 = new Mission();
        mission2.setId(2084);
        simulationRequestBean.setStatus(SimulationRequestStatus.PENDING);
        simulationRequestBean.setMainManeuverList(Arrays.asList(mainManeuver1, mainManeuver2));
        simulationRequestBean.setMissionList(Arrays.asList(mission1, mission2));
        simulationRequestBean.setId(12321);

        simulationRequestEntity = new SimulationRequestEntity();
        MainManeuverEntity mainManeuverEntity1 = new MainManeuverEntity();
        mainManeuverEntity1.setId(1542);
        MainManeuverEntity mainManeuverEntity2 = new MainManeuverEntity();
        mainManeuverEntity2.setId(2364);
        MissionEntity missionEntity1 = new MissionEntity();
        missionEntity1.setId(4580);
        MissionEntity missionEntity2 = new MissionEntity();
        missionEntity2.setId(2084);
        simulationRequestEntity.setStatus(SimulationRequestStatus.PENDING);
        simulationRequestEntity.setMainManeuverList(Arrays.asList(mainManeuverEntity1, mainManeuverEntity2));
        simulationRequestEntity.setMissionList(Arrays.asList(missionEntity1, missionEntity2));
        simulationRequestEntity.setId(12321);
    }

    @Test
    public void whenCreateCalledWithBeanParameterThenRepoSaveMethodShouldBeCalledWithMappedEntity() {
        simulationRequestService.create(simulationRequestBean, testUser);

        verify(simulationRequestRepository).save(entityArgumentCaptor.capture());
        verify(simulationRequestRepository, times(1)).save(any());
        assertEquals(simulationRequestMapper.entityToBean(entityArgumentCaptor.getValue()), simulationRequestBean);
        assertEquals(entityArgumentCaptor.getValue().getCreatedById(), testUser.getId());
        assertEquals(entityArgumentCaptor.getValue().getCreatedByUser(), testUser.getUsername());
    }

    @Test
    public void whenUpdateCalledWithBeanParameterThenRepoSaveMethodShouldBeCalledWithMappedEntity() {
        when(simulationRequestRepository.findById(12321)).thenReturn(Optional.of(simulationRequestEntity));

        simulationRequestService.update(simulationRequestBean, testUser);

        verify(simulationRequestRepository).save(entityArgumentCaptor.capture());
        verify(simulationRequestRepository, times(1)).save(any());
        assertEquals(simulationRequestMapper.entityToBean(entityArgumentCaptor.getValue()), simulationRequestBean);
        assertEquals(entityArgumentCaptor.getValue().getUpdatedById(), testUser.getId());
        assertEquals(entityArgumentCaptor.getValue().getUpdatedByUser(), testUser.getUsername());
    }

    @Test
    public void whenUpdateCalledWithIdThatDoesNotExistInDBThenAnExceptionShouldBeThrown() {
        when(simulationRequestRepository.findById(12321)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> simulationRequestService.update(simulationRequestBean, testUser));
        verify(simulationRequestRepository, times(1)).findById(any());
    }

    @Test
    public void whenUpdateCalledOnEntityThenTheCreateInformationShouldBeKept() {
        simulationRequestEntity.setCreatedById(32L);
        simulationRequestEntity.setCreatedByUser("createdByUser");
        simulationRequestEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.of(2001, 8, 2, 5, 46)));
        when(simulationRequestRepository.findById(12321)).thenReturn(Optional.of(simulationRequestEntity));

        simulationRequestService.update(simulationRequestBean, testUser);

        verify(simulationRequestRepository).save(entityArgumentCaptor.capture());
        assertEquals(entityArgumentCaptor.getValue().getCreatedById(), simulationRequestEntity.getCreatedById());
        assertEquals(entityArgumentCaptor.getValue().getCreatedByUser(), simulationRequestEntity.getCreatedByUser());
        assertEquals(entityArgumentCaptor.getValue().getCreatedAt(), simulationRequestEntity.getCreatedAt());
    }

    @Test
    public void whenGetCalledWithSpecificIdThenRepoFindByIdShouldBeCalledWithSameIdOnlyOnce() {
        when(simulationRequestRepository.findById(any())).thenReturn(Optional.of(simulationRequestEntity));

        simulationRequestService.get(15, testUser);

        verify(simulationRequestRepository).findById(15);
        verify(simulationRequestRepository, times(1)).findById(any());
    }

    @Test
    public void whenGetCalledThenReturnedValueShouldBeMappedBeanOfEntityReturnedByRepoFindByIdMethod() {
        when(simulationRequestRepository.findById(any())).thenReturn(Optional.of(simulationRequestEntity));

        SimulationRequest simulationRequest = simulationRequestService.get(any(), testUser);

        assertEquals(simulationRequestBean, simulationRequest);
    }

    @Test
    public void whenGetCalledWithIdThatNotExistInDBThenThrowCustomException() {
        when(simulationRequestRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> simulationRequestService.get(any(), testUser));
    }

    @Test
    public void whenBrowseCalledBrowseRequestParameterThenTheRepoBrowseListMethodShouldBeCalledWithSameObjectOnlyOnce() {
        when(simulationRequestRepository.browseList(browseRequest)).thenReturn(new ArrayList<>());

        simulationRequestService.browse(browseRequest, testUser);

        verify(simulationRequestRepository).browseList(browseRequest);
        verify(simulationRequestRepository, times(1)).browseList(any());
    }

    @Test
    public void whenResultListContainsOneElementThenTheListInBrowseResponseShouldContainObjectsThatMatchEntitiesInResultList() {
        List<SimulationRequestEntity> resultList = Collections.singletonList(simulationRequestEntity);
        when(simulationRequestRepository.browseList(browseRequest)).thenReturn(resultList);
        when(simulationRequestRepository.browseCount(browseRequest)).thenReturn((long) resultList.size());
        List<SimulationRequest> mappedResultList = resultList.stream().map(simulationRequestMapper::entityToBean).collect(Collectors.toList());

        BrowseResponse<SimulationRequest> browseResponse = simulationRequestService.browse(browseRequest, testUser);

        assertEquals(mappedResultList, browseResponse.getResultList());
        assertEquals(1, browseResponse.getTotalCount());
    }
}