package eu.circletouch.shuntingconn.repositories.impl;

import eu.circletouch.commons.beans.BrowseSorting;
import eu.circletouch.shuntingconn.entities.MainManeuverEntity;
import eu.circletouch.shuntingconn.repositories.MainManeuverCustomRepository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class MainManeuverCustomRepositoryImpl extends AbstractBrowseCustomRepositoryImpl<MainManeuverEntity> implements MainManeuverCustomRepository {

    public MainManeuverCustomRepositoryImpl(){
        this.setClazz(MainManeuverEntity.class);
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
