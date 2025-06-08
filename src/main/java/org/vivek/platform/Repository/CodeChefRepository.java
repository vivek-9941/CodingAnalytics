package org.vivek.platform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vivek.platform.Model.Codechef.Codechef;
import org.vivek.platform.Model.Codeforces.Codeforces;
import org.vivek.platform.Model.User;

public interface CodeChefRepository  extends JpaRepository<Codechef, Long> {
    Codechef findByUser(User user);
}
