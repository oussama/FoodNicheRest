package com.foodniche.rest.model;

/**
 * @author Alexey Dubrov
 *
 * Near restaurans.
 *
 */

public class NearRestaurants {

    Integer typeId;
    String zipCode;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
