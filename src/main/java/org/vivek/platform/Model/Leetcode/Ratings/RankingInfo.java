package org.vivek.platform.Model.Leetcode.Ratings;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RankingInfo {

    private String username;
    private Integer attendedContestsCount;
    private Double rating;
    private Integer globalRanking;
    private Double topPercentage;
    private LocalDateTime lastupdated;
    private List<UserContestRankingHistory> contestHistory = new ArrayList<>();

}
