package com.foodniche.db.repositories;

import com.foodniche.db.entities.Businesses;
import com.foodniche.db.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alexey Dubrov
 */

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByBusinesses(Businesses businesses);
}
