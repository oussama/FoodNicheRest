package com.foodniche.db.repositories;

import com.foodniche.db.entities.Businesstypes;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexey Dubrov
 *
 */

public interface BusinessTypesRepository extends JpaRepository<Businesstypes, Integer> {
}
