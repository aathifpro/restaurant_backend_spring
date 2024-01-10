package com.springjwt.dto.main;

import lombok.Data;

@Data
public class ProductDTO {

    private long id;
    private String title;
    private String ingredients;
    private String description;
    private String category;
    private String image;
    private double price;
    private int quantity;

}
