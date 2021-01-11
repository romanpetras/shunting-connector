package eu.circletouch.shuntingconn.services;

import eu.circletouch.shuntigconn.beans.dt.Terminal;
import eu.circletouch.shuntingconn.entities.TerminalEntity;
import eu.circletouch.shuntingconn.exceptions.CustomException;
import eu.circletouch.shuntingconn.mappers.TerminalMapper;
import eu.circletouch.shuntingconn.repositories.TerminalRepository;
import eu.circletouch.users.beans.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TerminalService {
    private final TerminalRepository terminalRepository;
    private final TerminalMapper terminalMapper = TerminalMapper.INSTANCE;

    public Terminal create(Terminal terminal, User user){
        TerminalEntity terminalEntity = terminalMapper.beanToEntity(terminal);
        terminalEntity.setCreatedById(user.getId());
        terminalEntity.setCreatedByUser(user.getUsername());
        return terminalMapper.entityToBean(terminalRepository.save(terminalEntity));
    }

    public Terminal update(Terminal terminal, User user) {
        TerminalEntity existingEntity = terminalRepository.findById(terminal.getId())
                .orElseThrow(() -> new CustomException("Terminal with id " + terminal.getId() + " not found! ", HttpStatus.NOT_FOUND));

        TerminalEntity terminalEntity = terminalMapper.beanToEntity(terminal);
        terminalEntity.setCreatedById(existingEntity.getCreatedById());
        terminalEntity.setCreatedByUser(existingEntity.getCreatedByUser());
        terminalEntity.setCreatedAt(existingEntity.getCreatedAt());
        terminalEntity.setUpdatedById(user.getId());
        terminalEntity.setUpdatedByUser(user.getUsername());

        return terminalMapper.entityToBean(terminalRepository.save(terminalEntity));
    }

    public Terminal get(Integer id, User user){
        return terminalRepository.findById(id)
                .map(terminalMapper::entityToBean)
                .orElseThrow(() -> new CustomException("Terminal with id " + id + " not found! ", HttpStatus.NOT_FOUND));
    }

    public List<Terminal> getAll(User user){
        return terminalRepository.findAll().stream()
                .map(terminalMapper::entityToBean)
                .collect(Collectors.toList());
    }
}
