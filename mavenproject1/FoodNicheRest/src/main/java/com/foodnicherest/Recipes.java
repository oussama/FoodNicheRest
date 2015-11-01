/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodnicherest;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "recipes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recipes.findAll", query = "SELECT r FROM Recipes r"),
    @NamedQuery(name = "Recipes.findByRecipeid", query = "SELECT r FROM Recipes r WHERE r.recipeid = :recipeid"),
    @NamedQuery(name = "Recipes.findByDescription", query = "SELECT r FROM Recipes r WHERE r.description = :description"),
    @NamedQuery(name = "Recipes.findByTimetoprepare", query = "SELECT r FROM Recipes r WHERE r.timetoprepare = :timetoprepare"),
    @NamedQuery(name = "Recipes.findByRecipedate", query = "SELECT r FROM Recipes r WHERE r.recipedate = :recipedate"),
    @NamedQuery(name = "Recipes.findByPicture", query = "SELECT r FROM Recipes r WHERE r.picture = :picture"),
    @NamedQuery(name = "Recipes.findByVideo", query = "SELECT r FROM Recipes r WHERE r.video = :video")})
public class Recipes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "recipeid")
    private Integer recipeid;
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    @Column(name = "timetoprepare")
    private Integer timetoprepare;
    @Column(name = "recipedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recipedate;
    @Size(max = 255)
    @Column(name = "picture")
    private String picture;
    @Size(max = 255)
    @Column(name = "video")
    private String video;
    @ManyToMany(mappedBy = "recipesCollection")
    private Collection<Ingredients> ingredientsCollection;
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    @ManyToOne
    private Users userid;

    public Recipes() {
    }

    public Recipes(Integer recipeid) {
        this.recipeid = recipeid;
    }

    public Integer getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(Integer recipeid) {
        this.recipeid = recipeid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTimetoprepare() {
        return timetoprepare;
    }

    public void setTimetoprepare(Integer timetoprepare) {
        this.timetoprepare = timetoprepare;
    }

    public Date getRecipedate() {
        return recipedate;
    }

    public void setRecipedate(Date recipedate) {
        this.recipedate = recipedate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @XmlTransient
    public Collection<Ingredients> getIngredientsCollection() {
        return ingredientsCollection;
    }

    public void setIngredientsCollection(Collection<Ingredients> ingredientsCollection) {
        this.ingredientsCollection = ingredientsCollection;
    }

    public Users getUserid() {
        return userid;
    }

    public void setUserid(Users userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recipeid != null ? recipeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recipes)) {
            return false;
        }
        Recipes other = (Recipes) object;
        if ((this.recipeid == null && other.recipeid != null) || (this.recipeid != null && !this.recipeid.equals(other.recipeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.foodnicherest.Recipes[ recipeid=" + recipeid + " ]";
    }
    
}
