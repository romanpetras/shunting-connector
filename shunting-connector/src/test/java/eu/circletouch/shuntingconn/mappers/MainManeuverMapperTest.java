package eu.circletouch.shuntingconn.mappers;

import eu.circletouch.shuntigconn.beans.dt.MainManeuver;
import eu.circletouch.shuntigconn.beans.dt.ManeuverType;
import eu.circletouch.shuntigconn.beans.dt.Point;
import eu.circletouch.shuntigconn.beans.dt.SimulationRequest;
import eu.circletouch.shuntigconn.beans.dt.Terminal;
import eu.circletouch.shuntigconn.beans.dt.WagonType;
import eu.circletouch.shuntingconn.entities.MainManeuverEntity;
import eu.circletouch.shuntingconn.entities.PointEntity;
import eu.circletouch.shuntingconn.entities.SimulationRequestEntity;
import eu.circletouch.shuntingconn.entities.TerminalEntity;
import eu.circletouch.shuntingconn.entities.WagonTypeEntity;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainManeuverMapperTest {
    @Test
    public void whenBeanMappedToEntityRelatedFieldsShouldMatch(){
        MainManeuverMapper mainManeuverMapper = MainManeuverMapper.INSTANCE;
        MainManeuver mainManeuver = new MainManeuver();
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
        SimulationRequest simulationRequest = new SimulationRequest();
        simulationRequest.setId(12);
        mainManeuver.setId(12321);
        mainManeuver.setManeuverType(ManeuverType.ARRIVAL);
        mainManeuver.setTraceNumber("4B34U7YFU775AC3NUMB3R");
        mainManeuver.setTerminal(terminal);
        mainManeuver.setEta(LocalDateTime.of(2001, 8, 2, 5, 46));
        mainManeuver.setEtp(LocalDateTime.of(2021, 8, 5, 2, 34));
        mainManeuver.setArrivalPoint(point1);
        mainManeuver.setManeuverParkPoint(point2);
        mainManeuver.setRegressionPoint(point3);
        mainManeuver.setLocomotorEndingPoint(point4);
        mainManeuver.setWagonsNumber(18);
        mainManeuver.setWagonsType(wagonType);
        mainManeuver.setId(195);

        MainManeuverEntity mainManeuverEntity = mainManeuverMapper.beanToEntity(mainManeuver);

        assertEquals(mainManeuverEntity.getId(), mainManeuver.getId());
        assertEquals(mainManeuverEntity.getManeuverType(), mainManeuver.getManeuverType());
        assertEquals(mainManeuverEntity.getTraceNumber(), mainManeuver.getTraceNumber());
        assertEquals(mainManeuverEntity.getTerminal().getId(), mainManeuver.getTerminal().getId());
        assertEquals(mainManeuverEntity.getTerminalId(), mainManeuver.getTerminal().getId());
        assertEquals(mainManeuverEntity.getEta(), Timestamp.valueOf(mainManeuver.getEta()));
        assertEquals(mainManeuverEntity.getEtp(), Timestamp.valueOf(mainManeuver.getEtp()));
        assertEquals(mainManeuverEntity.getManeuverParkPoint().getId(), mainManeuver.getManeuverParkPoint().getId());
        assertEquals(mainManeuverEntity.getManeuverParkPointId(), mainManeuver.getManeuverParkPoint().getId());
        assertEquals(mainManeuverEntity.getArrivalPoint().getId(), mainManeuver.getArrivalPoint().getId());
        assertEquals(mainManeuverEntity.getArrivalPointId(), mainManeuver.getArrivalPoint().getId());
        assertEquals(mainManeuverEntity.getRegressionPoint().getId(), mainManeuver.getRegressionPoint().getId());
        assertEquals(mainManeuverEntity.getRegressionPointId(), mainManeuver.getRegressionPoint().getId());
        assertEquals(mainManeuverEntity.getLocomotorEndingPoint().getId(), mainManeuver.getLocomotorEndingPoint().getId());
        assertEquals(mainManeuverEntity.getLocomotorEndingPointId(), mainManeuver.getLocomotorEndingPoint().getId());
        assertEquals(mainManeuverEntity.getWagonsNumber(), mainManeuver.getWagonsNumber());
        assertEquals(mainManeuverEntity.getWagonsType().getId(), mainManeuver.getWagonsType().getId());
        assertEquals(mainManeuverEntity.getWagonsTypeId(), mainManeuver.getWagonsType().getId());
        assertEquals(mainManeuverEntity.getSimulationRequest().getId(), mainManeuver.getSimulationRequestId());
    }

    @Test
    public void whenEntityMappedToBeanRelatedFieldsShouldMatch(){
        MainManeuverMapper mainManeuverMapper = MainManeuverMapper.INSTANCE;
        MainManeuverEntity maneuverEntity = new MainManeuverEntity();
        TerminalEntity terminal = new TerminalEntity();
        SimulationRequestEntity simulationRequestEntity = new SimulationRequestEntity();
        simulationRequestEntity.setId(154);
        terminal.setId(2);
        PointEntity point1 = new PointEntity();
        point1.setId(9);
        PointEntity point2 = new PointEntity();
        point2.setId(8);
        PointEntity point3 = new PointEntity();
        point3.setId(7);
        PointEntity point4 = new PointEntity();
        point3.setId(6);
        WagonTypeEntity wagonType = new WagonTypeEntity();
        wagonType.setId(15);
        SimulationRequest simulationRequest = new SimulationRequest();
        simulationRequest.setId(12);
        maneuverEntity.setId(12321);
        maneuverEntity.setManeuverType(ManeuverType.ARRIVAL);
        maneuverEntity.setTraceNumber("4B34U7YFU775AC3NUMB3R");
        maneuverEntity.setTerminal(terminal);
        maneuverEntity.setEta(Timestamp.valueOf(LocalDateTime.of(2001, 8, 2, 5, 46)));
        maneuverEntity.setEtp(Timestamp.valueOf(LocalDateTime.of(2021, 8, 5, 2, 34)));
        maneuverEntity.setArrivalPoint(point1);
        maneuverEntity.setManeuverParkPoint(point2);
        maneuverEntity.setRegressionPoint(point3);
        maneuverEntity.setLocomotorEndingPoint(point4);
        maneuverEntity.setWagonsNumber(18);
        maneuverEntity.setWagonsType(wagonType);
        maneuverEntity.setSimulationRequest(simulationRequestEntity);

        MainManeuver mainManeuver = mainManeuverMapper.entityToBean(maneuverEntity);

        assertEquals(mainManeuver.getId(), maneuverEntity.getId());
        assertEquals(mainManeuver.getManeuverType(), maneuverEntity.getManeuverType());
        assertEquals(mainManeuver.getTraceNumber(), maneuverEntity.getTraceNumber());
        assertEquals(mainManeuver.getTerminal().getId(), maneuverEntity.getTerminal().getId());
        assertEquals(Timestamp.valueOf(mainManeuver.getEta()), maneuverEntity.getEta());
        assertEquals(Timestamp.valueOf(mainManeuver.getEtp()), maneuverEntity.getEtp());
        assertEquals(mainManeuver.getManeuverParkPoint().getId(), maneuverEntity.getManeuverParkPoint().getId());
        assertEquals(mainManeuver.getArrivalPoint().getId(), maneuverEntity.getArrivalPoint().getId());
        assertEquals(mainManeuver.getRegressionPoint().getId(), maneuverEntity.getRegressionPoint().getId());
        assertEquals(mainManeuver.getLocomotorEndingPoint().getId(), maneuverEntity.getLocomotorEndingPoint().getId());
        assertEquals(mainManeuver.getWagonsNumber(), maneuverEntity.getWagonsNumber());
        assertEquals(mainManeuver.getWagonsType().getId(), maneuverEntity.getWagonsType().getId());
        assertEquals(mainManeuver.getSimulationRequestId(), maneuverEntity.getSimulationRequest().getId());
    }
}