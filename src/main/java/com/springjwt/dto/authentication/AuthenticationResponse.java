package com.springjwt.dto.authentication;

import java.util.Date;

public record AuthenticationResponse(String jwtToken, Date expireDate, Date nowDate) {

}
