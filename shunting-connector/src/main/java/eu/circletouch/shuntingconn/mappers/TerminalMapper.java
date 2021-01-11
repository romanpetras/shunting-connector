package eu.circletouch.shuntingconn.mappers;

import eu.circletouch.shuntigconn.beans.dt.Terminal;
import eu.circletouch.shuntingconn.entities.TerminalEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TerminalMapper {
    TerminalMapper INSTANCE = Mappers.getMapper(TerminalMapper.class);

    @Mappings({})
    Terminal entityToBean(TerminalEntity entity);

    @InheritInverseConfiguration
    TerminalEntity beanToEntity(Terminal bean);
}
