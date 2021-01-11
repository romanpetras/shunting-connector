package eu.circletouch.shuntingconn.repositories;

import eu.circletouch.shuntingconn.entities.MissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<MissionEntity, Integer>, MissionCustomRepository {
}
