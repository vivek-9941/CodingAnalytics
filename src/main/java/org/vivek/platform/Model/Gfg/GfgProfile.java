package org.vivek.platform.Model.Gfg;

import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "gfg_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GfgProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String fullName;
    private String profilePicture;
    private String institute;

    private Integer instituteRank;
    private Integer currentStreak;
    private Integer maxStreak;

    private Integer codingScore;
    private Integer monthlyScore;
    private Integer totalProblemsSolved;

    private int basicCount;
    private int easyCount;
    private int mediumCount;
    private int hardCount;

    private LocalDateTime lastUpdated;


}
