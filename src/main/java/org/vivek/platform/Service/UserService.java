package org.vivek.platform.Service;


import org.vivek.platform.Model.User;

public interface UserService {
    void save(User user);
    void register(User user);
    User getUserById(Long id);
}
