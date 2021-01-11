package eu.circletouch.shuntingconn.repositories;

import eu.circletouch.shuntigconn.beans.dt.SimulationRequestStatus;
import eu.circletouch.shuntingconn.entities.SimulationRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SimulationRequestRepository extends JpaRepository<SimulationRequestEntity, Integer>, SimulationRequestCustomRepository {
    Optional<SimulationRequestEntity> findFirstByStatusOrderByUpdatedAt(SimulationRequestStatus status);
}
