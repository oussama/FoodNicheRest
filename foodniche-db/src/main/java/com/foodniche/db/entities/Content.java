/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodniche.db.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author User
 */
@Entity
@Table(name = "content")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Content.findAll", query = "SELECT c FROM Content c"),
    @NamedQuery(name = "Content.findByContentid", query = "SELECT c FROM Content c WHERE c.contentid = :contentid"),
    @NamedQuery(name = "Content.findByContenttypeid", query = "SELECT c FROM Content c WHERE c.contenttypeid = :contenttypeid"),
    @NamedQuery(name = "Content.findByName", query = "SELECT c FROM Content c WHERE c.name = :name"),
    @NamedQuery(name = "Content.findByUserid", query = "SELECT c FROM Content c WHERE c.userid = :userid"),
    @NamedQuery(name = "Content.findByBusinessid", query = "SELECT c FROM Content c WHERE c.businessid = :businessid"),
    @NamedQuery(name = "Content.findByGroupid", query = "SELECT c FROM Content c WHERE c.groupid = :groupid"),
    @NamedQuery(name = "Content.findByContentdate", query = "SELECT c FROM Content c WHERE c.contentdate = :contentdate"),
    @NamedQuery(name = "Content.findByContent", query = "SELECT c FROM Content c WHERE c.content = :content"),
    @NamedQuery(name = "Content.findByFilename", query = "SELECT c FROM Content c WHERE c.filename = :filename")})
public class Content implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contentid")
    private Integer contentid;
    @Column(name = "contenttypeid")
    private Integer contenttypeid;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Column(name = "userid")
    private Integer userid;
    @Column(name = "businessid")
    private Integer businessid;
    @Column(name = "groupid")
    private Integer groupid;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    @Column(name = "contentdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contentdate;
    @Size(max = 2147483647)
    @Column(name = "content")
    private String content;
    @Size(max = 255)
    @Column(name = "filename")
    private String filename;

    public Content() {
    }

    public Content(Integer contentid) {
        this.contentid = contentid;
    }

    public Integer getContentid() {
        return contentid;
    }

    public void setContentid(Integer contentid) {
        this.contentid = contentid;
    }

    public Integer getContenttypeid() {
        return contenttypeid;
    }

    public void setContenttypeid(Integer contenttypeid) {
        this.contenttypeid = contenttypeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public Date getContentdate() {
        return contentdate;
    }

    public void setContentdate(Date contentdate) {
        this.contentdate = contentdate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contentid != null ? contentid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Content)) {
            return false;
        }
        Content other = (Content) object;
        if ((this.contentid == null && other.contentid != null) || (this.contentid != null && !this.contentid.equals(other.contentid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.foodnicherest.Content[ contentid=" + contentid + " ]";
    }
    
}
