package org.vivek.platform.Model.Codeforces;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vivek.platform.Model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Codeforces {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(unique = true)

    private String handle;
    private String globalrank;
    private Integer rating;
    private Integer maxRating;
    private LocalDateTime lastUpdated;

    @ElementCollection
    @CollectionTable(
            name = "codeforces_rating_history",
            joinColumns = @JoinColumn(name = "codeforces_id"))
    private List<Rating> CodeforcesRatings;

    @OneToOne
    private User user;
}
