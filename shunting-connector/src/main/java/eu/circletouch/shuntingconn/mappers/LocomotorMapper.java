package eu.circletouch.shuntingconn.mappers;

import eu.circletouch.shuntigconn.beans.dt.Locomotor;
import eu.circletouch.shuntingconn.entities.LocomotorEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LocomotorMapper {
    LocomotorMapper INSTANCE = Mappers.getMapper(LocomotorMapper.class);

    @Mappings({})
    Locomotor entityToBean(LocomotorEntity entity);

    @InheritInverseConfiguration
    LocomotorEntity beanToEntity(Locomotor bean);
}
