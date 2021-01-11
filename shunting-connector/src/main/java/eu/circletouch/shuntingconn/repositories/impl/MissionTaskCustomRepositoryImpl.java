package eu.circletouch.shuntingconn.repositories.impl;

import eu.circletouch.commons.beans.BrowseSorting;
import eu.circletouch.shuntingconn.entities.MissionTaskEntity;
import eu.circletouch.shuntingconn.repositories.MissionTaskCustomRepository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class MissionTaskCustomRepositoryImpl extends AbstractBrowseCustomRepositoryImpl<MissionTaskEntity> implements MissionTaskCustomRepository {

    public MissionTaskCustomRepositoryImpl(){
        this.setClazz(MissionTaskEntity.class);
    }

    @Override
    Map<String, Object> composeBrowseParameters(Map<String, String> filter) throws ParseException {
        return null;
    }

    @Override
    List<String> composeBrowseQueryElements(Map<String, String> filter) throws ParseException {
        return null;
    }

    @Override
    String composeBrowseSorting(List<BrowseSorting> sortingList) {
        return null;
    }
}
