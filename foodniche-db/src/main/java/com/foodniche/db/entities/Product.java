package com.foodniche.db.entities;

import javax.persistence.*;

/**
 * Created by agdubrov on 12/2/15.
 */

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "productid")
    private Integer productId;

    private Integer price;

    private Integer likes;

    @Column(name = "photourl")
    private String photoUrl;

    private String description;

    @ManyToOne
    @JoinColumn(name = "businessid")
    private Businesses businesses;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Businesses getBusinesses() {
        return businesses;
    }

    public void setBusinesses(Businesses businesses) {
        this.businesses = businesses;
    }
}
