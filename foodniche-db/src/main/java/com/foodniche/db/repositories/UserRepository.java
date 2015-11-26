package com.foodniche.db.repositories;

import com.foodniche.db.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by agdubrov on 11/26/15.
 */

public interface UserRepository extends JpaRepository<Users, Integer> {
}
