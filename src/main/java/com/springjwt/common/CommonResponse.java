package com.springjwt.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {

    private HttpStatus status;
    private String message;
    private Object data;
    private Date timestamp;

}
