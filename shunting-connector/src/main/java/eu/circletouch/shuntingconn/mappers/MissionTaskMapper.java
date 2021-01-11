package eu.circletouch.shuntingconn.mappers;

import eu.circletouch.shuntigconn.beans.dt.MissionTask;
import eu.circletouch.shuntingconn.entities.MissionTaskEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses =
        {PointMapper.class})
public interface MissionTaskMapper {
    MissionTaskMapper INSTANCE = Mappers.getMapper(MissionTaskMapper.class);

    @Mappings({@Mapping(source = "mainManeuver.id", target = "mainManeuverId"),
            @Mapping(source = "mission.id", target = "missionId")})
    MissionTask entityToBean(MissionTaskEntity entity);

    @Mappings({
            @Mapping(source = "departurePoint.id", target = "departurePointId"),
            @Mapping(source = "departurePoint", target = "departurePoint"),
            @Mapping(source = "arrivalPoint.id", target = "arrivalPointId"),
            @Mapping(source = "arrivalPoint", target = "arrivalPoint"),
    })
    MissionTaskEntity beanToEntity(MissionTask bean);
}
