package com.foodniche.db.dao;

import com.foodniche.db.entities.Businesses;
import com.foodniche.db.entities.Businesstypes;
import com.foodniche.db.repositories.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alexey Dubrov
 *
 * Business dao.
 */

@Repository
public class BusinessDao extends BaseDao<Businesses, Integer> {

    @Autowired
    private BusinessRepository businessRepository;

    @Override
    protected JpaRepository<Businesses, Integer> getRepository() {
        return businessRepository;
    }

    public List<Businesses> getNearestRestaurants(Integer typeId, String code) {
        Businesstypes bTypes = new Businesstypes();
        bTypes.setBusinesstypeid(typeId);

        return businessRepository.findByBusinessTypeAndZipCode(bTypes, code);
    }
}
