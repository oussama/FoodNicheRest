package com.foodniche.db.repositories;

import com.foodniche.db.entities.Businesses;
import com.foodniche.db.entities.Businesstypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alexey Dubrov
 *
 *
 */

public interface BusinessRepository extends JpaRepository<Businesses, Integer> {

    List<Businesses> findByBusinessTypeAndZipCode(Businesstypes businesstype, String zipCode);
}
