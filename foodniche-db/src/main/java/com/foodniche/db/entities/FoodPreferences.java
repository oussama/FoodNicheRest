/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodniche.db.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "foodpreferences")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Foodpreferences.findAll", query = "SELECT f FROM FoodPreferences f"),
    @NamedQuery(name = "Foodpreferences.findByFoodpreferenceid", query = "SELECT f FROM FoodPreferences f WHERE f.foodpreferenceid = :foodpreferenceid"),
    @NamedQuery(name = "Foodpreferences.findByName", query = "SELECT f FROM FoodPreferences f WHERE f.name = :name")})
public class FoodPreferences implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "foodpreferenceid")
    private Integer foodpreferenceid;
    @Size(max = 80)
    @Column(name = "name")
    private String name;

    public FoodPreferences() {
    }

    public FoodPreferences(Integer foodpreferenceid) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (foodpreferenceid != null ? foodpreferenceid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FoodPreferences)) {
            return false;
        }
        FoodPreferences other = (FoodPreferences) object;
        if ((this.foodpreferenceid == null && other.foodpreferenceid != null) || (this.foodpreferenceid != null && !this.foodpreferenceid.equals(other.foodpreferenceid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.foodnicherest.FoodPreferences[ foodpreferenceid=" + foodpreferenceid + " ]";
    }
    
}
