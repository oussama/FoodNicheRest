package com.foodniche.db.repositories;

import com.foodniche.db.entities.Groups;
import com.foodniche.db.entities.UserGroups;
import com.foodniche.db.entities.UserGroupsPK;
import com.foodniche.db.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Alexey Dubrov
 */

public interface UserGroupsRepository extends JpaRepository<UserGroups, UserGroupsPK> {

    @Query("select ug.userGroupsPK.user from UserGroups ug where ug.userGroupsPK.group = :group")
    List<Users> getUsersInGroup(@Param("group") Groups group);

    @Query("select ug.userGroupsPK.group from UserGroups ug where ug.userGroupsPK.user = :user")
    List<Groups> getUserGroups(@Param("user") Users user);
}
