package org.vivek.platform.Model.Codechef;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Codechef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String handle;
    private String realName;
    private Integer globalRanking;
    private Integer Currentrating;
    private String country;
    private LocalDateTime lastUpdated;

    @ElementCollection
    @CollectionTable(
            name = "codechef_rating_history",
            joinColumns = @JoinColumn(name = "codechef_id"))
    private List<Rating> CodechefRatings;

    @OneToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @JsonBackReference
    private User user;
}
