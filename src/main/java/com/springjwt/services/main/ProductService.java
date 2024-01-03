package com.springjwt.services.main;

import com.springjwt.dto.main.ProductDTO;
import com.springjwt.dto.main.ProductResponseDTO;
import com.springjwt.entity.main.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ProductResponseDTO createProduct(ProductDTO productDTO);

    ResponseEntity<List<Product>> getProducts();

    ResponseEntity<Product> getProduct(long id);

    ProductResponseDTO updateProduct(long id, ProductDTO productDTO);

    ProductResponseDTO deleteProduct(long id);
}
