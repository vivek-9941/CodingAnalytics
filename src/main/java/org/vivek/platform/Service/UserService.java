package org.vivek.platform.Service;


import org.vivek.platform.Model.User;

import java.util.Optional;

public interface UserService {
    void save(User user);
    boolean checkAlreadyPresent(String username, String email);

    User findByEmail(String email);
}
