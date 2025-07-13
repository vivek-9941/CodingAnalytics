package org.vivek.platform.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.vivek.platform.Model.LoginRequest;
import org.vivek.platform.Model.User;
import org.vivek.platform.Security.JwtService;
import org.vivek.platform.Service.Implementation.UserServiceImpl;
import org.vivek.platform.Service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/auth/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@RequestBody  User user) {
        if (userService.checkAlreadyPresent(user.getUsername(), user.getEmail())) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(user);
        } else {
            System.out.println(user);
            userService.save(user);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    
    @PostMapping("/present")
    public ResponseEntity<?> presentUser(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
        }
        String token = authHeader.substring(7);
        String email = jwtService.getEmailFromToken(token);
        System.out.println("emails is" + email);
        User userOptional = userService.findByEmail(email); // or findByUsername
        System.out.println(userOptional);
        if (userOptional != null) {
            return ResponseEntity.ok("User exists");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(email);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        System.out.println(request);
        String token =  userService.validate(request.getUsername(), request.getPassword());//@RequestParam String username, @RequestParam String password


        if(token != null) {
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }

        @GetMapping("/fetchuser")
        public ResponseEntity<?> fetchUser(HttpServletRequest request) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                System.out.println("fetching user errorr");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
            }
            String token = authHeader.substring(7);
            String email = jwtService.getEmailFromToken(token);

            User userOptional = userService.findByEmail(email); // or findByUsername
            System.out.println(userOptional);
            if (userOptional != null) {
                return ResponseEntity.ok(userOptional);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(email);
            }
        }
}
