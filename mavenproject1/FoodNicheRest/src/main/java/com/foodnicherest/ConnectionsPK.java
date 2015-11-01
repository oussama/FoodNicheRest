/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodnicherest;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Embeddable
public class ConnectionsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "fromuserid")
    private int fromuserid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "touserid")
    private int touserid;

    public ConnectionsPK() {
    }

    public ConnectionsPK(int fromuserid, int touserid) {
        this.fromuserid = fromuserid;
        this.touserid = touserid;
    }

    public int getFromuserid() {
        return fromuserid;
    }

    public void setFromuserid(int fromuserid) {
        this.fromuserid = fromuserid;
    }

    public int getTouserid() {
        return touserid;
    }

    public void setTouserid(int touserid) {
        this.touserid = touserid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) fromuserid;
        hash += (int) touserid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConnectionsPK)) {
            return false;
        }
        ConnectionsPK other = (ConnectionsPK) object;
        if (this.fromuserid != other.fromuserid) {
            return false;
        }
        if (this.touserid != other.touserid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.foodnicherest.ConnectionsPK[ fromuserid=" + fromuserid + ", touserid=" + touserid + " ]";
    }
    
}
