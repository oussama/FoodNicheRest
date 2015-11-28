/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodniche.db.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class UserFoodPreferencesPK implements Serializable {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "userid")
    private Users user;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "foodpreferenceid")
    private FoodPreferences foodPreferences;

    public UserFoodPreferencesPK() {
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public FoodPreferences getFoodPreferences() {
        return foodPreferences;
    }

    public void setFoodPreferences(FoodPreferences foodPreferences) {
        this.foodPreferences = foodPreferences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserFoodPreferencesPK that = (UserFoodPreferencesPK) o;

        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return !(foodPreferences != null ? !foodPreferences.equals(that.foodPreferences) : that.foodPreferences != null);

    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (foodPreferences != null ? foodPreferences.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserFoodPreferencesPK{" +
                "user=" + user +
                ", foodPreferences=" + foodPreferences +
                '}';
    }
}
