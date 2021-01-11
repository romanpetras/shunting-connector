package eu.circletouch.shuntingconn.services;

import eu.circletouch.shuntigconn.beans.dt.Locomotor;
import eu.circletouch.shuntingconn.entities.LocomotorEntity;
import eu.circletouch.shuntingconn.exceptions.CustomException;
import eu.circletouch.shuntingconn.mappers.LocomotorMapper;
import eu.circletouch.shuntingconn.repositories.LocomotorRepository;
import eu.circletouch.users.beans.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocomotorService {

    private final LocomotorRepository locomotorRepository;
    private final LocomotorMapper locomotorMapper = LocomotorMapper.INSTANCE;

    public Locomotor create(Locomotor locomotor, User user){
        LocomotorEntity locomotorEntity = locomotorMapper.beanToEntity(locomotor);
        locomotorEntity.setCreatedById(user.getId());
        locomotorEntity.setCreatedByUser(user.getUsername());
        return locomotorMapper.entityToBean(locomotorRepository.save(locomotorEntity));
    }

    public Locomotor update(Locomotor locomotor, User user) {
        LocomotorEntity existingEntity = locomotorRepository.findById(locomotor.getId())
                .orElseThrow(() -> new CustomException("Locomotor with id " + locomotor.getId() + " not found! ", HttpStatus.NOT_FOUND));

        LocomotorEntity locomotorEntity = locomotorMapper.beanToEntity(locomotor);
        locomotorEntity.setCreatedById(existingEntity.getCreatedById());
        locomotorEntity.setCreatedByUser(existingEntity.getCreatedByUser());
        locomotorEntity.setCreatedAt(existingEntity.getCreatedAt());
        locomotorEntity.setUpdatedById(user.getId());
        locomotorEntity.setUpdatedByUser(user.getUsername());

        return locomotorMapper.entityToBean(locomotorRepository.save(locomotorEntity));
    }

    public Locomotor get(Integer id, User user){
        return locomotorRepository.findById(id)
                .map(locomotorMapper::entityToBean)
                .orElseThrow(() -> new CustomException("Locomotor with id " + id + " not found! ", HttpStatus.NOT_FOUND));
    }

    public List<Locomotor> search(String hint, User req) {
        return locomotorRepository.findAllByCodeContainingIgnoreCaseOrDescriptionContainingIgnoreCase(hint, hint).stream()
                .map(locomotorMapper::entityToBean)
                .collect(Collectors.toList());
    }
}
