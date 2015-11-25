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
@Table(name = "coupontypes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coupontypes.findAll", query = "SELECT c FROM Coupontypes c"),
    @NamedQuery(name = "Coupontypes.findByCoupontypeid", query = "SELECT c FROM Coupontypes c WHERE c.coupontypeid = :coupontypeid"),
    @NamedQuery(name = "Coupontypes.findByDescription", query = "SELECT c FROM Coupontypes c WHERE c.description = :description")})
public class Coupontypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "coupontypeid")
    private Integer coupontypeid;
    @Size(max = 255)
    @Column(name = "description")
    private String description;

    public Coupontypes() {
    }

    public Coupontypes(Integer coupontypeid) {
        this.coupontypeid = coupontypeid;
    }

    public Integer getCoupontypeid() {
        return coupontypeid;
    }

    public void setCoupontypeid(Integer coupontypeid) {
        this.coupontypeid = coupontypeid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coupontypeid != null ? coupontypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coupontypes)) {
            return false;
        }
        Coupontypes other = (Coupontypes) object;
        if ((this.coupontypeid == null && other.coupontypeid != null) || (this.coupontypeid != null && !this.coupontypeid.equals(other.coupontypeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.foodnicherest.Coupontypes[ coupontypeid=" + coupontypeid + " ]";
    }
    
}
