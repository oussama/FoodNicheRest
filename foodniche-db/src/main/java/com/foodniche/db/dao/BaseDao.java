package com.foodniche.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * @author Alexey Dubrov
 *
 * Base dao class with some common operations.
 */

@Transactional
public abstract class BaseDao<T> {
    @PersistenceContext
    private EntityManager em;

    private JpaRepository<T, Integer> baseRepository;

    abstract protected JpaRepository<T, Integer> getRepository();

    @PostConstruct
    public void init() {
        baseRepository = getRepository();
    }

    public T save(T entity) {
        return baseRepository.save(entity);
    }

    public void delete(T entity) {
        baseRepository.delete(entity);
    }

    public T get(Integer id) {
        return baseRepository.findOne(id);
    }
}
