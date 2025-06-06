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
public class LeetCodeTagStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tagName;
    private String tagSlug;
    private String difficultyLevel; // fundamental, intermediate, advanced
    private Integer problemsSolved;

    @ManyToOne
    private Leetcode profile;
}
