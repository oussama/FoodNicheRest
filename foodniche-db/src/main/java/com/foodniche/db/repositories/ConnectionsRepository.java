package com.foodniche.db.repositories;

import com.foodniche.db.entities.Connections;
import com.foodniche.db.entities.ConnectionsPK;
import com.foodniche.db.entities.UserGroups;
import com.foodniche.db.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Alexey Dubrov
 *
 * Connections repository.
 */

public interface ConnectionsRepository extends JpaRepository<Connections, ConnectionsPK> {

    @Query("select cn from Connections cn where cn.connectionsPK.fromUser = :fromUser and cn.connectionsPK.toUser = :toUser")
    Connections getUsersConnection(@Param("fromUser") Users fromUser, @Param("toUser") Users toUser);
}
