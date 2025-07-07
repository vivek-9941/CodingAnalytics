package org.vivek.platform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vivek.platform.Model.Codeforces.Codeforces;
import org.vivek.platform.Model.User;
@Repository
public interface CodeforcesRepository extends JpaRepository<Codeforces, Long> {
    Codeforces findByHandle(String handle);

    Codeforces getCodeforcesByUser(User user);
}
