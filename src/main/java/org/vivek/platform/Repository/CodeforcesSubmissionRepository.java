package org.vivek.platform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vivek.platform.Model.Codeforces.CodeforcesSubmissions;

@Repository
public interface CodeforcesSubmissionRepository extends JpaRepository<CodeforcesSubmissions, Long> {
}
