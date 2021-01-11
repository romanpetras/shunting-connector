package eu.circletouch.shuntigconn.beans.dt;

import lombok.Data;

@Data
public class Point {

    private Integer id;
    private String code;
    private String description;
    private Boolean regression;
    private Boolean stop;
    private Boolean portEntrance;
    private Boolean locomotorEnd;
    private Boolean inManeuverPark;

}
