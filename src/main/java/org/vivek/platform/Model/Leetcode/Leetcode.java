package org.vivek.platform.Model.Leetcode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
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
    @ToString.Exclude
    private List<LeetCodeSubmissionStats> submissionStats;


    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<LeetCodeTagStat> tagStats;


    @OneToOne
    @ToString.Exclude
    @JsonManagedReference
    private User user;
}
