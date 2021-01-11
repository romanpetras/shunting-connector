package eu.circletouch.shuntingconn.mappers;

import eu.circletouch.shuntigconn.beans.dt.Point;
import eu.circletouch.shuntingconn.entities.PointEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointMapperTest {
    @Test
    public void whenBeanMappedToEntityRelatedFieldsShouldMatch(){
        PointMapper pointMapper = PointMapper.INSTANCE;
        Point point = new Point();
        point.setCode("S0M3L0N4C0D3H3R3");
        point.setDescription("This description describes a describable point");
        point.setId(12321);
        point.setPortEntrance(true);
        point.setLocomotorEnd(false);
        point.setRegression(true);
        point.setInManeuverPark(false);
        point.setStop(true);

        PointEntity pointEntity = pointMapper.beanToEntity(point);

        assertEquals(pointEntity.getCode(), point.getCode());
        assertEquals(pointEntity.getDescription(), point.getDescription());
        assertEquals(pointEntity.getId(), point.getId());
        assertEquals(pointEntity.getPortEntrance(), point.getPortEntrance());
        assertEquals(pointEntity.getLocomotorEnd(), point.getLocomotorEnd());
        assertEquals(pointEntity.getRegression(), point.getRegression());
        assertEquals(pointEntity.getInManeuverPark(), point.getInManeuverPark());
        assertEquals(pointEntity.getStop(), point.getStop());
    }

    @Test
    public void whenEntityMappedToBeanRelatedFieldsShouldMatch(){
        PointMapper pointMapper = PointMapper.INSTANCE;
        PointEntity pointEntity = new PointEntity();
        pointEntity.setCode("S0M3L0N4C0D3H3R3");
        pointEntity.setDescription("This description describes a describable point");
        pointEntity.setId(12321);
        pointEntity.setPortEntrance(true);
        pointEntity.setLocomotorEnd(false);
        pointEntity.setRegression(true);
        pointEntity.setInManeuverPark(false);
        pointEntity.setStop(true);

        Point point = pointMapper.entityToBean(pointEntity);

        assertEquals(pointEntity.getCode(), point.getCode());
        assertEquals(pointEntity.getDescription(), point.getDescription());
        assertEquals(pointEntity.getId(), point.getId());
        assertEquals(pointEntity.getPortEntrance(), point.getPortEntrance());
        assertEquals(pointEntity.getLocomotorEnd(), point.getLocomotorEnd());
        assertEquals(pointEntity.getRegression(), point.getRegression());
        assertEquals(pointEntity.getInManeuverPark(), point.getInManeuverPark());
        assertEquals(pointEntity.getStop(), point.getStop());
    }
}