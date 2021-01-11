package eu.circletouch.shuntingconn.services;

import eu.circletouch.shuntigconn.beans.dt.Terminal;
import eu.circletouch.shuntingconn.entities.TerminalEntity;
import eu.circletouch.shuntingconn.exceptions.CustomException;
import eu.circletouch.shuntingconn.mappers.TerminalMapper;
import eu.circletouch.shuntingconn.repositories.TerminalRepository;
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
class TerminalServiceTest {
    @Mock
    private TerminalRepository terminalRepository;
    TerminalMapper terminalMapper = TerminalMapper.INSTANCE;
    Terminal terminalBean;
    TerminalEntity terminalEntity;
    User testUser;

    TerminalService terminalService;
    ArgumentCaptor<TerminalEntity> entityArgumentCaptor;

    @BeforeEach
    public void init(){
        terminalService = new TerminalService(terminalRepository);
        entityArgumentCaptor = ArgumentCaptor.forClass(TerminalEntity.class);
        testUser = new User();

        testUser.setId((long)12);
        testUser.setUsername("testUserName");

        terminalBean = new Terminal();
        terminalBean.setCode("S0M3L0N4C0D3H3R3");
        terminalBean.setDescription("This description describes a describable terminal");
        terminalBean.setId(12321);

        terminalEntity = new TerminalEntity();
        terminalEntity.setCode("S0M3L0N4C0D3H3R3");
        terminalEntity.setDescription("This description describes a describable terminal");
        terminalEntity.setId(12321);
    }

    @Test
    public void whenCreateCalledWithBeanParameterThenRepoSaveMethodShouldBeCalledWithMappedEntity() {
        terminalService.create(terminalBean, testUser);

        verify(terminalRepository).save(entityArgumentCaptor.capture());
        verify(terminalRepository, times(1)).save(any());
        assertEquals(terminalMapper.entityToBean(entityArgumentCaptor.getValue()), terminalBean);
        assertEquals(entityArgumentCaptor.getValue().getCreatedById(), testUser.getId());
        assertEquals(entityArgumentCaptor.getValue().getCreatedByUser(), testUser.getUsername());
    }

    @Test
    public void whenUpdateCalledWithBeanParameterThenRepoSaveMethodShouldBeCalledWithMappedEntity() {
        when(terminalRepository.findById(12321)).thenReturn(Optional.of(terminalEntity));

        terminalService.update(terminalBean, testUser);

        verify(terminalRepository).save(entityArgumentCaptor.capture());
        verify(terminalRepository, times(1)).save(any());
        assertEquals(terminalMapper.entityToBean(entityArgumentCaptor.getValue()), terminalBean);
        assertEquals(entityArgumentCaptor.getValue().getUpdatedById(), testUser.getId());
        assertEquals(entityArgumentCaptor.getValue().getUpdatedByUser(), testUser.getUsername());
    }

    @Test
    public void whenUpdateCalledWithIdThatDoesNotExistInDBThenAnExceptionShouldBeThrown() {
        when(terminalRepository.findById(12321)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> terminalService.update(terminalBean, testUser));
        verify(terminalRepository, times(1)).findById(any());
    }

    @Test
    public void whenUpdateCalledOnEntityThenTheCreateInformationShouldBeKept() {
        terminalEntity.setCreatedById(32L);
        terminalEntity.setCreatedByUser("createdByUser");
        terminalEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.of(2001, 8, 2, 5, 46)));
        when(terminalRepository.findById(12321)).thenReturn(Optional.of(terminalEntity));

        terminalService.update(terminalBean, testUser);

        verify(terminalRepository).save(entityArgumentCaptor.capture());
        assertEquals(entityArgumentCaptor.getValue().getCreatedById(), terminalEntity.getCreatedById());
        assertEquals(entityArgumentCaptor.getValue().getCreatedByUser(), terminalEntity.getCreatedByUser());
        assertEquals(entityArgumentCaptor.getValue().getCreatedAt(), terminalEntity.getCreatedAt());
    }

    @Test
    public void whenGetCalledWithSpecificIdThenRepoFindByIdShouldBeCalledWithSameIdOnlyOnce() {
        when(terminalRepository.findById(any())).thenReturn(Optional.of(terminalEntity));

        terminalService.get(15, testUser);

        verify(terminalRepository).findById(15);
        verify(terminalRepository, times(1)).findById(any());
    }

    @Test
    public void whenGetCalledThenReturnedValueShouldBeMappedBeanOfEntityReturnedByRepoFindByIdMethod() {
        when(terminalRepository.findById(any())).thenReturn(Optional.of(terminalEntity));

        Terminal terminal = terminalService.get(any(), testUser);

        assertEquals(terminalBean, terminal);
    }

    @Test
    public void whenGetCalledWithIdThatNotExistInDBThenThrowCustomException() {
        when(terminalRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> terminalService.get(any(), testUser));
    }
}