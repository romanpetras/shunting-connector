package eu.circletouch.shuntingconn.repositories.impl;

import eu.circletouch.commons.beans.BrowseSorting;
import eu.circletouch.shuntingconn.entities.MissionEntity;
import eu.circletouch.shuntingconn.repositories.MissionCustomRepository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class MissionCustomRepositoryImpl extends AbstractBrowseCustomRepositoryImpl<MissionEntity> implements MissionCustomRepository {

    public MissionCustomRepositoryImpl(){
        this.setClazz(MissionEntity.class);
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
