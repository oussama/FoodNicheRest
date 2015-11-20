/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodnicherest;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "coupondownloads")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coupondownloads.findAll", query = "SELECT c FROM Coupondownloads c"),
    @NamedQuery(name = "Coupondownloads.findByDownloadid", query = "SELECT c FROM Coupondownloads c WHERE c.downloadid = :downloadid"),
    @NamedQuery(name = "Coupondownloads.findByCouponid", query = "SELECT c FROM Coupondownloads c WHERE c.couponid = :couponid"),
    @NamedQuery(name = "Coupondownloads.findByUserid", query = "SELECT c FROM Coupondownloads c WHERE c.userid = :userid"),
    @NamedQuery(name = "Coupondownloads.findByAmount", query = "SELECT c FROM Coupondownloads c WHERE c.amount = :amount"),
    @NamedQuery(name = "Coupondownloads.findByDownloaddate", query = "SELECT c FROM Coupondownloads c WHERE c.downloaddate = :downloaddate")})
public class Coupondownloads implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "downloadid")
    private Integer downloadid;
    @Column(name = "couponid")
    private Integer couponid;
    @Column(name = "userid")
    private Integer userid;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "downloaddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date downloaddate;

    public Coupondownloads() {
    }

    public Coupondownloads(Integer downloadid) {
        this.downloadid = downloadid;
    }

    public Integer getDownloadid() {
        return downloadid;
    }

    public void setDownloadid(Integer downloadid) {
        this.downloadid = downloadid;
    }

    public Integer getCouponid() {
        return couponid;
    }

    public void setCouponid(Integer couponid) {
        this.couponid = couponid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getDownloaddate() {
        return downloaddate;
    }

    public void setDownloaddate(Date downloaddate) {
        this.downloaddate = downloaddate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (downloadid != null ? downloadid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coupondownloads)) {
            return false;
        }
        Coupondownloads other = (Coupondownloads) object;
        if ((this.downloadid == null && other.downloadid != null) || (this.downloadid != null && !this.downloadid.equals(other.downloadid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.foodnicherest.Coupondownloads[ downloadid=" + downloadid + " ]";
    }
    
}
