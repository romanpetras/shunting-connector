package eu.circletouch.shuntigconn.beans.dt;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SimulationRequest {

    private Integer id;
    private SimulationRequestStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<MainManeuver> mainManeuverList;
    private List<Mission> missionList;

}
