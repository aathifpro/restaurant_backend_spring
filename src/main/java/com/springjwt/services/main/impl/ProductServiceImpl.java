package com.springjwt.services.main.impl;

import com.springjwt.common.StatusCode;
import com.springjwt.dto.main.ProductDTO;
import com.springjwt.dto.main.responseDto.ProductResponseDTO;
import com.springjwt.entity.main.Product;
import com.springjwt.mapper.ProductMapper;
import com.springjwt.repository.main.ProductRepository;
import com.springjwt.services.main.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;


    /**
     * Create product request
     *
     * @param productDTO - required dto to create an product
     * @return success or failed response from product creation and product details
     * @author aathif
     */
    @Override
    public ProductResponseDTO createProduct(ProductDTO productDTO) {
        try {
            Product product = new Product(
                    productDTO.getTitle(),
                    productDTO.getIngredients(),
                    productDTO.getDescription(),
                    productDTO.getCategory(),
                    productDTO.getImage(),
                    productDTO.getPrice(),
                    productDTO.getQuantity()
            );
            productRepository.save(product);
            return new ProductResponseDTO(StatusCode.CREATED, product, "Product creation success!", new Date());
        } catch (Exception e) {
            return new ProductResponseDTO(StatusCode.INTERNAL_SERVER_ERROR, null, "Product creation failed", new Date());
        }
    }


    /**
     * Get products request
     *
     * @return success or failed response from order and all orders details
     * @author aathif
     */
    @Override
    public ResponseEntity<List<Product>> getProducts() {
        try {
            List<Product> productList = new ArrayList<>(productRepository.findAll());

            if (productList.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get product request
     *
     * @param id - required variable to get product
     * @return success or failed response from product and product details
     * @author aathif
     */
    @Override
    public ResponseEntity<Product> getProduct(long id) {
        try {
            Optional<Product> productData = productRepository.findById(id);
            return productData.map(product -> new ResponseEntity<>(product, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update product request
     *
     * @param id - required variable to update an product
     * @param productDTO - required dto to update an product
     * @return success or failed response from product update and return updated product details
     * @author aathif
     */
    @Override
    public ProductResponseDTO updateProduct(long id, ProductDTO productDTO) {
        try {
            Optional<Product> productDetails = productRepository.findById(id);
            if (productDetails.isPresent()) {
                Product product = ProductMapper.getInstance().getProductMapper(productDTO, productDetails);
                return new ProductResponseDTO(StatusCode.OK, productRepository.save(product), "Product Update success!", new Date());
            }
            return new ProductResponseDTO(StatusCode.NO_CONTENT, null, "Product Update Failed!", new Date());
        } catch (Exception e) {
            return new ProductResponseDTO(StatusCode.INTERNAL_SERVER_ERROR, null, e.getMessage(), new Date());
        }
    }

    /**
     * Delete order request
     *
     * @param id - required variable to delete product
     * @return success or failed response from product
     * @author aathif
     */
    @Override
    public ProductResponseDTO deleteProduct(long id) {
        try {
            productRepository.deleteById(id);
            return new ProductResponseDTO(StatusCode.OK, null, "Product is Deleted!", new Date());
        } catch (Exception e) {
            return new ProductResponseDTO(StatusCode.INTERNAL_SERVER_ERROR, null, "Exception occurred while deleting the product", new Date());
        }
    }


}
