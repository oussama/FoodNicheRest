package com.foodniche.db.repositories;

import com.foodniche.db.entities.Contenttypes;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexey Dubrov
 */

public interface ContentTypeRepository extends JpaRepository<Contenttypes, Integer> {
    Contenttypes findByName(String name);
}
