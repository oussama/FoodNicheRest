package com.foodniche.db.dao;

import com.foodniche.db.entities.*;
import com.foodniche.db.repositories.GroupsRepository;
import com.foodniche.db.repositories.UserGroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alexey Dubrov
 */

@Repository
public class UserGroupsDao extends BaseDao<UserGroups, UserGroupsPK> {

    @Autowired
    private UserGroupsRepository userGroupsRepository;

    @Autowired
    private GroupsRepository groupsRepository;

    @Override
    protected JpaRepository<UserGroups, UserGroupsPK> getRepository() {
        return userGroupsRepository;
    }

    /**
     * List of users added to group.
     * @param groupId group id
     * @return list of users
     */
    public List<Users> getUsersInGroup(Integer groupId) {

        Groups group = groupsRepository.getOne(groupId);

        if (group != null) {
            return userGroupsRepository.getUsersInGroup(group);
        } else {
            return null;
        }
    }

    /**
     * List of user groups.
     * @param user user
     * @return list of groups
     */
    public List<Groups> getUserGroups(Users user) {
        return userGroupsRepository.getUserGroups(user);
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
