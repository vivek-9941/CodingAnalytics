package org.vivek.platform.Model.Leetcode.Ratings;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_contest_ranking_info")
public class RankingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private Integer attendedContestsCount;
    private Double rating;
    private Integer globalRanking;
    private Double topPercentage;
    private LocalDateTime lastupdated;

    @OneToMany(mappedBy = "userRankingInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserContestRankingHistory> contestHistory  = new ArrayList<>();
}
