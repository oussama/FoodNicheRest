package com.foodniche.db.repositories;

import com.foodniche.db.entities.Connections;
import com.foodniche.db.entities.ConnectionsPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alexey Dubrov
 *
 * Connections repository.
 */

public interface ConnectionsRepository extends JpaRepository<Connections, ConnectionsPK> {

}
