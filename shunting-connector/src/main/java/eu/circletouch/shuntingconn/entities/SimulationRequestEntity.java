package eu.circletouch.shuntingconn.entities;

import eu.circletouch.shuntigconn.beans.dt.SimulationRequestStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "simulation_requests", schema = "shuntingconn")
public class SimulationRequestEntity extends BaseEntity {

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private SimulationRequestStatus status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "simulationRequest", fetch = FetchType.LAZY)
    private List<MainManeuverEntity> mainManeuverList;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "simulationRequest", fetch = FetchType.LAZY)
    private List<MissionEntity> missionList;
}
