package eu.circletouch.shuntingconn.repositories;

import eu.circletouch.shuntingconn.entities.PointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRepository extends JpaRepository<PointEntity, Integer> {

    List<PointEntity> findAllByCodeContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String codeHint, String descriptionHint);

}
