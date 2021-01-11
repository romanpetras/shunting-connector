package eu.circletouch.shuntigconn.beans.dt;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MainManeuver {

    private Integer id;
    private ManeuverType maneuverType;
    private String traceNumber;
    private Terminal terminal;
    private LocalDateTime eta;  // verify meaning
    private LocalDateTime etp;  // verify meaning

    private Point arrivalPoint;
    private Point maneuverParkPoint;
    private Point regressionPoint;
    private Point locomotorEndingPoint;
    private Integer wagonsNumber;
    private WagonType wagonsType;
    private Integer simulationRequestId;
}
