package com.foodniche.db.dao;

import com.foodniche.db.entities.Businesstypes;
import com.foodniche.db.repositories.BusinessTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alexey Dubrov
 *
 * Business type dao.
 */

@Repository
public class BusinessTypesDao extends BaseDao<Businesstypes, Integer> {

    @Autowired
    private BusinessTypesRepository businessTypesRepository;

    @Override
    protected JpaRepository<Businesstypes, Integer> getRepository() {
        return businessTypesRepository;
    }

}
