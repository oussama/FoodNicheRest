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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "ingredients")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ingredients.findAll", query = "SELECT i FROM Ingredients i"),
    @NamedQuery(name = "Ingredients.findByIngredientid", query = "SELECT i FROM Ingredients i WHERE i.ingredientid = :ingredientid"),
    @NamedQuery(name = "Ingredients.findByName", query = "SELECT i FROM Ingredients i WHERE i.name = :name"),
    @NamedQuery(name = "Ingredients.findByPicture", query = "SELECT i FROM Ingredients i WHERE i.picture = :picture")})
public class Ingredients implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ingredientid")
    private Integer ingredientid;
    @Size(max = 80)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "picture")
    private String picture;
    @JoinTable(name = "recipeingredients", joinColumns = {
        @JoinColumn(name = "ingredientid", referencedColumnName = "ingredientid")}, inverseJoinColumns = {
        @JoinColumn(name = "recipeid", referencedColumnName = "recipeid")})
    @ManyToMany
    private Collection<Recipes> recipesCollection;

    public Ingredients() {
    }

    public Ingredients(Integer ingredientid) {
        this.ingredientid = ingredientid;
    }

    public Integer getIngredientid() {
        return ingredientid;
    }

    public void setIngredientid(Integer ingredientid) {
        this.ingredientid = ingredientid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @XmlTransient
    public Collection<Recipes> getRecipesCollection() {
        return recipesCollection;
    }

    public void setRecipesCollection(Collection<Recipes> recipesCollection) {
        this.recipesCollection = recipesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ingredientid != null ? ingredientid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingredients)) {
            return false;
        }
        Ingredients other = (Ingredients) object;
        if ((this.ingredientid == null && other.ingredientid != null) || (this.ingredientid != null && !this.ingredientid.equals(other.ingredientid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.foodnicherest.Ingredients[ ingredientid=" + ingredientid + " ]";
    }
    
}
