package eu.circletouch.shuntigconn.beans.dt;

import lombok.Data;

@Data
public class MissionTask {

    private Integer id;
    private Integer sequence;
    private Integer mainManeuverId;
    private Integer trainPart;
    private Point departurePoint;
    private Point arrivalPoint;
    private Direction direction;
    private Boolean couple;
    private Boolean decouple;
    private Boolean split;
    private Integer splitPart;
    private SplitPosition splitPosition;
    private Integer splitNumber;
    private Integer missionId;
}
