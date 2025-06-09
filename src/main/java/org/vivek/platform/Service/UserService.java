package org.vivek.platform.Service;


import org.vivek.platform.Model.User;

public interface UserService {
    void save(User user);
    boolean checkAlreadyPresent(String username, String email);

}
