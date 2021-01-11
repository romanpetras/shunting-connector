package eu.circletouch.shuntingconn.repositories;

import eu.circletouch.commons.beans.BrowseRequest;

import java.util.List;

public interface BrowseCustomRepository<T> {
    String getBaseQueryList();
    String getBaseQueryCount();
    List<T> browseList(BrowseRequest browseRequest) throws IllegalArgumentException;
    Long browseCount(BrowseRequest browseRequest) throws IllegalArgumentException;
}
