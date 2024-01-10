package com.springjwt.entity.main;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private long id;

    @Column(name = "product_title")
    private String title;

    @Column(name = "product_ingredients")
    private String ingredients;

    @Column(name = "product_description")
    private String description;

    @Column(name = "product_category")
    private String category;

    @Column(name = "product_image")
    private String image;

    @Column(name = "product_price")
    private double price;

    @Column(name = "Product_quantity")
    private int quantity;

    public Product(String title, String ingredients, String description, String category, String image, double price, int quantity) {
        this.title = title;
        this.ingredients = ingredients;
        this.description = description;
        this.category = category;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }
}
