/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodnicherest;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "foodpreferences")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Foodpreferences.findAll", query = "SELECT f FROM Foodpreferences f"),
    @NamedQuery(name = "Foodpreferences.findByFoodpreferenceid", query = "SELECT f FROM Foodpreferences f WHERE f.foodpreferenceid = :foodpreferenceid"),
    @NamedQuery(name = "Foodpreferences.findByName", query = "SELECT f FROM Foodpreferences f WHERE f.name = :name")})
public class Foodpreferences implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "foodpreferenceid")
    private Integer foodpreferenceid;
    @Size(max = 80)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foodpreferences")
    private Collection<Userfoodpreferences> userfoodpreferencesCollection;

    public Foodpreferences() {
    }

    public Foodpreferences(Integer foodpreferenceid) {
        this.foodpreferenceid = foodpreferenceid;
    }

    public Integer getFoodpreferenceid() {
        return foodpreferenceid;
    }

    public void setFoodpreferenceid(Integer foodpreferenceid) {
        this.foodpreferenceid = foodpreferenceid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Userfoodpreferences> getUserfoodpreferencesCollection() {
        return userfoodpreferencesCollection;
    }

    public void setUserfoodpreferencesCollection(Collection<Userfoodpreferences> userfoodpreferencesCollection) {
        this.userfoodpreferencesCollection = userfoodpreferencesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (foodpreferenceid != null ? foodpreferenceid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Foodpreferences)) {
            return false;
        }
        Foodpreferences other = (Foodpreferences) object;
        if ((this.foodpreferenceid == null && other.foodpreferenceid != null) || (this.foodpreferenceid != null && !this.foodpreferenceid.equals(other.foodpreferenceid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.foodnicherest.Foodpreferences[ foodpreferenceid=" + foodpreferenceid + " ]";
    }
    
}
