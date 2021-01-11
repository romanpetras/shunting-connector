package eu.circletouch.shuntingconn.services;

import eu.circletouch.shuntigconn.beans.dt.WagonType;
import eu.circletouch.shuntingconn.entities.WagonTypeEntity;
import eu.circletouch.shuntingconn.exceptions.CustomException;
import eu.circletouch.shuntingconn.mappers.WagonTypeMapper;
import eu.circletouch.shuntingconn.repositories.WagonTypeRepository;
import eu.circletouch.users.beans.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WagonTypeService {
    private final WagonTypeRepository wagonTypeRepository;
    private final WagonTypeMapper wagonTypeMapper = WagonTypeMapper.INSTANCE;

    public WagonType create(WagonType wagonType, User user){
        WagonTypeEntity wagonTypeEntity = wagonTypeMapper.beanToEntity(wagonType);
        wagonTypeEntity.setCreatedById(user.getId());
        wagonTypeEntity.setCreatedByUser(user.getUsername());
        return wagonTypeMapper.entityToBean(wagonTypeRepository.save(wagonTypeEntity));
    }

    public WagonType update(WagonType wagonType, User user) {
        WagonTypeEntity existingEntity = wagonTypeRepository.findById(wagonType.getId())
                .orElseThrow(() -> new CustomException("WagonType with id " + wagonType.getId() + " not found! ", HttpStatus.NOT_FOUND));

        WagonTypeEntity wagonTypeEntity = wagonTypeMapper.beanToEntity(wagonType);
        wagonTypeEntity.setCreatedById(existingEntity.getCreatedById());
        wagonTypeEntity.setCreatedByUser(existingEntity.getCreatedByUser());
        wagonTypeEntity.setCreatedAt(existingEntity.getCreatedAt());
        wagonTypeEntity.setUpdatedById(user.getId());
        wagonTypeEntity.setUpdatedByUser(user.getUsername());

        return wagonTypeMapper.entityToBean(wagonTypeRepository.save(wagonTypeEntity));
    }

    public WagonType get(Integer id, User user){
        return wagonTypeRepository.findById(id)
                .map(wagonTypeMapper::entityToBean)
                .orElseThrow(() -> new CustomException("WagonType with id " + id + " not found! ", HttpStatus.NOT_FOUND));
    }

    public List<WagonType> getAll(User user){
        return wagonTypeRepository.findAll().stream()
                .map(wagonTypeMapper::entityToBean)
                .collect(Collectors.toList());
    }
}
