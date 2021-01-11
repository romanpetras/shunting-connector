package eu.circletouch.shuntingconn.mappers;

import eu.circletouch.shuntigconn.beans.dt.Locomotor;
import eu.circletouch.shuntigconn.beans.dt.Mission;
import eu.circletouch.shuntigconn.beans.dt.MissionTask;
import eu.circletouch.shuntigconn.beans.dt.Point;
import eu.circletouch.shuntigconn.beans.dt.SimulationRequest;
import eu.circletouch.shuntigconn.beans.dt.Terminal;
import eu.circletouch.shuntigconn.beans.dt.WagonType;
import eu.circletouch.shuntingconn.entities.LocomotorEntity;
import eu.circletouch.shuntingconn.entities.MissionEntity;
import eu.circletouch.shuntingconn.entities.MissionTaskEntity;
import eu.circletouch.shuntingconn.entities.PointEntity;
import eu.circletouch.shuntingconn.entities.SimulationRequestEntity;
import eu.circletouch.shuntingconn.entities.TerminalEntity;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MissionMapperTest {
    @Test
    public void whenBeanMappedToEntityRelatedFieldsShouldMatch(){
        MissionMapper missionMapper = MissionMapper.INSTANCE;
        Mission mission = new Mission();
        Locomotor locomotor = new Locomotor();
        locomotor.setId(5);
        MissionTask missionTask = new MissionTask();
        missionTask.setId(13);
        MissionTask missionTask1 = new MissionTask();
        missionTask1.setId(23);
        Terminal terminal = new Terminal();
        terminal.setId(2);
        Point point1 = new Point();
        point1.setId(9);
        Point point2 = new Point();
        point2.setId(8);
        Point point3 = new Point();
        point3.setId(7);
        Point point4 = new Point();
        point3.setId(6);
        WagonType wagonType = new WagonType();
        wagonType.setId(15);
        mission.setId(12321);
        mission.setStartDateTime(LocalDateTime.of(2001, 8, 2, 5, 46));
        mission.setLocomotor(locomotor);
        mission.setMissionTaskList(Arrays.asList(missionTask1, missionTask));
        mission.setTraceNumber("4B34U7YFU775AC3NUMB3R");
        mission.setLocomotorStartingPoint(point1);
        mission.setTrainStartingPoint(point2);
        mission.setTrainEndingPoint(point3);
        mission.setTrainSplit(true);
        mission.setCutNumber(54);
        mission.setWagonCutEndingPoint(point4);
        mission.setSimulationRequestId(154);

        MissionEntity missionEntity = missionMapper.beanToEntity(mission);

        assertEquals(missionEntity.getId(), mission.getId());
        assertEquals(missionEntity.getStartDateTime(), Timestamp.valueOf(mission.getStartDateTime()));
        assertEquals(missionEntity.getLocomotor().getId(), mission.getLocomotor().getId());
        assertEquals(missionEntity.getLocomotorId(), mission.getLocomotor().getId());
        assertEquals(missionEntity.getMissionTaskList().get(0).getId(), mission.getMissionTaskList().get(0).getId());
        assertEquals(missionEntity.getTraceNumber(), mission.getTraceNumber());
        assertEquals(missionEntity.getLocomotorStartingPoint().getId(), mission.getLocomotorStartingPoint().getId());
        assertEquals(missionEntity.getLocomotorStartingPointId(), mission.getLocomotorStartingPoint().getId());
        assertEquals(missionEntity.getTrainStartingPoint().getId(), mission.getTrainStartingPoint().getId());
        assertEquals(missionEntity.getTrainStartingPointId(), mission.getTrainStartingPoint().getId());
        assertEquals(missionEntity.getTrainEndingPoint().getId(), mission.getTrainEndingPoint().getId());
        assertEquals(missionEntity.getTrainEndingPointId(), mission.getTrainEndingPoint().getId());
        assertEquals(missionEntity.getTrainSplit(), mission.getTrainSplit());
        assertEquals(missionEntity.getCutNumber(), mission.getCutNumber());
        assertEquals(missionEntity.getWagonCutEndingPoint().getId(), mission.getWagonCutEndingPoint().getId());
        assertEquals(missionEntity.getWagonCutEndingPointId(), mission.getWagonCutEndingPoint().getId());
    }

    @Test
    public void whenEntityMappedToBeanRelatedFieldsShouldMatch(){
        MissionMapper missionMapper = MissionMapper.INSTANCE;
        MissionEntity missionEntity = new MissionEntity();
        LocomotorEntity locomotor = new LocomotorEntity();
        locomotor.setId(5);
        MissionTaskEntity missionTask = new MissionTaskEntity();
        missionTask.setId(13);
        MissionTaskEntity missionTask1 = new MissionTaskEntity();
        missionTask1.setId(14);
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
        SimulationRequestEntity simulationRequestEntity = new SimulationRequestEntity();
        simulationRequestEntity.setId(628);
        missionEntity.setId(12321);
        missionEntity.setStartDateTime(Timestamp.valueOf(LocalDateTime.of(2001, 8, 2, 5, 46)));
        missionEntity.setLocomotor(locomotor);
        missionEntity.setMissionTaskList(Arrays.asList(missionTask1, missionTask));
        missionEntity.setTraceNumber("4B34U7YFU775AC3NUMB3R");
        missionEntity.setLocomotorStartingPoint(point1);
        missionEntity.setTrainStartingPoint(point2);
        missionEntity.setTrainEndingPoint(point3);
        missionEntity.setTrainSplit(true);
        missionEntity.setCutNumber(54);
        missionEntity.setWagonCutEndingPoint(point4);
        missionEntity.setSimulationRequest(simulationRequestEntity);

        Mission mission = missionMapper.entityToBean(missionEntity);

        assertEquals(mission.getId(), missionEntity.getId());
        assertEquals(Timestamp.valueOf(mission.getStartDateTime()), missionEntity.getStartDateTime());
        assertEquals(mission.getLocomotor().getId(), missionEntity.getLocomotor().getId());
        assertEquals(mission.getMissionTaskList().get(0).getId(), missionEntity.getMissionTaskList().get(0).getId());
        assertEquals(mission.getTraceNumber(), missionEntity.getTraceNumber());
        assertEquals(mission.getLocomotorStartingPoint().getId(), missionEntity.getLocomotorStartingPoint().getId());
        assertEquals(mission.getTrainStartingPoint().getId(), missionEntity.getTrainStartingPoint().getId());
        assertEquals(mission.getTrainEndingPoint().getId(), missionEntity.getTrainEndingPoint().getId());
        assertEquals(mission.getTrainSplit(), missionEntity.getTrainSplit());
        assertEquals(mission.getCutNumber(), missionEntity.getCutNumber());
        assertEquals(mission.getWagonCutEndingPoint().getId(), missionEntity.getWagonCutEndingPoint().getId());
        assertEquals(mission.getSimulationRequestId(), missionEntity.getSimulationRequest().getId());
    }
}