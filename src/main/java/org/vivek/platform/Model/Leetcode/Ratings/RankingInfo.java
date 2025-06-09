package org.vivek.platform.Model.Leetcode.Ratings;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_ranking_info_id")  // This creates the foreign key in the child table
    private List<UserContestRankingHistory> contestHistory = new ArrayList<>();

}
