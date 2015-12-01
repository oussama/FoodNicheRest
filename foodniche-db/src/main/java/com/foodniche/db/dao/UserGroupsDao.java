package com.foodniche.db.dao;

import com.foodniche.db.entities.*;
import com.foodniche.db.repositories.UserGroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alexey Dubrov
 */

@Repository
public class UserGroupsDao extends BaseDao<UserGroups, UserGroupsPK> {

    @Autowired
    private UserGroupsRepository userGroupsRepository;

    @Override
    protected JpaRepository<UserGroups, UserGroupsPK> getRepository() {
        return userGroupsRepository;
    }

    /**
     * Adding user to group.
     * @param group group
     * @param user user
     */
    public void addUserToGroup(Groups group, Users user) {
        UserGroups userGroups = new UserGroups();
        userGroups.getUserGroupsPK().setGroup(group);
        userGroups.getUserGroupsPK().setUser(user);

        if (group.getApprovedmembership()) {
            userGroups.setStatus(UserGroupStatus.WAITING_APPROVAL);
        } else {
            userGroups.setStatus(UserGroupStatus.APPROVED);
        }

        save(userGroups);
    }

    /**
     * Updates user group status.
     * @param group group
     * @param user user
     * @param status status
     * @return true if user group exists
     */
    public boolean updateUserStatus(Groups group, Users user, UserGroupStatus status) {
        UserGroupsPK pk = new UserGroupsPK();
        pk.setUser(user);
        pk.setGroup(group);

        UserGroups userGroups = userGroupsRepository.getOne(pk);

        if (userGroups != null) {
            userGroups.setStatus(status);
            save(userGroups);
        }

        return userGroups != null;
    }
}
