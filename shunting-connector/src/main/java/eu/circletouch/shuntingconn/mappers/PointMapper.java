package eu.circletouch.shuntingconn.mappers;

import eu.circletouch.shuntigconn.beans.dt.Point;
import eu.circletouch.shuntingconn.entities.PointEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PointMapper {
    PointMapper INSTANCE = Mappers.getMapper(PointMapper.class);

    @Mappings({})
    Point entityToBean(PointEntity entity);

    @InheritInverseConfiguration
    PointEntity beanToEntity(Point bean);
}
