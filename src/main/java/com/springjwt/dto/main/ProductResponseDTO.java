package com.springjwt.dto.main;

import com.springjwt.entity.main.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

    private Integer status;
    private Product data;
    private String message;
    private Date timestamp;

}
