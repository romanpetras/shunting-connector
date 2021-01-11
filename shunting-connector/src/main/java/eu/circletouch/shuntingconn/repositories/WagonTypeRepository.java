package eu.circletouch.shuntingconn.repositories;

import eu.circletouch.shuntingconn.entities.WagonTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WagonTypeRepository extends JpaRepository<WagonTypeEntity, Integer> {
}
