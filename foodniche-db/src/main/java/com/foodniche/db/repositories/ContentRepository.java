package com.foodniche.db.repositories;

import com.foodniche.db.entities.Content;
import com.foodniche.db.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alexey Dubrov
 */

public interface ContentRepository extends JpaRepository<Content, Integer> {

    List<Content> findByProductAndContenttypeid(Product product, Integer contentTypeId);
}
