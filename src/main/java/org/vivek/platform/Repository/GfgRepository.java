package org.vivek.platform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vivek.platform.Model.Gfg.GfgProfile;
import org.vivek.platform.Model.User;
@Repository
public interface GfgRepository extends JpaRepository<GfgProfile, Long> {
    GfgProfile findByUsername(String username);
}
