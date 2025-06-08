package org.vivek.platform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vivek.platform.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
