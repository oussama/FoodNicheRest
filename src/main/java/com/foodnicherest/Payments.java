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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "payments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payments.findAll", query = "SELECT p FROM Payments p"),
    @NamedQuery(name = "Payments.findByPaymentid", query = "SELECT p FROM Payments p WHERE p.paymentid = :paymentid"),
    @NamedQuery(name = "Payments.findByUserid", query = "SELECT p FROM Payments p WHERE p.userid = :userid"),
    @NamedQuery(name = "Payments.findBySubscriptionid", query = "SELECT p FROM Payments p WHERE p.subscriptionid = :subscriptionid"),
    @NamedQuery(name = "Payments.findByPaymentdatetime", query = "SELECT p FROM Payments p WHERE p.paymentdatetime = :paymentdatetime"),
    @NamedQuery(name = "Payments.findByAmount", query = "SELECT p FROM Payments p WHERE p.amount = :amount"),
    @NamedQuery(name = "Payments.findBySource", query = "SELECT p FROM Payments p WHERE p.source = :source")})
public class Payments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "paymentid")
    private Integer paymentid;
    @Column(name = "userid")
    private Integer userid;
    @Column(name = "subscriptionid")
    private Integer subscriptionid;
    @Column(name = "paymentdatetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentdatetime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Size(max = 80)
    @Column(name = "source")
    private String source;

    public Payments() {
    }

    public Payments(Integer paymentid) {
        this.paymentid = paymentid;
    }

    public Integer getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(Integer paymentid) {
        this.paymentid = paymentid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getSubscriptionid() {
        return subscriptionid;
    }

    public void setSubscriptionid(Integer subscriptionid) {
        this.subscriptionid = subscriptionid;
    }

    public Date getPaymentdatetime() {
        return paymentdatetime;
    }

    public void setPaymentdatetime(Date paymentdatetime) {
        this.paymentdatetime = paymentdatetime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentid != null ? paymentid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payments)) {
            return false;
        }
        Payments other = (Payments) object;
        if ((this.paymentid == null && other.paymentid != null) || (this.paymentid != null && !this.paymentid.equals(other.paymentid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.foodnicherest.Payments[ paymentid=" + paymentid + " ]";
    }
    
}
