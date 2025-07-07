package org.vivek.platform.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.vivek.platform.Model.User;
import org.vivek.platform.Repository.UserRepository;
import org.vivek.platform.Security.JwtService;
import org.vivek.platform.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder; // ADD THIS

    @Override
    public void save(User user) {
        // ENCODE PASSWORD BEFORE SAVING
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        System.out.println("User saved with encoded password: " + user.getUsername());
    }

    @Override
    public boolean checkAlreadyPresent(String username, String email) {
        return userRepository.findByUsername(username) != null || userRepository.findByEmail(email) != null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public String validate(String username, String password) {
        try {
            System.out.println("Attempting to authenticate user: " + username);

            // Authenticate using username and password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            if (authentication.isAuthenticated()) {
                System.out.println("Authentication successful for: " + username);

                // Find the user to get email for JWT token
                User foundUser = userRepository.findByUsername(username);
                if (foundUser != null) {
                    String token = jwtService.generateToken(foundUser.getEmail());
                    System.out.println("JWT token generated for email: " + foundUser.getEmail());
                    return token;
                } else {
                    System.out.println("User not found after authentication: " + username);
                    return null;
                }
            }
        } catch (Exception e) {
            System.out.println("Authentication failed for user: " + username + " - " + e.getMessage());
        }
        return null;
    }
}