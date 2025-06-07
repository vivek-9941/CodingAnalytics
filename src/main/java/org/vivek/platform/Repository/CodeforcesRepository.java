package org.vivek.platform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vivek.platform.Model.Codeforces.Codeforces;

public interface CodeforcesRepository extends JpaRepository<Codeforces, Long> {
}
