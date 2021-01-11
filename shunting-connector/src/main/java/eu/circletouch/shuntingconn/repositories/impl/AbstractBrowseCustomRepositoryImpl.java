package eu.circletouch.shuntingconn.repositories.impl;

import eu.circletouch.commons.beans.BrowseRequest;
import eu.circletouch.commons.beans.BrowseSorting;
import eu.circletouch.shuntingconn.repositories.BrowseCustomRepository;
import org.apache.commons.lang.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class AbstractBrowseCustomRepositoryImpl<T> implements BrowseCustomRepository<T> {

    private Class<T> clazz;

    @PersistenceContext
    protected EntityManager em;

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String getBaseQueryList() {
        return "select entity from " + this.clazz.getSimpleName() +  " entity ";
    }

    @Override
    public String getBaseQueryCount() {
        return "select count(entity) from " + this.clazz.getSimpleName() + " entity ";
    }

    @Override
    public List<T> browseList(BrowseRequest browseRequest) throws IllegalArgumentException {
        try {
            Map<String, Object> parameters = composeBrowseParameters(browseRequest.getFilter());
            StringBuilder query = new StringBuilder(getBaseQueryList());

            buildQueryBasedOnBrowseRequest(browseRequest, query);
            query.append(composeBrowseSorting(browseRequest.getSortingList()));

            TypedQuery<T> typedQuery = em.createQuery(query.toString(), this.clazz);

            parameters.forEach(typedQuery::setParameter);

            return typedQuery
                    .setFirstResult(browseRequest.getOffset())
                    .setMaxResults(browseRequest.getLimit())
                    .getResultList();

        } catch (NoResultException e) {
            return new LinkedList<>();
        } catch (ParseException parseException){
            parseException.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Long browseCount(BrowseRequest browseRequest) {
        try {
            Map<String, Object> parameters = composeBrowseParameters(browseRequest.getFilter());
            StringBuilder query = new StringBuilder(getBaseQueryCount());

            buildQueryBasedOnBrowseRequest(browseRequest, query);

            TypedQuery<Long> typedQuery = em.createQuery(query.toString(), Long.class);
            parameters.forEach(typedQuery::setParameter);

            return typedQuery.getSingleResult();

        } catch (NoResultException e) {
            return 0L;
        } catch (ParseException parseException){
            parseException.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    private void buildQueryBasedOnBrowseRequest(BrowseRequest browseRequest, StringBuilder query) throws ParseException {
        List<String> queryElements;
        queryElements = composeBrowseQueryElements(browseRequest.getFilter());

        if (!queryElements.isEmpty()) {
            query.append(" where ");
            query.append(StringUtils.join(queryElements, " and "));
        }
    }

    abstract Map<String, Object> composeBrowseParameters(Map<String, String> filter) throws ParseException;

    abstract List<String> composeBrowseQueryElements(Map<String, String> filter) throws ParseException;

    abstract String composeBrowseSorting(List<BrowseSorting> sortingList);

}
