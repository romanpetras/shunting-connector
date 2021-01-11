package eu.circletouch.shuntingconn.mappers;

import eu.circletouch.shuntigconn.beans.dt.WagonType;
import eu.circletouch.shuntingconn.entities.WagonTypeEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WagonTypeMapper {
    WagonTypeMapper INSTANCE = Mappers.getMapper(WagonTypeMapper.class);

    @Mappings({})
    WagonType entityToBean(WagonTypeEntity entity);

    @InheritInverseConfiguration
    WagonTypeEntity beanToEntity(WagonType bean);
}
