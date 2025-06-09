package org.vivek.platform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vivek.platform.Model.User;
import org.vivek.platform.Service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(User user) {
        if(userService.checkAlreadyPresent(user.getUsername(), user.getEmail())){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("User already exists");
        }
        else{
            System.out.println(user);
            userService.save(user);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }




}
