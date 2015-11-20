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
public class UserfoodpreferencesPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "userid")
    private int userid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "foodpreferenceid")
    private int foodpreferenceid;

    public UserfoodpreferencesPK() {
    }

    public UserfoodpreferencesPK(int userid, int foodpreferenceid) {
        this.userid = userid;
        this.foodpreferenceid = foodpreferenceid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getFoodpreferenceid() {
        return foodpreferenceid;
    }

    public void setFoodpreferenceid(int foodpreferenceid) {
        this.foodpreferenceid = foodpreferenceid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userid;
        hash += (int) foodpreferenceid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserfoodpreferencesPK)) {
            return false;
        }
        UserfoodpreferencesPK other = (UserfoodpreferencesPK) object;
        if (this.userid != other.userid) {
            return false;
        }
        if (this.foodpreferenceid != other.foodpreferenceid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.foodnicherest.UserfoodpreferencesPK[ userid=" + userid + ", foodpreferenceid=" + foodpreferenceid + " ]";
    }
    
}
