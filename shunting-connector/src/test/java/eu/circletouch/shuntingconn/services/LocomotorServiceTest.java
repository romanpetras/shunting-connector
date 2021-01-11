package eu.circletouch.shuntingconn.services;

import eu.circletouch.shuntigconn.beans.dt.Locomotor;
import eu.circletouch.shuntingconn.entities.LocomotorEntity;
import eu.circletouch.shuntingconn.exceptions.CustomException;
import eu.circletouch.shuntingconn.mappers.LocomotorMapper;
import eu.circletouch.shuntingconn.repositories.LocomotorRepository;
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
class LocomotorServiceTest {
    @Mock
    private LocomotorRepository locomotorRepository;
    LocomotorMapper locomotorMapper = LocomotorMapper.INSTANCE;
    Locomotor locomotorBean;
    LocomotorEntity locomotorEntity;
    User testUser;

    LocomotorService locomotorService;
    ArgumentCaptor<LocomotorEntity> entityArgumentCaptor;

    @BeforeEach
    public void init(){
        locomotorService = new LocomotorService(locomotorRepository);
        entityArgumentCaptor = ArgumentCaptor.forClass(LocomotorEntity.class);
        testUser = new User();

        testUser.setId((long)12);
        testUser.setUsername("testUserName");

        locomotorBean = new Locomotor();
        locomotorBean.setCode("S0M3L0N4C0D3H3R3");
        locomotorBean.setDescription("This description describes a describable locomotor");
        locomotorBean.setId(12321);

        locomotorEntity = new LocomotorEntity();
        locomotorEntity.setCode("S0M3L0N4C0D3H3R3");
        locomotorEntity.setDescription("This description describes a describable locomotor");
        locomotorEntity.setId(12321);
    }

    @Test
    public void whenCreateCalledWithBeanParameterThenRepoSaveMethodShouldBeCalledWithMappedEntity() {
        locomotorService.create(locomotorBean, testUser);

        verify(locomotorRepository).save(entityArgumentCaptor.capture());
        verify(locomotorRepository, times(1)).save(any());
        assertEquals(locomotorMapper.entityToBean(entityArgumentCaptor.getValue()), locomotorBean);
        assertEquals(entityArgumentCaptor.getValue().getCreatedById(), testUser.getId());
        assertEquals(entityArgumentCaptor.getValue().getCreatedByUser(), testUser.getUsername());
    }

    @Test
    public void whenUpdateCalledWithBeanParameterThenRepoSaveMethodShouldBeCalledWithMappedEntity() {
        when(locomotorRepository.findById(12321)).thenReturn(Optional.of(locomotorEntity));

        locomotorService.update(locomotorBean, testUser);

        verify(locomotorRepository).save(entityArgumentCaptor.capture());
        verify(locomotorRepository, times(1)).save(any());
        assertEquals(locomotorMapper.entityToBean(entityArgumentCaptor.getValue()), locomotorBean);
        assertEquals(entityArgumentCaptor.getValue().getUpdatedById(), testUser.getId());
        assertEquals(entityArgumentCaptor.getValue().getUpdatedByUser(), testUser.getUsername());
    }

    @Test
    public void whenUpdateCalledWithIdThatDoesNotExistInDBThenAnExceptionShouldBeThrown() {
        when(locomotorRepository.findById(12321)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> locomotorService.update(locomotorBean, testUser));
        verify(locomotorRepository, times(1)).findById(any());
    }

    @Test
    public void whenUpdateCalledOnEntityThenTheCreateInformationShouldBeKept() {
        locomotorEntity.setCreatedById(32L);
        locomotorEntity.setCreatedByUser("createdByUser");
        locomotorEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.of(2001, 8, 2, 5, 46)));
        when(locomotorRepository.findById(12321)).thenReturn(Optional.of(locomotorEntity));

        locomotorService.update(locomotorBean, testUser);

        verify(locomotorRepository).save(entityArgumentCaptor.capture());
        assertEquals(entityArgumentCaptor.getValue().getCreatedById(), locomotorEntity.getCreatedById());
        assertEquals(entityArgumentCaptor.getValue().getCreatedByUser(), locomotorEntity.getCreatedByUser());
        assertEquals(entityArgumentCaptor.getValue().getCreatedAt(), locomotorEntity.getCreatedAt());
    }

    @Test
    public void whenGetCalledWithSpecificIdThenRepoFindByIdShouldBeCalledWithSameIdOnlyOnce() {
        when(locomotorRepository.findById(any())).thenReturn(Optional.of(locomotorEntity));

        locomotorService.get(15, testUser);

        verify(locomotorRepository).findById(15);
        verify(locomotorRepository, times(1)).findById(any());
    }

    @Test
    public void whenGetCalledThenReturnedValueShouldBeMappedBeanOfEntityReturnedByRepoFindByIdMethod() {
        when(locomotorRepository.findById(any())).thenReturn(Optional.of(locomotorEntity));

        Locomotor locomotor = locomotorService.get(any(), testUser);

        assertEquals(locomotorBean, locomotor);
    }

    @Test
    public void whenGetCalledWithIdThatNotExistInDBThenThrowCustomException() {
        when(locomotorRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> locomotorService.get(any(), testUser));
    }
}