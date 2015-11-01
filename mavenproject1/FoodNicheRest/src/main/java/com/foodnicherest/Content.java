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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

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
    @NamedQuery(name = "Content.findByName", query = "SELECT c FROM Content c WHERE c.name = :name"),
    @NamedQuery(name = "Content.findByContentdate", query = "SELECT c FROM Content c WHERE c.contentdate = :contentdate"),
    @NamedQuery(name = "Content.findByContent", query = "SELECT c FROM Content c WHERE c.content = :content"),
    @NamedQuery(name = "Content.findByFilename", query = "SELECT c FROM Content c WHERE c.filename = :filename")})
public class Content implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "contentid")
    private Integer contentid;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Column(name = "contentdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contentdate;
    @Size(max = 2147483647)
    @Column(name = "content")
    private String content;
    @Size(max = 255)
    @Column(name = "filename")
    private String filename;
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    @ManyToOne
    private Users userid;
    @JoinColumn(name = "groupid", referencedColumnName = "groupid")
    @ManyToOne
    private Groups groupid;
    @JoinColumn(name = "contenttypeid", referencedColumnName = "contenttypeid")
    @ManyToOne
    private Contenttypes contenttypeid;
    @JoinColumn(name = "businessid", referencedColumnName = "businessid")
    @ManyToOne
    private Businesses businessid;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Users getUserid() {
        return userid;
    }

    public void setUserid(Users userid) {
        this.userid = userid;
    }

    public Groups getGroupid() {
        return groupid;
    }

    public void setGroupid(Groups groupid) {
        this.groupid = groupid;
    }

    public Contenttypes getContenttypeid() {
        return contenttypeid;
    }

    public void setContenttypeid(Contenttypes contenttypeid) {
        this.contenttypeid = contenttypeid;
    }

    public Businesses getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Businesses businessid) {
        this.businessid = businessid;
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
