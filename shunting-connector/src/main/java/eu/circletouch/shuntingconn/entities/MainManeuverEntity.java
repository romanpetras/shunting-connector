package eu.circletouch.shuntingconn.entities;

import java.sql.Timestamp;
import eu.circletouch.shuntigconn.beans.dt.ManeuverType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "main_maneuvers", schema = "shuntingconn")
public class MainManeuverEntity extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "maneuver_type")
    @Enumerated(EnumType.STRING)
    private ManeuverType maneuverType;

    @Column(name = "trace_number")
    private String traceNumber;

    @ManyToOne
    @JoinColumn(name = "terminal_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TerminalEntity terminal;

    @Column(name = "terminal_id")
    private Integer terminalId;

    @Column(name = "eta")
    private Timestamp eta;

    @Column(name = "etp")
    private Timestamp etp;

    @ManyToOne
    @JoinColumn(name = "arrival_point_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PointEntity arrivalPoint;

    @Column(name = "arrival_point_id")
    private Integer arrivalPointId;

    @ManyToOne
    @JoinColumn(name = "maneuver_park_point_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PointEntity maneuverParkPoint;

    @Column(name = "maneuver_park_point_id")
    private Integer maneuverParkPointId;

    @ManyToOne
    @JoinColumn(name = "regression_point_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PointEntity regressionPoint;

    @Column(name = "regression_point_id")
    private Integer regressionPointId;

    @ManyToOne
    @JoinColumn(name = "locomotor_ending_point_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PointEntity locomotorEndingPoint;

    @Column(name = "locomotor_ending_point_id")
    private Integer locomotorEndingPointId;

    @Column(name = "wagons_number")
    private Integer wagonsNumber;

    @ManyToOne
    @JoinColumn(name = "wagon_type_id", referencedColumnName = "id", insertable = false, updatable = false)
    private WagonTypeEntity wagonsType;

    @Column(name = "wagon_type_id")
    private Integer wagonsTypeId;

    @ManyToOne
    @JoinColumn(name = "simulation_request_id", referencedColumnName = "id", insertable = false, updatable = false)
    private SimulationRequestEntity simulationRequest;

    @Column(name = "simulation_request_id")
    private Integer simulationRequestId;
}
