package com.springjwt.controllers.main;


import com.springjwt.common.CommonResponse;
import com.springjwt.common.StatusCode;
import com.springjwt.dto.main.ProductDTO;
import com.springjwt.dto.main.ProductResponseDTO;
import com.springjwt.entity.main.Product;
import com.springjwt.services.main.ProductService;
import jakarta.persistence.Id;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Statement;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Create product request
     *
     * @param productDTO - required dto to create an product
     * @return success or failed response from product creation and product details
     * @author aathif
     */
    @PostMapping("/product")
    public ResponseEntity<CommonResponse> createProduct(@RequestBody ProductDTO productDTO) {
        CommonResponse commonResponse = new CommonResponse();
        ProductResponseDTO responseDTO = productService.createProduct(productDTO);
        if (responseDTO.getStatus() == 200 || responseDTO.getStatus() == 201) {
            commonResponse.setStatus(HttpStatus.OK);
            commonResponse.setData(responseDTO.getData());
            commonResponse.setMessage(responseDTO.getMessage());
            commonResponse.setTimestamp(responseDTO.getTimestamp());
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        } else {
            commonResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            commonResponse.setData(responseDTO.getData());
            commonResponse.setMessage(responseDTO.getMessage());
            commonResponse.setTimestamp(responseDTO.getTimestamp());
            return new ResponseEntity<>(commonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Get products request
     *
     * @return success or failed response from product and all orders details
     * @author aathif
     */
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return productService.getProducts();
    }

    /**
     * Get product request
     *
     * @param id - required variable to get product
     * @return success or failed response from product and product details
     * @author aathif
     */
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id){
        return productService.getProduct(id);
    }


    /**
     * Update product request
     *
     * @param id - required variable to update an product
     * @param productDTO - required dto to update an product
     * @return success or failed response from product update and return updated product details
     * @author aathif
     */
    @PutMapping("/product/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable("id") long id, @RequestBody ProductDTO productDTO) {
        ProductResponseDTO productResponse = productService.updateProduct(id, productDTO);
        if (productResponse != null) {
            return new ResponseEntity<>(productResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<ProductResponseDTO> deleteProduct(@PathVariable("id") long id) {
        ProductResponseDTO productResponse = productService.deleteProduct(id);
        if(productResponse.getStatus() == StatusCode.OK) {
            return new ResponseEntity<>(productResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
