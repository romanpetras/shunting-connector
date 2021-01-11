package eu.circletouch.shuntingconn.mappers;

import eu.circletouch.commons.utils.DateMapper;
import eu.circletouch.shuntigconn.beans.dt.Mission;
import eu.circletouch.shuntingconn.entities.MissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {DateMapper.class,
        PointMapper.class,
        TerminalMapper.class,
        MissionTaskMapper.class,
        LocomotorMapper.class
})
public interface MissionMapper {
    MissionMapper INSTANCE = Mappers.getMapper(MissionMapper.class);

    @Mappings({@Mapping(source = "simulationRequest.id", target = "simulationRequestId")})
    Mission entityToBean(MissionEntity entity);

    @Mappings({
            @Mapping(source = "simulationRequestId", target = "simulationRequestId"),
            @Mapping(source = "locomotor.id", target = "locomotorId"),
            @Mapping(source = "locomotorStartingPoint.id", target = "locomotorStartingPointId"),
            @Mapping(source = "trainStartingPoint.id", target = "trainStartingPointId"),
            @Mapping(source = "trainEndingPoint.id", target = "trainEndingPointId"),
            @Mapping(source = "wagonCutEndingPoint.id", target = "wagonCutEndingPointId"),
    })
    MissionEntity beanToEntity(Mission bean);
}
