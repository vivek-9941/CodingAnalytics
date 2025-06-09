package org.vivek.platform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vivek.platform.Model.Leetcode.Ratings.RankingInfo;
import org.vivek.platform.Model.Leetcode.Ratings.UserContestRankingHistory;

@Repository
public interface UserContestRankingInfoRepository extends JpaRepository<RankingInfo, Long> {}
