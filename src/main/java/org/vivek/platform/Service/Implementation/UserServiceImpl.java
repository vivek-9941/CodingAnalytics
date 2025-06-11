package org.vivek.platform.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vivek.platform.Model.User;
import org.vivek.platform.Repository.UserRepository;
import org.vivek.platform.Service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean checkAlreadyPresent(String username, String email) {
        return userRepository.findByUsername(username) != null || userRepository.findByEmail(email) != null;
    }


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
