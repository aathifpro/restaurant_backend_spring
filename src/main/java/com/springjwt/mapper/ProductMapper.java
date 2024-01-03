package com.springjwt.mapper;

import com.springjwt.dto.main.ProductDTO;
import com.springjwt.entity.main.Product;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

public class ProductMapper {

    private static ProductMapper instance = null;

    private ProductMapper() {}

    public static ProductMapper getInstance() {
        if(instance == null) {
            instance = new ProductMapper();
        }
        return instance;
    }

    public Product getProductMapper(ProductDTO productDTO, Optional<Product> productDetails) {
        Product product = new Product();
        if (productDetails.isPresent()) {
            product = productDetails.get();
            product.setTitle(productDTO.getTitle());
            product.setIngredients(productDTO.getIngredients());
            product.setCategory(productDTO.getCategory());
            product.setDescription(productDTO.getDescription());
            product.setPrice(productDTO.getPrice());
            product.setImage(productDTO.getImage());
            product.setQuantity(productDTO.getQuantity());
        }
        return product;
    }
}
