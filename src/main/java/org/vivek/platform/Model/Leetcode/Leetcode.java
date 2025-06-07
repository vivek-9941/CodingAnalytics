package org.vivek.platform.Model.Leetcode;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vivek.platform.Model.Codeforces.Rating;
import org.vivek.platform.Model.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Leetcode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String handle;
    private String realName;
    private Integer globalRanking;
    private String country;
    private Integer totalSolved;

    private LocalDateTime lastUpdated;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LeetCodeSubmissionStats> submissionStats;


    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LeetCodeTagStat> tagStats;

    @ElementCollection
    @CollectionTable(
            name = "leetcode_rating_history",
            joinColumns = @JoinColumn(name = "leetcode_id"))
    private List<Rating> ratingHistory;

    @OneToOne
    private User user;
}
