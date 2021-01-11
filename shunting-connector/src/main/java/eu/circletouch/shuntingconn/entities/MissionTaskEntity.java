package eu.circletouch.shuntingconn.entities;

import eu.circletouch.shuntigconn.beans.dt.Direction;
import eu.circletouch.shuntigconn.beans.dt.SplitPosition;
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
@Table(name = "mission_tasks", schema = "shuntingconn")
public class MissionTaskEntity extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sequence")
    private Integer sequence;

    @ManyToOne
    @JoinColumn(name = "main_maneuver_id", referencedColumnName = "id", insertable = false, updatable = false)
    private MainManeuverEntity mainManeuver;

    @Column(name = "main_maneuver_id")
    private Integer mainManeuverId;

    @Column(name = "train_part")
    private Integer trainPart;

    @ManyToOne
    @JoinColumn(name = "departure_point_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PointEntity departurePoint;

    @Column(name = "departure_point_id")
    private Integer departurePointId;

    @ManyToOne
    @JoinColumn(name = "arrival_point_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PointEntity arrivalPoint;

    @Column(name = "arrival_point_id")
    private Integer arrivalPointId;

    @Column(name = "direction")
    @Enumerated(EnumType.STRING)
    private Direction direction;

    @Column(name = "couple")
    private Boolean couple;

    @Column(name = "decouple")
    private Boolean decouple;

    @Column(name = "split")
    private Boolean split;

    @Column(name = "split_part")
    private Integer splitPart;

    @Column(name = "split_position")
    @Enumerated(EnumType.STRING)
    private SplitPosition splitPosition;

    @Column(name = "split_number")
    private Integer splitNumber;

    @ManyToOne
    @JoinColumn(name = "mission_id", referencedColumnName = "id", insertable = false, updatable = false)
    private MissionEntity mission;

    @Column(name = "mission_id")
    private Integer missionId;
}
