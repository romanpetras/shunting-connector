package eu.circletouch.shuntingconn.services;

import eu.circletouch.shuntigconn.beans.dt.Point;
import eu.circletouch.shuntingconn.entities.PointEntity;
import eu.circletouch.shuntingconn.exceptions.CustomException;
import eu.circletouch.shuntingconn.mappers.PointMapper;
import eu.circletouch.shuntingconn.repositories.PointRepository;
import eu.circletouch.users.beans.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PointService {
    private final PointRepository pointRepository;
    private final PointMapper pointMapper = PointMapper.INSTANCE;

    public Point create(Point point, User user){
        PointEntity pointEntity = pointMapper.beanToEntity(point);
        pointEntity.setCreatedById(user.getId());
        pointEntity.setCreatedByUser(user.getUsername());
        return pointMapper.entityToBean(pointRepository.save(pointEntity));
    }

    public Point update(Point point, User user) {
        PointEntity existingEntity = pointRepository.findById(point.getId())
                .orElseThrow(() -> new CustomException("Point with id " + point.getId() + " not found! ", HttpStatus.NOT_FOUND));

        PointEntity pointEntity = pointMapper.beanToEntity(point);
        pointEntity.setCreatedById(existingEntity.getCreatedById());
        pointEntity.setCreatedByUser(existingEntity.getCreatedByUser());
        pointEntity.setCreatedAt(existingEntity.getCreatedAt());
        pointEntity.setUpdatedById(user.getId());
        pointEntity.setUpdatedByUser(user.getUsername());

        return pointMapper.entityToBean(pointRepository.save(pointEntity));
    }

    public Point get(Integer id, User user){
        return pointRepository.findById(id)
                .map(pointMapper::entityToBean)
                .orElseThrow(() -> new CustomException("Point with id " + id + " not found! ", HttpStatus.NOT_FOUND));
    }

    public List<Point> getAll(User req) {
        return pointRepository.findAll().stream()
                .map(pointMapper::entityToBean)
                .collect(Collectors.toList());
    }

    public List<Point> search(String hint, User req) {
        return pointRepository.findAllByCodeContainingIgnoreCaseOrDescriptionContainingIgnoreCase(hint, hint).stream()
                .map(pointMapper::entityToBean)
                .collect(Collectors.toList());
    }
}
