package eu.circletouch.shuntingconn.repositories.impl;

import eu.circletouch.commons.beans.BrowseSorting;
import eu.circletouch.shuntigconn.beans.dt.SimulationRequestStatus;
import eu.circletouch.shuntingconn.entities.SimulationRequestEntity;
import eu.circletouch.shuntingconn.repositories.SimulationRequestCustomRepository;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SimulationRequestCustomRepositoryImpl extends AbstractBrowseCustomRepositoryImpl<SimulationRequestEntity> implements SimulationRequestCustomRepository {

    public SimulationRequestCustomRepositoryImpl(){
        this.setClazz(SimulationRequestEntity.class);
    }

    @Override
    Map<String, Object> composeBrowseParameters(Map<String, String> filter) throws ParseException {
        Map<String, Object> parameters = new HashMap<>();
        for (Object o : filter.entrySet()) {
            String key = (String) ((Map.Entry) o).getKey();
            String value = (String) ((Map.Entry) o).getValue();

            if (StringUtils.isEmpty(value)) {
                continue;
            }

            value = StringUtils.strip(value);

            switch (key) {
                case "id":
                    parameters.put("id", Integer.valueOf(value));
                    break;
                case "status":
                    parameters.put(key, SimulationRequestStatus.valueOf(value));
                    break;
                case "createdAtFrom":
                case "createdAtTo":
                case "updatedAtFrom":
                case "updatedAtTo":
                    parameters.put(key, Timestamp.valueOf(LocalDateTime.parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
                    break;
            }
        }
        return parameters;
    }

    @Override
    List<String> composeBrowseQueryElements(Map<String, String> filter) throws ParseException {
        List<String> queryElements = new LinkedList<>();
        for (Object o : filter.entrySet()) {
            String key = (String) ((Map.Entry) o).getKey();
            String value = (String) ((Map.Entry) o).getValue();

            if (StringUtils.isEmpty(value)) {
                continue;
            }

            switch (key) {
                case "id":
                case "status":
                    queryElements.add("entity." + key + " = :" + key);
                    break;
                case "createdAtFrom":
                    queryElements.add("(entity.createdAt >= :createdAtFrom or entity.createdAt is null)");
                    break;
                case "createdAtTo":
                    queryElements.add("(entity.createdAt <= :createdAtTo or entity.createdAt is null)");
                    break;
                case "updatedAtFrom":
                    queryElements.add("(entity.updatedAt >= :updatedAtFrom or entity.updatedAt is null)");
                    break;
                case "updatedAtTo":
                    queryElements.add("(entity.updatedAt <= :updatedAtTo or entity.updatedAt is null)");
                    break;
            }
        }
        return queryElements;
    }

    @Override
    String composeBrowseSorting(List<BrowseSorting> sortingList) {
        StringBuilder orderBy = new StringBuilder(" order by ");

        if (sortingList.size() == 0) {
            orderBy.append("entity.id ASC");
        }

        for (BrowseSorting sorting : sortingList) {
            switch (sorting.getColumn()) {
                case "id":
                    orderBy.append("entity.id");
                    break;
                case "status":
                    orderBy.append("entity.status");
                    break;
                case "createdAt":
                    orderBy.append("entity.createdAt");
                    break;
                case "updatedAt":
                    orderBy.append("entity.updatedAt");
                    break;
                default:
                    orderBy.append("entity.id");
                    break;
            }
            switch (sorting.getDirection()) {
                case ASC:
                    orderBy.append(" ASC");
                    break;
                case DESC:
                    orderBy.append(" DESC");
                    break;
                default:
                    orderBy.append(" ASC");
                    break;
            }
            orderBy.append(",");
        }

        return orderBy.toString().replaceAll(",$", "");
    }
}
