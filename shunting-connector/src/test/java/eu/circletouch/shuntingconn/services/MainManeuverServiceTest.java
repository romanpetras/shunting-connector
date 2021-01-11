package eu.circletouch.shuntingconn.services;

import eu.circletouch.commons.beans.BrowseRequest;
import eu.circletouch.commons.beans.BrowseResponse;
import eu.circletouch.shuntigconn.beans.dt.MainManeuver;
import eu.circletouch.shuntigconn.beans.dt.ManeuverType;
import eu.circletouch.shuntigconn.beans.dt.Point;
import eu.circletouch.shuntigconn.beans.dt.SimulationRequest;
import eu.circletouch.shuntigconn.beans.dt.Terminal;
import eu.circletouch.shuntigconn.beans.dt.WagonType;
import eu.circletouch.shuntingconn.entities.MainManeuverEntity;
import eu.circletouch.shuntingconn.entities.PointEntity;
import eu.circletouch.shuntingconn.entities.TerminalEntity;
import eu.circletouch.shuntingconn.entities.WagonTypeEntity;
import eu.circletouch.shuntingconn.exceptions.CustomException;
import eu.circletouch.shuntingconn.mappers.MainManeuverMapper;
import eu.circletouch.shuntingconn.repositories.MainManeuverRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MainManeuverServiceTest {
    @Mock
    private MainManeuverRepository mainManeuverRepository;
    MainManeuverMapper mainManeuverMapper = MainManeuverMapper.INSTANCE;
    MainManeuver mainManeuverBean;
    MainManeuverEntity mainManeuverEntity;
    User testUser;
    BrowseRequest browseRequest;

    MainManeuverService mainManeuverService;
    ArgumentCaptor<MainManeuverEntity> entityArgumentCaptor;

    @BeforeEach
    public void init(){
        mainManeuverService = new MainManeuverService(mainManeuverRepository);
        entityArgumentCaptor = ArgumentCaptor.forClass(MainManeuverEntity.class);
        testUser = new User();
        browseRequest = new BrowseRequest();

        browseRequest.setFilter(new HashMap<>());
        browseRequest.setLimit(50);
        browseRequest.setOffset(25);
        browseRequest.setSortingList(new LinkedList<>());

        testUser.setId((long)12);
        testUser.setUsername("testUserName");

        mainManeuverBean = new MainManeuver();
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
        mainManeuverBean.setId(12321);
        mainManeuverBean.setManeuverType(ManeuverType.ARRIVAL);
        mainManeuverBean.setTraceNumber("4B34U7YFU775AC3NUMB3R");
        mainManeuverBean.setTerminal(terminalBean);
        mainManeuverBean.setEta(LocalDateTime.of(2001, 8, 2, 5, 46));
        mainManeuverBean.setEtp(LocalDateTime.of(2021, 8, 5, 2, 34));
        mainManeuverBean.setArrivalPoint(pointBean1);
        mainManeuverBean.setManeuverParkPoint(pointBean2);
        mainManeuverBean.setRegressionPoint(pointBean3);
        mainManeuverBean.setLocomotorEndingPoint(pointBean4);
        mainManeuverBean.setWagonsNumber(18);
        mainManeuverBean.setWagonsType(wagonTypeBean);

        mainManeuverEntity = new MainManeuverEntity();
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
        WagonTypeEntity wagonType = new WagonTypeEntity();
        wagonType.setId(15);
        SimulationRequest simulationRequest = new SimulationRequest();
        simulationRequest.setId(12);
        mainManeuverEntity.setId(12321);
        mainManeuverEntity.setManeuverType(ManeuverType.ARRIVAL);
        mainManeuverEntity.setTraceNumber("4B34U7YFU775AC3NUMB3R");
        mainManeuverEntity.setTerminal(terminal);
        mainManeuverEntity.setEta(Timestamp.valueOf(LocalDateTime.of(2001, 8, 2, 5, 46)));
        mainManeuverEntity.setEtp(Timestamp.valueOf(LocalDateTime.of(2021, 8, 5, 2, 34)));
        mainManeuverEntity.setArrivalPoint(point1);
        mainManeuverEntity.setManeuverParkPoint(point2);
        mainManeuverEntity.setRegressionPoint(point3);
        mainManeuverEntity.setLocomotorEndingPoint(point4);
        mainManeuverEntity.setWagonsNumber(18);
        mainManeuverEntity.setWagonsType(wagonType);
    }

    @Test
    public void whenCreateCalledWithBeanParameterThenRepoSaveMethodShouldBeCalledWithMappedEntity() {
        mainManeuverService.create(mainManeuverBean, testUser);

        verify(mainManeuverRepository).save(entityArgumentCaptor.capture());
        verify(mainManeuverRepository, times(1)).save(any());
        assertEquals(mainManeuverMapper.entityToBean(entityArgumentCaptor.getValue()), mainManeuverBean);
        assertEquals(entityArgumentCaptor.getValue().getCreatedById(), testUser.getId());
        assertEquals(entityArgumentCaptor.getValue().getCreatedByUser(), testUser.getUsername());
    }

    @Test
    public void whenUpdateCalledWithBeanParameterThenRepoSaveMethodShouldBeCalledWithMappedEntity() {
        when(mainManeuverRepository.findById(12321)).thenReturn(Optional.of(mainManeuverEntity));

        mainManeuverService.update(mainManeuverBean, testUser);

        verify(mainManeuverRepository).save(entityArgumentCaptor.capture());
        verify(mainManeuverRepository, times(1)).save(any());
        assertEquals(mainManeuverMapper.entityToBean(entityArgumentCaptor.getValue()), mainManeuverBean);
        assertEquals(entityArgumentCaptor.getValue().getUpdatedById(), testUser.getId());
        assertEquals(entityArgumentCaptor.getValue().getUpdatedByUser(), testUser.getUsername());
    }

    @Test
    public void whenUpdateCalledWithIdThatDoesNotExistInDBThenAnExceptionShouldBeThrown() {
        when(mainManeuverRepository.findById(12321)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> mainManeuverService.update(mainManeuverBean, testUser));
        verify(mainManeuverRepository, times(1)).findById(any());
    }

    @Test
    public void whenUpdateCalledOnEntityThenTheCreateInformationShouldBeKept() {
        mainManeuverEntity.setCreatedById(32L);
        mainManeuverEntity.setCreatedByUser("createdByUser");
        mainManeuverEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.of(2001, 8, 2, 5, 46)));
        when(mainManeuverRepository.findById(12321)).thenReturn(Optional.of(mainManeuverEntity));

        mainManeuverService.update(mainManeuverBean, testUser);

        verify(mainManeuverRepository).save(entityArgumentCaptor.capture());
        assertEquals(entityArgumentCaptor.getValue().getCreatedById(), mainManeuverEntity.getCreatedById());
        assertEquals(entityArgumentCaptor.getValue().getCreatedByUser(), mainManeuverEntity.getCreatedByUser());
        assertEquals(entityArgumentCaptor.getValue().getCreatedAt(), mainManeuverEntity.getCreatedAt());
    }

    @Test
    public void whenGetCalledWithSpecificIdThenRepoFindByIdShouldBeCalledWithSameIdOnlyOnce() {
        when(mainManeuverRepository.findById(any())).thenReturn(Optional.of(mainManeuverEntity));

        mainManeuverService.get(15, testUser);

        verify(mainManeuverRepository).findById(15);
        verify(mainManeuverRepository, times(1)).findById(any());
    }

    @Test
    public void whenGetCalledThenReturnedValueShouldBeMappedBeanOfEntityReturnedByRepoFindByIdMethod() {
        when(mainManeuverRepository.findById(any())).thenReturn(Optional.of(mainManeuverEntity));

        MainManeuver mainManeuver = mainManeuverService.get(any(), testUser);

        assertEquals(mainManeuverBean, mainManeuver);
    }

    @Test
    public void whenGetCalledWithIdThatNotExistInDBThenThrowCustomException() {
        when(mainManeuverRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> mainManeuverService.get(any(), testUser));
    }

    @Test
    public void whenBrowseCalledBrowseRequestParameterThenTheRepoBrowseListMethodShouldBeCalledWithSameObjectOnlyOnce() {
        when(mainManeuverRepository.browseList(browseRequest)).thenReturn(new ArrayList<>());

        mainManeuverService.browse(browseRequest, testUser);

        verify(mainManeuverRepository).browseList(browseRequest);
        verify(mainManeuverRepository, times(1)).browseList(any());
    }

    @Test
    public void whenResultListContainsOneElementThenTheListInBrowseResponseShouldContainObjectsThatMatchEntitiesInResultList() {
        List<MainManeuverEntity> resultList = Collections.singletonList(mainManeuverEntity);
        when(mainManeuverRepository.browseList(browseRequest)).thenReturn(resultList);
        when(mainManeuverRepository.browseCount(browseRequest)).thenReturn((long) resultList.size());
        List<MainManeuver> mappedResultList = resultList.stream().map(mainManeuverMapper::entityToBean).collect(Collectors.toList());

        BrowseResponse<MainManeuver> browseResponse = mainManeuverService.browse(browseRequest, testUser);

        assertEquals(mappedResultList, browseResponse.getResultList());
        assertEquals(1, browseResponse.getTotalCount());
    }
}