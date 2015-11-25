/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodniche.db.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author User
 */
@Entity
@Table(name = "connections")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Connections.findAll", query = "SELECT c FROM Connections c"),
    @NamedQuery(name = "Connections.findByFromuserid", query = "SELECT c FROM Connections c WHERE c.connectionsPK.fromuserid = :fromuserid"),
    @NamedQuery(name = "Connections.findByTouserid", query = "SELECT c FROM Connections c WHERE c.connectionsPK.touserid = :touserid"),
    @NamedQuery(name = "Connections.findByStatus", query = "SELECT c FROM Connections c WHERE c.status = :status"),
    @NamedQuery(name = "Connections.findByCreateddate", query = "SELECT c FROM Connections c WHERE c.createddate = :createddate"),
    @NamedQuery(name = "Connections.findByApproveddate", query = "SELECT c FROM Connections c WHERE c.approveddate = :approveddate"),
    @NamedQuery(name = "Connections.findByDeleteddate", query = "SELECT c FROM Connections c WHERE c.deleteddate = :deleteddate")})
public class Connections implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConnectionsPK connectionsPK;
    @Column(name = "status")
    private Short status;
    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Column(name = "approveddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approveddate;
    @Column(name = "deleteddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleteddate;

    public Connections() {
    }

    public Connections(ConnectionsPK connectionsPK) {
        this.connectionsPK = connectionsPK;
    }

    public Connections(int fromuserid, int touserid) {
        this.connectionsPK = new ConnectionsPK(fromuserid, touserid);
    }

    public ConnectionsPK getConnectionsPK() {
        return connectionsPK;
    }

    public void setConnectionsPK(ConnectionsPK connectionsPK) {
        this.connectionsPK = connectionsPK;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public Date getApproveddate() {
        return approveddate;
    }

    public void setApproveddate(Date approveddate) {
        this.approveddate = approveddate;
    }

    public Date getDeleteddate() {
        return deleteddate;
    }

    public void setDeleteddate(Date deleteddate) {
        this.deleteddate = deleteddate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (connectionsPK != null ? connectionsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Connections)) {
            return false;
        }
        Connections other = (Connections) object;
        if ((this.connectionsPK == null && other.connectionsPK != null) || (this.connectionsPK != null && !this.connectionsPK.equals(other.connectionsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.foodnicherest.Connections[ connectionsPK=" + connectionsPK + " ]";
    }
    
}
