package org.vivek.platform.Model.Leetcode.Ratings;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserContestRankingHistory {

    private String trendDirection;

    private Integer problemsSolved;

    private Integer totalProblems;

    private Long finishTimeInSeconds;
    private Double rating;
    private Integer ranking;
    private String contestTitle;
    private LocalDateTime contestDateTime;


}
