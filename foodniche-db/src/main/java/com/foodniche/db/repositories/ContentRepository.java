package com.foodniche.db.repositories;

import com.foodniche.db.entities.Content;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexey Dubrov
 */

public interface ContentRepository extends JpaRepository<Content, Integer> {
}
