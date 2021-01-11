package eu.circletouch.shuntingconn.repositories;

import eu.circletouch.shuntingconn.entities.LocomotorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocomotorRepository extends JpaRepository<LocomotorEntity, Integer> {
    List<LocomotorEntity> findAllByCodeContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String codeHint, String descriptionHint);
}
