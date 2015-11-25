/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodniche.db.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author User
 */
@Entity
@Table(name = "subcsriptiontypes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subcsriptiontypes.findAll", query = "SELECT s FROM Subcsriptiontypes s"),
    @NamedQuery(name = "Subcsriptiontypes.findBySubscriptiontypeid", query = "SELECT s FROM Subcsriptiontypes s WHERE s.subscriptiontypeid = :subscriptiontypeid"),
    @NamedQuery(name = "Subcsriptiontypes.findByName", query = "SELECT s FROM Subcsriptiontypes s WHERE s.name = :name")})
public class Subcsriptiontypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "subscriptiontypeid")
    private Integer subscriptiontypeid;
    @Size(max = 80)
    @Column(name = "name")
    private String name;

    public Subcsriptiontypes() {
    }

    public Subcsriptiontypes(Integer subscriptiontypeid) {
        this.subscriptiontypeid = subscriptiontypeid;
    }

    public Integer getSubscriptiontypeid() {
        return subscriptiontypeid;
    }

    public void setSubscriptiontypeid(Integer subscriptiontypeid) {
        this.subscriptiontypeid = subscriptiontypeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subscriptiontypeid != null ? subscriptiontypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subcsriptiontypes)) {
            return false;
        }
        Subcsriptiontypes other = (Subcsriptiontypes) object;
        if ((this.subscriptiontypeid == null && other.subscriptiontypeid != null) || (this.subscriptiontypeid != null && !this.subscriptiontypeid.equals(other.subscriptiontypeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.foodnicherest.Subcsriptiontypes[ subscriptiontypeid=" + subscriptiontypeid + " ]";
    }
    
}
