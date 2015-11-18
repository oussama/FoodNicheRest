/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodnicherest;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "businesses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Businesses.findAll", query = "SELECT b FROM Businesses b"),
    @NamedQuery(name = "Businesses.findByBusinessid", query = "SELECT b FROM Businesses b WHERE b.businessid = :businessid"),
    @NamedQuery(name = "Businesses.findByBusinesstypeid", query = "SELECT b FROM Businesses b WHERE b.businesstypeid = :businesstypeid"),
    @NamedQuery(name = "Businesses.findByName", query = "SELECT b FROM Businesses b WHERE b.name = :name"),
    @NamedQuery(name = "Businesses.findByZipcode", query = "SELECT b FROM Businesses b WHERE b.zipcode = :zipcode"),
    @NamedQuery(name = "Businesses.findByWebsite", query = "SELECT b FROM Businesses b WHERE b.website = :website"),
    @NamedQuery(name = "Businesses.findByDescription", query = "SELECT b FROM Businesses b WHERE b.description = :description")})
public class Businesses implements Serializable {
    test broken build
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "businessid")
    private Integer businessid;
    @Column(name = "businesstypeid")
    private Integer businesstypeid;
    @Size(max = 80)
    @Column(name = "name")
    private String name;
    @Size(max = 80)
    @Column(name = "zipcode")
    private String zipcode;
    @Size(max = 255)
    @Column(name = "website")
    private String website;
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;

    public Businesses() {
    }

    public Businesses(Integer businessid) {
        this.businessid = businessid;
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public Integer getBusinesstypeid() {
        return businesstypeid;
    }

    public void setBusinesstypeid(Integer businesstypeid) {
        this.businesstypeid = businesstypeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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
        hash += (businessid != null ? businessid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Businesses)) {
            return false;
        }
        Businesses other = (Businesses) object;
        if ((this.businessid == null && other.businessid != null) || (this.businessid != null && !this.businessid.equals(other.businessid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.foodnicherest.Businesses[ businessid=" + businessid + " ]";
    }
    
}
