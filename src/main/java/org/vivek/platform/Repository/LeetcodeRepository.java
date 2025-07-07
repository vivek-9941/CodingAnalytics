package org.vivek.platform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vivek.platform.Model.Leetcode.Leetcode;
import org.vivek.platform.Model.User;

import java.util.Optional;
@Repository
public interface LeetcodeRepository extends JpaRepository<Leetcode, Long> {
    Leetcode findByHandle(String handle);

    Leetcode findByUser(User user);
}
