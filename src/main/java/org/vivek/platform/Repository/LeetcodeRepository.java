package org.vivek.platform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vivek.platform.Model.Leetcode.Leetcode;
import org.vivek.platform.Model.User;

import java.util.Optional;

public interface LeetcodeRepository extends JpaRepository<Leetcode, Long> {
    Optional<Leetcode> findByHandleAndUser(String handle, User user);
}
