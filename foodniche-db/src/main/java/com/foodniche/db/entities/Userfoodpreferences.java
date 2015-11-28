/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodniche.db.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author User
 */
@Entity
@Table(name = "userfoodpreferences")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userfoodpreferences.findAll", query = "SELECT u FROM UserFoodPreferences u"),
    @NamedQuery(name = "Userfoodpreferences.findByDescription", query = "SELECT u FROM UserFoodPreferences u WHERE u.description = :description")})
public class UserFoodPreferences implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserFoodPreferencesPK userFoodPreferencesPK;
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;

    public UserFoodPreferences() {
    }

    public UserFoodPreferences(UserFoodPreferencesPK userFoodPreferencesPK) {
        this.userFoodPreferencesPK = userFoodPreferencesPK;
    }

    public UserFoodPreferencesPK getUserFoodPreferencesPK() {
        return userFoodPreferencesPK;
    }

    public void setUserFoodPreferencesPK(UserFoodPreferencesPK userFoodPreferencesPK) {
        this.userFoodPreferencesPK = userFoodPreferencesPK;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserFoodPreferences that = (UserFoodPreferences) o;

        if (userFoodPreferencesPK != null ? !userFoodPreferencesPK.equals(that.userFoodPreferencesPK) : that.userFoodPreferencesPK != null)
            return false;
        return !(description != null ? !description.equals(that.description) : that.description != null);

    }

    @Override
    public int hashCode() {
        int result = userFoodPreferencesPK != null ? userFoodPreferencesPK.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserFoodPreferences{" +
                "userFoodPreferencesPK=" + userFoodPreferencesPK +
                ", description='" + description + '\'' +
                '}';
    }
}
