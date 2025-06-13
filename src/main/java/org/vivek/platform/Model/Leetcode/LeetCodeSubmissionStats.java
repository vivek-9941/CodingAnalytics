package org.vivek.platform.Model.Leetcode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "leet_code_submission_stats")
public class LeetCodeSubmissionStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer Easy;  // "Easy", "Medium", "Hard", "All"
    private Integer Medium;
    private Integer Hard;
    private Integer All_count;       // Accepted

    private Integer EasySubmission;  // "Easy", "Medium", "Hard", "All"
    private Integer MediumSubmission;
    private Integer HardSubmission;
    private Integer AllSubmission;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    @ToString.Exclude
    @JsonBackReference
    private Leetcode profile;
}
