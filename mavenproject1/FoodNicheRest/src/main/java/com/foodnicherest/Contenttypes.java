/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodnicherest;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "contenttypes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contenttypes.findAll", query = "SELECT c FROM Contenttypes c"),
    @NamedQuery(name = "Contenttypes.findByContenttypeid", query = "SELECT c FROM Contenttypes c WHERE c.contenttypeid = :contenttypeid"),
    @NamedQuery(name = "Contenttypes.findByName", query = "SELECT c FROM Contenttypes c WHERE c.name = :name")})
public class Contenttypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "contenttypeid")
    private Integer contenttypeid;
    @Size(max = 80)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "contenttypeid")
    private Collection<Content> contentCollection;

    public Contenttypes() {
    }

    public Contenttypes(Integer contenttypeid) {
        this.contenttypeid = contenttypeid;
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

    @XmlTransient
    public Collection<Content> getContentCollection() {
        return contentCollection;
    }

    public void setContentCollection(Collection<Content> contentCollection) {
        this.contentCollection = contentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contenttypeid != null ? contenttypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contenttypes)) {
            return false;
        }
        Contenttypes other = (Contenttypes) object;
        if ((this.contenttypeid == null && other.contenttypeid != null) || (this.contenttypeid != null && !this.contenttypeid.equals(other.contenttypeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.foodnicherest.Contenttypes[ contenttypeid=" + contenttypeid + " ]";
    }
    
}
