package eu.circletouch.shuntingconn.repositories;

import eu.circletouch.shuntingconn.entities.MissionTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionTaskRepository extends JpaRepository<MissionTaskEntity, Integer>, MissionTaskCustomRepository {
}
