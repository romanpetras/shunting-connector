package eu.circletouch.shuntingconn.repositories;

import eu.circletouch.shuntingconn.entities.TerminalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerminalRepository extends JpaRepository<TerminalEntity, Integer> {
}
