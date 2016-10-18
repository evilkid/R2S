package tn.esprit.R2S.service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractService<T> {

    private final Class<T> entityClass;

    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public T edit(T entity) {
        return getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public T findSingleByNamedQuery(String namedQueryName, Class<T> classT) {
        try {
            return getEntityManager().createNamedQuery(namedQueryName, classT).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public T findSingleByNamedQuery(String namedQueryName, Map<String, Object> parameters, Class<T> classT) {
        Set<Entry<String, Object>> rawParameters = parameters.entrySet();
        TypedQuery<T> query = getEntityManager().createNamedQuery(namedQueryName, classT);
        rawParameters.forEach((entry) -> {
            query.setParameter(entry.getKey(), entry.getValue());
        });
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> findByNamedQuery(String namedQueryName) {
        return getEntityManager().createNamedQuery(namedQueryName).getResultList();
    }

    public List<T> findByNamedQuery(String namedQueryName, Map<String, Object> parameters) {
        Set<Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = getEntityManager().createNamedQuery(namedQueryName);
        rawParameters.forEach((entry) -> {
            query.setParameter(entry.getKey(), entry.getValue());
        });
        return query.getResultList();
    }
}
