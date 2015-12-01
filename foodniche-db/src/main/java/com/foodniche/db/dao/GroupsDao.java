package com.foodniche.db.dao;

import com.foodniche.db.entities.Groups;
import com.foodniche.db.repositories.GroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alexey Dubrov
 */

@Repository
public class GroupsDao extends BaseDao<Groups, Integer> {

    @Autowired
    private GroupsRepository groupsRepository;

    @Override
    protected JpaRepository<Groups, Integer> getRepository() {
        return groupsRepository;
    }
}
