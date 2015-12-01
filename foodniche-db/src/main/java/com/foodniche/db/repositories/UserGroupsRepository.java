package com.foodniche.db.repositories;

import com.foodniche.db.entities.UserGroups;
import com.foodniche.db.entities.UserGroupsPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexey Dubrov
 */

public interface UserGroupsRepository extends JpaRepository<UserGroups, UserGroupsPK> {
}
