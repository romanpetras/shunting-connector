package eu.circletouch.shuntingconn.mappers;

import eu.circletouch.shuntigconn.beans.dt.MainManeuver;
import eu.circletouch.shuntigconn.beans.dt.Mission;
import eu.circletouch.shuntigconn.beans.dt.SimulationRequest;
import eu.circletouch.shuntigconn.beans.dt.SimulationRequestStatus;
import eu.circletouch.shuntingconn.entities.MainManeuverEntity;
import eu.circletouch.shuntingconn.entities.MissionEntity;
import eu.circletouch.shuntingconn.entities.SimulationRequestEntity;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SimulationRequestMapperTest {
    @Test
    public void whenBeanMappedToEntityRelatedFieldsShouldMatch(){
        SimulationRequestMapper simulationRequestMapper = SimulationRequestMapper.INSTANCE;
        SimulationRequest simulationRequest = new SimulationRequest();
        MainManeuver mainManeuver1 = new MainManeuver();
        mainManeuver1.setId(1542);
        MainManeuver mainManeuver2 = new MainManeuver();
        mainManeuver2.setId(2364);
        Mission mission1 = new Mission();
        mission1.setId(4580);
        Mission mission2 = new Mission();
        mission2.setId(2084);
        simulationRequest.setStatus(SimulationRequestStatus.PENDING);
        simulationRequest.setMainManeuverList(Arrays.asList(mainManeuver1, mainManeuver2));
        simulationRequest.setMissionList(Arrays.asList(mission1, mission2));
        simulationRequest.setId(12321);

        SimulationRequestEntity simulationRequestEntity = simulationRequestMapper.beanToEntity(simulationRequest);

        assertEquals(simulationRequestEntity.getId(), simulationRequest.getId());
        assertEquals(simulationRequestEntity.getStatus(), simulationRequest.getStatus());
        assertEquals(simulationRequestEntity.getMissionList().get(0).getId(), simulationRequest.getMissionList().get(0).getId());
        assertEquals(simulationRequestEntity.getMissionList().get(1).getId(), simulationRequest.getMissionList().get(1).getId());
        assertEquals(simulationRequestEntity.getMainManeuverList().get(0).getId(), simulationRequest.getMainManeuverList().get(0).getId());
        assertEquals(simulationRequestEntity.getMainManeuverList().get(1).getId(), simulationRequest.getMainManeuverList().get(1).getId());
    }

    @Test
    public void whenEntityMappedToBeanRelatedFieldsShouldMatch(){
        SimulationRequestMapper simulationRequestMapper = SimulationRequestMapper.INSTANCE;
        SimulationRequestEntity simulationRequestEntity = new SimulationRequestEntity();
        MainManeuverEntity mainManeuver1 = new MainManeuverEntity();
        mainManeuver1.setId(1542);
        MainManeuverEntity mainManeuver2 = new MainManeuverEntity();
        mainManeuver2.setId(2364);
        MissionEntity mission1 = new MissionEntity();
        mission1.setId(4580);
        MissionEntity mission2 = new MissionEntity();
        mission2.setId(2084);
        simulationRequestEntity.setStatus(SimulationRequestStatus.PENDING);
        simulationRequestEntity.setMainManeuverList(Arrays.asList(mainManeuver1, mainManeuver2));
        simulationRequestEntity.setMissionList(Arrays.asList(mission1, mission2));
        simulationRequestEntity.setId(12321);

        SimulationRequest simulationRequest = simulationRequestMapper.entityToBean(simulationRequestEntity);

        assertEquals(simulationRequest.getId(), simulationRequestEntity.getId());
        assertEquals(simulationRequest.getStatus(), simulationRequestEntity.getStatus());
        assertEquals(simulationRequest.getMissionList().get(0).getId(), simulationRequestEntity.getMissionList().get(0).getId());
        assertEquals(simulationRequest.getMissionList().get(1).getId(), simulationRequestEntity.getMissionList().get(1).getId());
        assertEquals(simulationRequest.getMainManeuverList().get(0).getId(), simulationRequestEntity.getMainManeuverList().get(0).getId());
        assertEquals(simulationRequest.getMainManeuverList().get(1).getId(), simulationRequestEntity.getMainManeuverList().get(1).getId());
    }
}