package eu.circletouch.shuntingconn.mappers;

import eu.circletouch.shuntigconn.beans.dt.Locomotor;
import eu.circletouch.shuntingconn.entities.LocomotorEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocomotorMapperTest {
    @Test
    public void whenBeanMappedToEntityRelatedFieldsShouldMatch(){
        LocomotorMapper locomotorMapper = LocomotorMapper.INSTANCE;
        Locomotor locomotor = new Locomotor();
        locomotor.setCode("S0M3L0N4C0D3H3R3");
        locomotor.setDescription("This description describes a describable locomotor");
        locomotor.setId(12321);

        LocomotorEntity locomotorEntity = locomotorMapper.beanToEntity(locomotor);

        assertEquals(locomotorEntity.getCode(), locomotor.getCode());
        assertEquals(locomotorEntity.getDescription(), locomotor.getDescription());
        assertEquals(locomotorEntity.getId(), locomotor.getId());
    }

    @Test
    public void whenEntityMappedToBeanRelatedFieldsShouldMatch(){
        LocomotorMapper locomotorMapper = LocomotorMapper.INSTANCE;
        LocomotorEntity locomotorEntity = new LocomotorEntity();
        locomotorEntity.setCode("S0M3L0N4C0D3H3R3");
        locomotorEntity.setDescription("This description describes a describable locomotor");
        locomotorEntity.setId(12321);

        Locomotor locomotor = locomotorMapper.entityToBean(locomotorEntity);

        assertEquals(locomotorEntity.getCode(), locomotor.getCode());
        assertEquals(locomotorEntity.getDescription(), locomotor.getDescription());
        assertEquals(locomotorEntity.getId(), locomotor.getId());
    }
}