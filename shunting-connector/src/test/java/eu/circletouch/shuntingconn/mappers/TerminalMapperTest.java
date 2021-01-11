package eu.circletouch.shuntingconn.mappers;

import eu.circletouch.shuntigconn.beans.dt.Terminal;
import eu.circletouch.shuntingconn.entities.TerminalEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TerminalMapperTest {
    @Test
    public void whenBeanMappedToEntityRelatedFieldsShouldMatch(){
        TerminalMapper terminalMapper = TerminalMapper.INSTANCE;
        Terminal terminal = new Terminal();
        terminal.setCode("S0M3L0N4C0D3H3R3");
        terminal.setDescription("This description describes a describable terminal");
        terminal.setId(12321);

        TerminalEntity terminalEntity = terminalMapper.beanToEntity(terminal);

        assertEquals(terminalEntity.getCode(), terminal.getCode());
        assertEquals(terminalEntity.getDescription(), terminal.getDescription());
        assertEquals(terminalEntity.getId(), terminal.getId());
    }

    @Test
    public void whenEntityMappedToBeanRelatedFieldsShouldMatch(){
        TerminalMapper terminalMapper = TerminalMapper.INSTANCE;
        TerminalEntity terminalEntity = new TerminalEntity();
        terminalEntity.setCode("S0M3L0N4C0D3H3R3");
        terminalEntity.setDescription("This description describes a describable terminal");
        terminalEntity.setId(12321);

        Terminal terminal = terminalMapper.entityToBean(terminalEntity);

        assertEquals(terminalEntity.getCode(), terminal.getCode());
        assertEquals(terminalEntity.getDescription(), terminal.getDescription());
        assertEquals(terminalEntity.getId(), terminal.getId());
    }
}