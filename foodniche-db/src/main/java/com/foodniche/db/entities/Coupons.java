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
import java.util.Date;

/**
 *
 * @author User
 */
@Entity
@Table(name = "coupons")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coupons.findAll", query = "SELECT c FROM Coupons c"),
    @NamedQuery(name = "Coupons.findByCouponid", query = "SELECT c FROM Coupons c WHERE c.couponid = :couponid"),
    @NamedQuery(name = "Coupons.findByBusinessid", query = "SELECT c FROM Coupons c WHERE c.businessid = :businessid"),
    @NamedQuery(name = "Coupons.findByCoupontypeid", query = "SELECT c FROM Coupons c WHERE c.coupontypeid = :coupontypeid"),
    @NamedQuery(name = "Coupons.findByName", query = "SELECT c FROM Coupons c WHERE c.name = :name"),
    @NamedQuery(name = "Coupons.findByDescription", query = "SELECT c FROM Coupons c WHERE c.description = :description"),
    @NamedQuery(name = "Coupons.findByValidfrom", query = "SELECT c FROM Coupons c WHERE c.validfrom = :validfrom"),
    @NamedQuery(name = "Coupons.findByValidto", query = "SELECT c FROM Coupons c WHERE c.validto = :validto")})
public class Coupons implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "couponid")
    private Integer couponid;
    @Column(name = "businessid")
    private Integer businessid;
    @Column(name = "coupontypeid")
    private Integer coupontypeid;
    @Size(max = 80)
    @Column(name = "name")
    private String name;
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    @Column(name = "validfrom")
    @Temporal(TemporalType.DATE)
    private Date validfrom;
    @Column(name = "validto")
    @Temporal(TemporalType.DATE)
    private Date validto;

    public Coupons() {
    }

    public Coupons(Integer couponid) {
        this.couponid = couponid;
    }

    public Integer getCouponid() {
        return couponid;
    }

    public void setCouponid(Integer couponid) {
        this.couponid = couponid;
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public Integer getCoupontypeid() {
        return coupontypeid;
    }

    public void setCoupontypeid(Integer coupontypeid) {
        this.coupontypeid = coupontypeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getValidfrom() {
        return validfrom;
    }

    public void setValidfrom(Date validfrom) {
        this.validfrom = validfrom;
    }

    public Date getValidto() {
        return validto;
    }

    public void setValidto(Date validto) {
        this.validto = validto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (couponid != null ? couponid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coupons)) {
            return false;
        }
        Coupons other = (Coupons) object;
        if ((this.couponid == null && other.couponid != null) || (this.couponid != null && !this.couponid.equals(other.couponid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.foodnicherest.Coupons[ couponid=" + couponid + " ]";
    }
    
}
