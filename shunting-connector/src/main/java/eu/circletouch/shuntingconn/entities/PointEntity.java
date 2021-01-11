package eu.circletouch.shuntingconn.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "points", schema = "shuntingconn")
public class PointEntity extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "regression")
    private Boolean regression;

    @Column(name = "stop")
    private Boolean stop;

    @Column(name = "port_entrance")
    private Boolean portEntrance;

    @Column(name = "locomotor_end")
    private Boolean locomotorEnd;

    @Column(name = "in_maneuver_park")
    private Boolean inManeuverPark;
}
