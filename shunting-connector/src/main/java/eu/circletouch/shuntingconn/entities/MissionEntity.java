package eu.circletouch.shuntingconn.entities;

import java.sql.Timestamp;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "missions", schema = "shuntingconn")
public class MissionEntity extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start_date_time")
    private Timestamp startDateTime;

    @ManyToOne
    @JoinColumn(name = "locomotor_id", referencedColumnName = "id", insertable = false, updatable = false)
    private LocomotorEntity locomotor;

    @Column(name = "locomotor_id")
    private Integer locomotorId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "mission")
    private List<MissionTaskEntity> missionTaskList;

    @Column(name = "trace_number")
    private String traceNumber;

    @ManyToOne
    @JoinColumn(name = "locomotor_starting_point_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PointEntity locomotorStartingPoint;

    @Column(name = "locomotor_starting_point_id")
    private Integer locomotorStartingPointId;

    @ManyToOne
    @JoinColumn(name = "train_starting_point_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PointEntity trainStartingPoint;

    @Column(name = "train_starting_point_id")
    private Integer trainStartingPointId;

    @ManyToOne
    @JoinColumn(name = "train_ending_point_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PointEntity trainEndingPoint;

    @Column(name = "train_ending_point_id")
    private Integer trainEndingPointId;

    @Column(name = "train_split")
    private Boolean trainSplit;

    @Column(name = "cut_number")
    private Integer cutNumber;

    @ManyToOne
    @JoinColumn(name = "wagon_cut_ending_point_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PointEntity wagonCutEndingPoint;

    @Column(name = "wagon_cut_ending_point_id")
    private Integer wagonCutEndingPointId;

    @ManyToOne
    @JoinColumn(name = "simulation_request_id", referencedColumnName = "id", insertable = false, updatable = false)
    private SimulationRequestEntity simulationRequest;

    @Column(name = "simulation_request_id")
    private Integer simulationRequestId;
}
