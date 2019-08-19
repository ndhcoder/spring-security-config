package com.ndhcoder.demo.controller;

import com.ndhcoder.demo.model.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/users")
public class UserController {

    @RequestMapping(method = RequestMethod.GET, value = "/profile")
    public ResponseEntity getUserInfo() {
        UserDTO currentUser = getCurrentUser();
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    private UserDTO getCurrentUser() {
        UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}
