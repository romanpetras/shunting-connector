package eu.circletouch.shuntingconn.mappers;

import eu.circletouch.commons.utils.DateMapper;
import eu.circletouch.shuntigconn.beans.dt.MainManeuver;
import eu.circletouch.shuntingconn.entities.MainManeuverEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {DateMapper.class,
        PointMapper.class,
        TerminalMapper.class,
        WagonTypeMapper.class
})
public interface MainManeuverMapper {
    MainManeuverMapper INSTANCE = Mappers.getMapper(MainManeuverMapper.class);

    @Mappings ({
            @Mapping(source = "simulationRequest.id",    target ="simulationRequestId" )
    })
    MainManeuver entityToBean(MainManeuverEntity entity);

    @Mappings ({
            @Mapping(source = "simulationRequestId",    target ="simulationRequest.id" ),
            @Mapping(source = "simulationRequestId",    target ="simulationRequestId" ),
            @Mapping(source = "wagonsType.id",    target ="wagonsTypeId" ),
            @Mapping(source = "wagonsType",    target ="wagonsType" ),
            @Mapping(source = "locomotorEndingPoint.id",    target ="locomotorEndingPointId" ),
            @Mapping(source = "locomotorEndingPoint",    target ="locomotorEndingPoint" ),
            @Mapping(source = "regressionPoint.id",    target ="regressionPointId" ),
            @Mapping(source = "maneuverParkPoint.id",    target ="maneuverParkPointId" ),
            @Mapping(source = "arrivalPoint.id",    target ="arrivalPointId" ),
            @Mapping(source = "terminal.id",    target ="terminalId"),
            @Mapping(source = "terminal",    target ="terminal")
    })
    MainManeuverEntity beanToEntity(MainManeuver bean);
}
