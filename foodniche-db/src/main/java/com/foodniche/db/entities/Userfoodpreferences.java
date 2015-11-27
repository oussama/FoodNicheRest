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

/**
 *
 * @author User
 */
@Entity
@Table(name = "userfoodpreferences")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userfoodpreferences.findAll", query = "SELECT u FROM Userfoodpreferences u"),
    @NamedQuery(name = "Userfoodpreferences.findByUserid", query = "SELECT u FROM Userfoodpreferences u WHERE u.userfoodpreferencesPK.userid = :userid"),
    @NamedQuery(name = "Userfoodpreferences.findByFoodpreferenceid", query = "SELECT u FROM Userfoodpreferences u WHERE u.userfoodpreferencesPK.foodpreferenceid = :foodpreferenceid"),
    @NamedQuery(name = "Userfoodpreferences.findByDescription", query = "SELECT u FROM Userfoodpreferences u WHERE u.description = :description")})
public class Userfoodpreferences implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserfoodpreferencesPK userfoodpreferencesPK;
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;

    public Userfoodpreferences() {
    }

    public Userfoodpreferences(UserfoodpreferencesPK userfoodpreferencesPK) {
        this.userfoodpreferencesPK = userfoodpreferencesPK;
    }

    public Userfoodpreferences(int userid, int foodpreferenceid) {
        this.userfoodpreferencesPK = new UserfoodpreferencesPK(userid, foodpreferenceid);
    }

    public UserfoodpreferencesPK getUserfoodpreferencesPK() {
        return userfoodpreferencesPK;
    }

    public void setUserfoodpreferencesPK(UserfoodpreferencesPK userfoodpreferencesPK) {
        this.userfoodpreferencesPK = userfoodpreferencesPK;
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
        hash += (userfoodpreferencesPK != null ? userfoodpreferencesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userfoodpreferences)) {
            return false;
        }
        Userfoodpreferences other = (Userfoodpreferences) object;
        if ((this.userfoodpreferencesPK == null && other.userfoodpreferencesPK != null) || (this.userfoodpreferencesPK != null && !this.userfoodpreferencesPK.equals(other.userfoodpreferencesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.foodnicherest.Userfoodpreferences[ userfoodpreferencesPK=" + userfoodpreferencesPK + " ]";
    }
    
}
