package com.springjwt.services.authentication.auth;

import com.springjwt.dto.authentication.SignupDTO;
import com.springjwt.dto.authentication.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupDTO signupDTO);
}
