/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodnicherest;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserid", query = "SELECT u FROM Users u WHERE u.userid = :userid"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByFirstname", query = "SELECT u FROM Users u WHERE u.firstname = :firstname"),
    @NamedQuery(name = "Users.findByLastname", query = "SELECT u FROM Users u WHERE u.lastname = :lastname"),
    @NamedQuery(name = "Users.findByZipcode", query = "SELECT u FROM Users u WHERE u.zipcode = :zipcode"),
    @NamedQuery(name = "Users.findByStatus", query = "SELECT u FROM Users u WHERE u.status = :status"),
    @NamedQuery(name = "Users.findByProfilepicture", query = "SELECT u FROM Users u WHERE u.profilepicture = :profilepicture"),
    @NamedQuery(name = "Users.findByAccounttype", query = "SELECT u FROM Users u WHERE u.accounttype = :accounttype")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userid")
    private Integer userid;
    @Size(max = 80)
    @Column(name = "username")
    private String username;
    @Size(max = 80)
    @Column(name = "password")
    private String password;
    @Size(max = 80)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 80)
    @Column(name = "lastname")
    private String lastname;
    @Size(max = 80)
    @Column(name = "zipcode")
    private String zipcode;
    @Column(name = "status")
    private Short status;
    @Size(max = 255)
    @Column(name = "profilepicture")
    private String profilepicture;
    @Column(name = "accounttype")
    private Short accounttype;
    @ManyToMany(mappedBy = "usersCollection")
    private Collection<Groups> groupsCollection;
    @ManyToMany(mappedBy = "usersCollection")
    private Collection<Businesses> businessesCollection;
    @ManyToMany(mappedBy = "usersCollection1")
    private Collection<Businesses> businessesCollection1;
    @OneToMany(mappedBy = "userid")
    private Collection<Subscriptions> subscriptionsCollection;
    @OneToMany(mappedBy = "userid")
    private Collection<Recipes> recipesCollection;
    @OneToMany(mappedBy = "touserid")
    private Collection<Messages> messagesCollection;
    @OneToMany(mappedBy = "fromuserid")
    private Collection<Messages> messagesCollection1;
    @OneToMany(mappedBy = "userid")
    private Collection<Content> contentCollection;
    @OneToMany(mappedBy = "userid")
    private Collection<Payments> paymentsCollection;
    @OneToMany(mappedBy = "userid")
    private Collection<Coupondownloads> coupondownloadsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<Userfoodpreferences> userfoodpreferencesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<Connections> connectionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users1")
    private Collection<Connections> connectionsCollection1;

    public Users() {
    }

    public Users(Integer userid) {
        this.userid = userid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    public Short getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(Short accounttype) {
        this.accounttype = accounttype;
    }

    @XmlTransient
    public Collection<Groups> getGroupsCollection() {
        return groupsCollection;
    }

    public void setGroupsCollection(Collection<Groups> groupsCollection) {
        this.groupsCollection = groupsCollection;
    }

    @XmlTransient
    public Collection<Businesses> getBusinessesCollection() {
        return businessesCollection;
    }

    public void setBusinessesCollection(Collection<Businesses> businessesCollection) {
        this.businessesCollection = businessesCollection;
    }

    @XmlTransient
    public Collection<Businesses> getBusinessesCollection1() {
        return businessesCollection1;
    }

    public void setBusinessesCollection1(Collection<Businesses> businessesCollection1) {
        this.businessesCollection1 = businessesCollection1;
    }

    @XmlTransient
    public Collection<Subscriptions> getSubscriptionsCollection() {
        return subscriptionsCollection;
    }

    public void setSubscriptionsCollection(Collection<Subscriptions> subscriptionsCollection) {
        this.subscriptionsCollection = subscriptionsCollection;
    }

    @XmlTransient
    public Collection<Recipes> getRecipesCollection() {
        return recipesCollection;
    }

    public void setRecipesCollection(Collection<Recipes> recipesCollection) {
        this.recipesCollection = recipesCollection;
    }

    @XmlTransient
    public Collection<Messages> getMessagesCollection() {
        return messagesCollection;
    }

    public void setMessagesCollection(Collection<Messages> messagesCollection) {
        this.messagesCollection = messagesCollection;
    }

    @XmlTransient
    public Collection<Messages> getMessagesCollection1() {
        return messagesCollection1;
    }

    public void setMessagesCollection1(Collection<Messages> messagesCollection1) {
        this.messagesCollection1 = messagesCollection1;
    }

    @XmlTransient
    public Collection<Content> getContentCollection() {
        return contentCollection;
    }

    public void setContentCollection(Collection<Content> contentCollection) {
        this.contentCollection = contentCollection;
    }

    @XmlTransient
    public Collection<Payments> getPaymentsCollection() {
        return paymentsCollection;
    }

    public void setPaymentsCollection(Collection<Payments> paymentsCollection) {
        this.paymentsCollection = paymentsCollection;
    }

    @XmlTransient
    public Collection<Coupondownloads> getCoupondownloadsCollection() {
        return coupondownloadsCollection;
    }

    public void setCoupondownloadsCollection(Collection<Coupondownloads> coupondownloadsCollection) {
        this.coupondownloadsCollection = coupondownloadsCollection;
    }

    @XmlTransient
    public Collection<Userfoodpreferences> getUserfoodpreferencesCollection() {
        return userfoodpreferencesCollection;
    }

    public void setUserfoodpreferencesCollection(Collection<Userfoodpreferences> userfoodpreferencesCollection) {
        this.userfoodpreferencesCollection = userfoodpreferencesCollection;
    }

    @XmlTransient
    public Collection<Connections> getConnectionsCollection() {
        return connectionsCollection;
    }

    public void setConnectionsCollection(Collection<Connections> connectionsCollection) {
        this.connectionsCollection = connectionsCollection;
    }

    @XmlTransient
    public Collection<Connections> getConnectionsCollection1() {
        return connectionsCollection1;
    }

    public void setConnectionsCollection1(Collection<Connections> connectionsCollection1) {
        this.connectionsCollection1 = connectionsCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.foodnicherest.Users[ userid=" + userid + " ]";
    }
    
}
