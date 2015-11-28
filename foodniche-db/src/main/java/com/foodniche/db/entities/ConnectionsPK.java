/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodniche.db.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author User
 */
@Embeddable
public class ConnectionsPK implements Serializable {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fromuserid")
    private Users fromUser;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "touserid")
    private Users toUser;

    public ConnectionsPK() {
    }

    public Users getFromUser() {
        return fromUser;
    }

    public void setFromUser(Users fromUser) {
        this.fromUser = fromUser;
    }

    public Users getToUser() {
        return toUser;
    }

    public void setToUser(Users toUser) {
        this.toUser = toUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConnectionsPK that = (ConnectionsPK) o;

        if (!fromUser.equals(that.fromUser)) return false;
        return toUser.equals(that.toUser);

    }

    @Override
    public int hashCode() {
        int result = fromUser.hashCode();
        result = 31 * result + toUser.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ConnectionsPK{" +
                "fromUser=" + fromUser +
                ", toUser=" + toUser +
                '}';
    }
}
