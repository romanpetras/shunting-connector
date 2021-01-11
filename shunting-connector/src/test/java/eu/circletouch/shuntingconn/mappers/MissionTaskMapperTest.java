package eu.circletouch.shuntingconn.mappers;

import eu.circletouch.shuntigconn.beans.dt.Direction;
import eu.circletouch.shuntigconn.beans.dt.Mission;
import eu.circletouch.shuntigconn.beans.dt.MissionTask;
import eu.circletouch.shuntigconn.beans.dt.Point;
import eu.circletouch.shuntigconn.beans.dt.SplitPosition;
import eu.circletouch.shuntingconn.entities.MainManeuverEntity;
import eu.circletouch.shuntingconn.entities.MissionEntity;
import eu.circletouch.shuntingconn.entities.MissionTaskEntity;
import eu.circletouch.shuntingconn.entities.PointEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MissionTaskMapperTest {
    @Test
    public void whenBeanMappedToEntityRelatedFieldsShouldMatch(){
        MissionTaskMapper missionTaskMapper = MissionTaskMapper.INSTANCE;
        MissionTask missionTask = new MissionTask();
        Point point1 = new Point();
        point1.setId(9);
        Point point2 = new Point();
        point2.setId(8);
        missionTask.setId(5);
        missionTask.setSequence(112358);
        missionTask.setMainManeuverId(1);
        missionTask.setTrainPart(457);
        missionTask.setDeparturePoint(point1);
        missionTask.setArrivalPoint(point2);
        missionTask.setDirection(Direction.LEFT_TO_RIGHT);
        missionTask.setCouple(true);
        missionTask.setDecouple(false);
        missionTask.setSplit(true);
        missionTask.setSplitPart(1254);
        missionTask.setSplitPosition(SplitPosition.HEAD);
        missionTask.setSplitNumber(6589);
        missionTask.setMissionId(132);

        MissionTaskEntity missionTaskEntity = missionTaskMapper.beanToEntity(missionTask);

        assertEquals(missionTaskEntity.getId(), missionTask.getId());
        assertEquals(missionTaskEntity.getSequence(), missionTask.getSequence());
        assertEquals(missionTaskEntity.getTrainPart(), missionTask.getTrainPart());
        assertEquals(missionTaskEntity.getDeparturePoint().getId(), missionTask.getDeparturePoint().getId());
        assertEquals(missionTaskEntity.getDeparturePoint().getId(), missionTask.getDeparturePoint().getId());
        assertEquals(missionTaskEntity.getArrivalPointId(), missionTask.getArrivalPoint().getId());
        assertEquals(missionTaskEntity.getArrivalPointId(), missionTask.getArrivalPoint().getId());
        assertEquals(missionTaskEntity.getDirection(), missionTask.getDirection());
        assertEquals(missionTaskEntity.getCouple(), missionTask.getCouple());
        assertEquals(missionTaskEntity.getDecouple(), missionTask.getDecouple());
        assertEquals(missionTaskEntity.getSplit(), missionTask.getSplit());
        assertEquals(missionTaskEntity.getSplitPart(), missionTask.getSplitPart());
        assertEquals(missionTaskEntity.getSplitPosition(), missionTask.getSplitPosition());
        assertEquals(missionTaskEntity.getSplitNumber(), missionTask.getSplitNumber());
    }

    @Test
    public void whenEntityMappedToBeanRelatedFieldsShouldMatch(){
        MissionTaskMapper missionTaskMapper = MissionTaskMapper.INSTANCE;
        MissionTaskEntity missionTaskEntity = new MissionTaskEntity();
        PointEntity point1 = new PointEntity();
        point1.setId(9);
        PointEntity point2 = new PointEntity();
        point2.setId(8);
        MainManeuverEntity mainManeuverEntity = new MainManeuverEntity();
        MissionEntity missionEntity = new MissionEntity();
        missionEntity.setId(132);
        mainManeuverEntity.setId(2);
        missionTaskEntity.setId(5);
        missionTaskEntity.setSequence(112358);
        missionTaskEntity.setMainManeuver(mainManeuverEntity);
        missionTaskEntity.setTrainPart(457);
        missionTaskEntity.setDeparturePoint(point1);
        missionTaskEntity.setArrivalPoint(point2);
        missionTaskEntity.setDirection(Direction.LEFT_TO_RIGHT);
        missionTaskEntity.setCouple(true);
        missionTaskEntity.setDecouple(false);
        missionTaskEntity.setSplit(true);
        missionTaskEntity.setSplitPart(1254);
        missionTaskEntity.setSplitPosition(SplitPosition.HEAD);
        missionTaskEntity.setSplitNumber(6589);
        missionTaskEntity.setMission(missionEntity);

        MissionTask missionTask = missionTaskMapper.entityToBean(missionTaskEntity);

        assertEquals(missionTask.getId(), missionTaskEntity.getId());
        assertEquals(missionTask.getSequence(), missionTaskEntity.getSequence());
        assertEquals(missionTask.getMainManeuverId(), missionTaskEntity.getMainManeuver().getId());
        assertEquals(missionTask.getTrainPart(), missionTaskEntity.getTrainPart());
        assertEquals(missionTask.getDeparturePoint().getId(), missionTaskEntity.getDeparturePoint().getId());
        assertEquals(missionTask.getArrivalPoint().getId(), missionTaskEntity.getArrivalPoint().getId());
        assertEquals(missionTask.getDirection(), missionTaskEntity.getDirection());
        assertEquals(missionTask.getCouple(), missionTaskEntity.getCouple());
        assertEquals(missionTask.getDecouple(), missionTaskEntity.getDecouple());
        assertEquals(missionTask.getSplit(), missionTaskEntity.getSplit());
        assertEquals(missionTask.getSplitPart(), missionTaskEntity.getSplitPart());
        assertEquals(missionTask.getSplitPosition(), missionTaskEntity.getSplitPosition());
        assertEquals(missionTask.getSplitNumber(), missionTaskEntity.getSplitNumber());
        assertEquals(missionTask.getMissionId(), missionTaskEntity.getMission().getId());
    }
}