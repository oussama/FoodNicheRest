package com.foodniche.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author Alexey Dubrov
 *
 * Base dao class with some common operations.
 */

@Transactional
public abstract class BaseDao<T, ID extends Serializable> {
    private Class<T> entityClass;

    @PersistenceContext
    private EntityManager em;

    private JpaRepository<T, ID> baseRepository;

    abstract protected JpaRepository<T, ID> getRepository();

    @PostConstruct
    public void init() {
        baseRepository = getRepository();

        Type mySuperclass = this.getClass().getGenericSuperclass();
        Type tType = ((ParameterizedType)mySuperclass).getActualTypeArguments()[0];

        try {
            entityClass = (Class<T>) Class.forName(tType.toString());
        } catch (ClassNotFoundException e) {
        }
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    public T save(T entity) {
        return baseRepository.save(entity);
    }

    public void delete(T entity) {
        baseRepository.delete(entity);
    }

    public T get(ID id) {
        return baseRepository.findOne(id);
    }

    public List<T> getAll() {
        return baseRepository.findAll();
    }

    /**
     * Execute named query with parameters
     * @param namedQuery named query
     * @param params parameters
     * @return list of entities
     */
    public List<T> executeNamedQuery(String namedQuery, Map<String, Object> params) {
        Query query = em.createNamedQuery(namedQuery, entityClass);

        params.forEach((name, value) -> {
            query.setParameter(name, value);
        });

        return query.getResultList();
    }
}
