package eu.circletouch.shuntigconn.beans.dt;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Mission {

    private Integer id;
    private LocalDateTime startDateTime;
    private Locomotor locomotor;
    private List<MissionTask> missionTaskList;
    private String traceNumber;
    private Point locomotorStartingPoint;
    private Point trainStartingPoint;
    private Point trainEndingPoint;
    private Boolean trainSplit;
    private Integer cutNumber;
    private Point wagonCutEndingPoint;
    private Integer simulationRequestId;
}
