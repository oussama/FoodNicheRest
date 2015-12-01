package com.foodniche.db.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Alexey Dubrov
 *
 *
 */

@Entity
@Table(name = "usergroups")
public class UserGroups implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UserGroupsPK userGroupsPK;

    @Enumerated(EnumType.ORDINAL)
    private UserGroupStatus status;

    public UserGroupsPK getUserGroupsPK() {
        if (userGroupsPK == null) {
            userGroupsPK = new UserGroupsPK();
        }
        return userGroupsPK;
    }

    public void setUserGroupsPK(UserGroupsPK userGroupsPK) {
        this.userGroupsPK = userGroupsPK;
    }

    public UserGroupStatus getStatus() {
        return status;
    }

    public void setStatus(UserGroupStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserGroups that = (UserGroups) o;

        if (userGroupsPK != null ? !userGroupsPK.equals(that.userGroupsPK) : that.userGroupsPK != null) return false;
        return status == that.status;

    }

    @Override
    public int hashCode() {
        int result = userGroupsPK != null ? userGroupsPK.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserGroups{" +
                "userGroupsPK=" + userGroupsPK +
                ", status=" + status +
                '}';
    }
}
