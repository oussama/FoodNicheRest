package com.foodniche.db.repositories;

import com.foodniche.db.entities.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexey Dubrov
 */

public interface GroupsRepository extends JpaRepository<Groups, Integer> {
}
