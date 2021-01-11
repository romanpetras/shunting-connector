package eu.circletouch.shuntingconn.mappers;

import eu.circletouch.commons.utils.DateMapper;
import eu.circletouch.shuntigconn.beans.dt.SimulationRequest;
import eu.circletouch.shuntingconn.entities.SimulationRequestEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {MainManeuverMapper.class, MissionMapper.class, DateMapper.class})
public interface SimulationRequestMapper {
    SimulationRequestMapper INSTANCE = Mappers.getMapper(SimulationRequestMapper.class);

    @Mappings({})
    SimulationRequest entityToBean(SimulationRequestEntity entity);

    @InheritInverseConfiguration
    SimulationRequestEntity beanToEntity(SimulationRequest bean);
}
