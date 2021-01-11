package eu.circletouch.shuntingconn.services;

import eu.circletouch.shuntigconn.beans.dt.Point;
import eu.circletouch.shuntingconn.entities.PointEntity;
import eu.circletouch.shuntingconn.exceptions.CustomException;
import eu.circletouch.shuntingconn.mappers.PointMapper;
import eu.circletouch.shuntingconn.repositories.PointRepository;
import eu.circletouch.users.beans.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PointServiceTest {
    @Mock
    private PointRepository pointRepository;
    PointMapper pointMapper = PointMapper.INSTANCE;
    Point pointBean;
    PointEntity pointEntity;
    User testUser;

    PointService pointService;
    ArgumentCaptor<PointEntity> entityArgumentCaptor;

    @BeforeEach
    public void init(){
        pointService = new PointService(pointRepository);
        entityArgumentCaptor = ArgumentCaptor.forClass(PointEntity.class);
        testUser = new User();

        testUser.setId((long)12);
        testUser.setUsername("testUserName");

        pointBean = new Point();
        pointBean.setCode("S0M3L0N4C0D3H3R3");
        pointBean.setDescription("This description describes a describable point");
        pointBean.setId(12321);
        pointBean.setPortEntrance(true);
        pointBean.setLocomotorEnd(false);
        pointBean.setRegression(true);
        pointBean.setInManeuverPark(false);
        pointBean.setStop(true);

        pointEntity = new PointEntity();
        pointEntity.setCode("S0M3L0N4C0D3H3R3");
        pointEntity.setDescription("This description describes a describable point");
        pointEntity.setId(12321);
        pointEntity.setPortEntrance(true);
        pointEntity.setLocomotorEnd(false);
        pointEntity.setRegression(true);
        pointEntity.setInManeuverPark(false);
        pointEntity.setStop(true);
    }

    @Test
    public void whenCreateCalledWithBeanParameterThenRepoSaveMethodShouldBeCalledWithMappedEntity() {
        pointService.create(pointBean, testUser);

        verify(pointRepository).save(entityArgumentCaptor.capture());
        verify(pointRepository, times(1)).save(any());
        assertEquals(pointMapper.entityToBean(entityArgumentCaptor.getValue()), pointBean);
        assertEquals(entityArgumentCaptor.getValue().getCreatedById(), testUser.getId());
        assertEquals(entityArgumentCaptor.getValue().getCreatedByUser(), testUser.getUsername());
    }

    @Test
    public void whenUpdateCalledWithBeanParameterThenRepoSaveMethodShouldBeCalledWithMappedEntity() {
        when(pointRepository.findById(12321)).thenReturn(Optional.of(pointEntity));

        pointService.update(pointBean, testUser);

        verify(pointRepository).save(entityArgumentCaptor.capture());
        verify(pointRepository, times(1)).save(any());
        assertEquals(pointMapper.entityToBean(entityArgumentCaptor.getValue()), pointBean);
        assertEquals(entityArgumentCaptor.getValue().getUpdatedById(), testUser.getId());
        assertEquals(entityArgumentCaptor.getValue().getUpdatedByUser(), testUser.getUsername());
    }

    @Test
    public void whenUpdateCalledWithIdThatDoesNotExistInDBThenAnExceptionShouldBeThrown() {
        when(pointRepository.findById(12321)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> pointService.update(pointBean, testUser));
        verify(pointRepository, times(1)).findById(any());
    }

    @Test
    public void whenUpdateCalledOnEntityThenTheCreateInformationShouldBeKept() {
        pointEntity.setCreatedById(32L);
        pointEntity.setCreatedByUser("createdByUser");
        pointEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.of(2001, 8, 2, 5, 46)));
        when(pointRepository.findById(12321)).thenReturn(Optional.of(pointEntity));

        pointService.update(pointBean, testUser);

        verify(pointRepository).save(entityArgumentCaptor.capture());
        assertEquals(entityArgumentCaptor.getValue().getCreatedById(), pointEntity.getCreatedById());
        assertEquals(entityArgumentCaptor.getValue().getCreatedByUser(), pointEntity.getCreatedByUser());
        assertEquals(entityArgumentCaptor.getValue().getCreatedAt(), pointEntity.getCreatedAt());
    }

    @Test
    public void whenGetCalledWithSpecificIdThenRepoFindByIdShouldBeCalledWithSameIdOnlyOnce() {
        when(pointRepository.findById(any())).thenReturn(Optional.of(pointEntity));

        pointService.get(15, testUser);

        verify(pointRepository).findById(15);
        verify(pointRepository, times(1)).findById(any());
    }

    @Test
    public void whenGetCalledThenReturnedValueShouldBeMappedBeanOfEntityReturnedByRepoFindByIdMethod() {
        when(pointRepository.findById(any())).thenReturn(Optional.of(pointEntity));

        Point point = pointService.get(any(), testUser);

        assertEquals(pointBean, point);
    }

    @Test
    public void whenGetCalledWithIdThatNotExistInDBThenThrowCustomException() {
        when(pointRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> pointService.get(any(), testUser));
    }
}