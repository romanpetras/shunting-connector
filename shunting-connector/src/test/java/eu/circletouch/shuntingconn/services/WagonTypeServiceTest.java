package eu.circletouch.shuntingconn.services;

import eu.circletouch.shuntigconn.beans.dt.WagonType;
import eu.circletouch.shuntingconn.entities.WagonTypeEntity;
import eu.circletouch.shuntingconn.exceptions.CustomException;
import eu.circletouch.shuntingconn.mappers.WagonTypeMapper;
import eu.circletouch.shuntingconn.repositories.WagonTypeRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WagonTypeServiceTest {
    @Mock
    private WagonTypeRepository wagonTypeRepository;
    WagonTypeMapper wagonTypeMapper = WagonTypeMapper.INSTANCE;
    WagonType wagonTypeBean;
    WagonTypeEntity wagonTypeEntity;
    User testUser;

    WagonTypeService wagonTypeService;
    ArgumentCaptor<WagonTypeEntity> entityArgumentCaptor;

    @BeforeEach
    public void init(){
        wagonTypeService = new WagonTypeService(wagonTypeRepository);
        entityArgumentCaptor = ArgumentCaptor.forClass(WagonTypeEntity.class);
        testUser = new User();

        testUser.setId((long)12);
        testUser.setUsername("testUserName");

        wagonTypeBean = new WagonType();
        wagonTypeBean.setCode("S0M3L0N4C0D3H3R3");
        wagonTypeBean.setDescription("This description describes a describable wagonType");
        wagonTypeBean.setId(12321);

        wagonTypeEntity = new WagonTypeEntity();
        wagonTypeEntity.setCode("S0M3L0N4C0D3H3R3");
        wagonTypeEntity.setDescription("This description describes a describable wagonType");
        wagonTypeEntity.setId(12321);
    }

    @Test
    public void whenCreateCalledWithBeanParameterThenRepoSaveMethodShouldBeCalledWithMappedEntity() {
        wagonTypeService.create(wagonTypeBean, testUser);

        verify(wagonTypeRepository).save(entityArgumentCaptor.capture());
        verify(wagonTypeRepository, times(1)).save(any());
        assertEquals(wagonTypeMapper.entityToBean(entityArgumentCaptor.getValue()), wagonTypeBean);
        assertEquals(entityArgumentCaptor.getValue().getCreatedById(), testUser.getId());
        assertEquals(entityArgumentCaptor.getValue().getCreatedByUser(), testUser.getUsername());
    }

    @Test
    public void whenUpdateCalledWithBeanParameterThenRepoSaveMethodShouldBeCalledWithMappedEntity() {
        when(wagonTypeRepository.findById(12321)).thenReturn(Optional.of(wagonTypeEntity));

        wagonTypeService.update(wagonTypeBean, testUser);

        verify(wagonTypeRepository).save(entityArgumentCaptor.capture());
        verify(wagonTypeRepository, times(1)).save(any());
        assertEquals(wagonTypeMapper.entityToBean(entityArgumentCaptor.getValue()), wagonTypeBean);
        assertEquals(entityArgumentCaptor.getValue().getUpdatedById(), testUser.getId());
        assertEquals(entityArgumentCaptor.getValue().getUpdatedByUser(), testUser.getUsername());
    }

    @Test
    public void whenUpdateCalledWithIdThatDoesNotExistInDBThenAnExceptionShouldBeThrown() {
        when(wagonTypeRepository.findById(12321)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> wagonTypeService.update(wagonTypeBean, testUser));
        verify(wagonTypeRepository, times(1)).findById(any());
    }

    @Test
    public void whenUpdateCalledOnEntityThenTheCreateInformationShouldBeKept() {
        wagonTypeEntity.setCreatedById(32L);
        wagonTypeEntity.setCreatedByUser("createdByUser");
        wagonTypeEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.of(2001, 8, 2, 5, 46)));
        when(wagonTypeRepository.findById(12321)).thenReturn(Optional.of(wagonTypeEntity));

        wagonTypeService.update(wagonTypeBean, testUser);

        verify(wagonTypeRepository).save(entityArgumentCaptor.capture());
        assertEquals(entityArgumentCaptor.getValue().getCreatedById(), wagonTypeEntity.getCreatedById());
        assertEquals(entityArgumentCaptor.getValue().getCreatedByUser(), wagonTypeEntity.getCreatedByUser());
        assertEquals(entityArgumentCaptor.getValue().getCreatedAt(), wagonTypeEntity.getCreatedAt());
    }

    @Test
    public void whenGetCalledWithSpecificIdThenRepoFindByIdShouldBeCalledWithSameIdOnlyOnce() {
        when(wagonTypeRepository.findById(any())).thenReturn(Optional.of(wagonTypeEntity));

        wagonTypeService.get(15, testUser);

        verify(wagonTypeRepository).findById(15);
        verify(wagonTypeRepository, times(1)).findById(any());
    }

    @Test
    public void whenGetCalledThenReturnedValueShouldBeMappedBeanOfEntityReturnedByRepoFindByIdMethod() {
        when(wagonTypeRepository.findById(any())).thenReturn(Optional.of(wagonTypeEntity));

        WagonType wagonType = wagonTypeService.get(any(), testUser);

        assertEquals(wagonTypeBean, wagonType);
    }

    @Test
    public void whenGetCalledWithIdThatNotExistInDBThenThrowCustomException() {
        when(wagonTypeRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> wagonTypeService.get(any(), testUser));
    }
}