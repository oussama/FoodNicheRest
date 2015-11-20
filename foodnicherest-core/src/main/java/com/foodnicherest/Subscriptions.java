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
@Table(name = "subscriptions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subscriptions.findAll", query = "SELECT s FROM Subscriptions s"),
    @NamedQuery(name = "Subscriptions.findBySubscriptionid", query = "SELECT s FROM Subscriptions s WHERE s.subscriptionid = :subscriptionid"),
    @NamedQuery(name = "Subscriptions.findByBusinessid", query = "SELECT s FROM Subscriptions s WHERE s.businessid = :businessid"),
    @NamedQuery(name = "Subscriptions.findByUserid", query = "SELECT s FROM Subscriptions s WHERE s.userid = :userid"),
    @NamedQuery(name = "Subscriptions.findBySubscriptiontypeid", query = "SELECT s FROM Subscriptions s WHERE s.subscriptiontypeid = :subscriptiontypeid"),
    @NamedQuery(name = "Subscriptions.findByValidfrom", query = "SELECT s FROM Subscriptions s WHERE s.validfrom = :validfrom"),
    @NamedQuery(name = "Subscriptions.findByValidto", query = "SELECT s FROM Subscriptions s WHERE s.validto = :validto")})
public class Subscriptions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "subscriptionid")
    private Integer subscriptionid;
    @Column(name = "businessid")
    private Integer businessid;
    @Column(name = "userid")
    private Integer userid;
    @Column(name = "subscriptiontypeid")
    private Integer subscriptiontypeid;
    @Column(name = "validfrom")
    @Temporal(TemporalType.DATE)
    private Date validfrom;
    @Column(name = "validto")
    @Temporal(TemporalType.DATE)
    private Date validto;

    public Subscriptions() {
    }

    public Subscriptions(Integer subscriptionid) {
        this.subscriptionid = subscriptionid;
    }

    public Integer getSubscriptionid() {
        return subscriptionid;
    }

    public void setSubscriptionid(Integer subscriptionid) {
        this.subscriptionid = subscriptionid;
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getSubscriptiontypeid() {
        return subscriptiontypeid;
    }

    public void setSubscriptiontypeid(Integer subscriptiontypeid) {
        this.subscriptiontypeid = subscriptiontypeid;
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
        hash += (subscriptionid != null ? subscriptionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subscriptions)) {
            return false;
        }
        Subscriptions other = (Subscriptions) object;
        if ((this.subscriptionid == null && other.subscriptionid != null) || (this.subscriptionid != null && !this.subscriptionid.equals(other.subscriptionid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.foodnicherest.Subscriptions[ subscriptionid=" + subscriptionid + " ]";
    }
    
}
