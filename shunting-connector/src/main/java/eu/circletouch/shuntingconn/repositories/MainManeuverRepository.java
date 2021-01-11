package eu.circletouch.shuntingconn.repositories;

import eu.circletouch.shuntingconn.entities.MainManeuverEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainManeuverRepository extends JpaRepository<MainManeuverEntity, Integer>, MainManeuverCustomRepository {
}
