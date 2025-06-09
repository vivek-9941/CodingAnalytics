package org.vivek.platform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vivek.platform.Model.Leetcode.Ratings.RankingInfo;

@Repository
public interface CurrentRatingInfo extends JpaRepository<RankingInfo, Long> {
    RankingInfo findByUsername(String username);
}
