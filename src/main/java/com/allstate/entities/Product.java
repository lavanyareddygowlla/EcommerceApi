package com.allstate.entities;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "stock_number")
    private String stockNumber;
    private String description;
    private Integer rating;
    private Integer numberOfReview;
    private Integer price;
    private Integer discount;
    @Column(nullable = true,name = "actual_price")
    private Integer actualPrice;
    private Integer quantity;
    private Boolean restricted;


    public Integer getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Integer actualPrice) {

        this.actualPrice = this.price - (this.price * this.discount/100);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(String stockNumber) {
        this.stockNumber = stockNumber;
    }

}
