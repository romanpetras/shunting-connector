package eu.circletouch.shuntingconn.mappers;

import eu.circletouch.shuntigconn.beans.dt.WagonType;
import eu.circletouch.shuntingconn.entities.WagonTypeEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WagonTypeMapperTest {
    @Test
    public void whenBeanMappedToEntityRelatedFieldsShouldMatch(){
        WagonTypeMapper wagonTypeMapper = WagonTypeMapper.INSTANCE;
        WagonType wagonType = new WagonType();
        wagonType.setCode("S0M3L0N4C0D3H3R3");
        wagonType.setDescription("This description describes a describable wagonType");
        wagonType.setId(12321);

        WagonTypeEntity wagonTypeEntity = wagonTypeMapper.beanToEntity(wagonType);

        assertEquals(wagonTypeEntity.getCode(), wagonType.getCode());
        assertEquals(wagonTypeEntity.getDescription(), wagonType.getDescription());
        assertEquals(wagonTypeEntity.getId(), wagonType.getId());
    }

    @Test
    public void whenEntityMappedToBeanRelatedFieldsShouldMatch(){
        WagonTypeMapper wagonTypeMapper = WagonTypeMapper.INSTANCE;
        WagonTypeEntity wagonTypeEntity = new WagonTypeEntity();
        wagonTypeEntity.setCode("S0M3L0N4C0D3H3R3");
        wagonTypeEntity.setDescription("This description describes a describable wagonType");
        wagonTypeEntity.setId(12321);

        WagonType wagonType = wagonTypeMapper.entityToBean(wagonTypeEntity);

        assertEquals(wagonTypeEntity.getCode(), wagonType.getCode());
        assertEquals(wagonTypeEntity.getDescription(), wagonType.getDescription());
        assertEquals(wagonTypeEntity.getId(), wagonType.getId());
    }
}