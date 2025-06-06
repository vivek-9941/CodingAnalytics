package org.vivek.platform.Model.Leetcode;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeetCodeSubmissionStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String difficulty;  // "Easy", "Medium", "Hard", "All"
    private Integer count;      // Accepted
    private Integer submissions;

    @ManyToOne
    private Leetcode profile;
}
