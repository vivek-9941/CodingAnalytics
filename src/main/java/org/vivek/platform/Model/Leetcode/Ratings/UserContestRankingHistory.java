package org.vivek.platform.Model.Leetcode.Ratings;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_contest_ranking_history")
public class UserContestRankingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trendDirection;

    private Integer problemsSolved;

    private Integer totalProblems;

    private Long finishTimeInSeconds;
    private Double rating;
    private Integer ranking;
    private String contestTitle;
    private LocalDateTime contestDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_ranking_info_id")
    private RankingInfo userRankingInfo;
}
