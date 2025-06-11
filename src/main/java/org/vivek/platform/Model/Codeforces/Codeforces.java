package org.vivek.platform.Model.Codeforces;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.vivek.platform.Model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder@ToString(exclude = "user")

public class Codeforces {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;


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
    @JsonBackReference
    private User user;
}
