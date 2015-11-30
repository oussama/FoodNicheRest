package com.foodniche.db.dao;

import com.foodniche.db.entities.Connections;
import com.foodniche.db.entities.ConnectionsPK;
import com.foodniche.db.entities.Users;
import com.foodniche.db.repositories.ConnectionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Alexey Dubrov
 *
 * Connections Dao.
 */

@Repository
public class ConnectionsDao extends BaseDao<Connections, ConnectionsPK> {

    @Autowired
    private ConnectionsRepository connectionsRepository;

    /**
     * Getting connected users.
     * @param user
     * @return list of connected users
     */
    public List<Users> getConnections(Users user) {

        Map<String, Object> params = new HashMap<>();
        List<Connections> connectionsList = executeNamedQuery("Сonnections.findByFromuserid", params);

        return connectionsList.stream().map((collection) -> {
            return collection.getConnectionsPK().getToUser();
        }).collect(Collectors.toList());
    }

    @Override
    protected JpaRepository<Connections, ConnectionsPK> getRepository() {
        return connectionsRepository;
    }
}
