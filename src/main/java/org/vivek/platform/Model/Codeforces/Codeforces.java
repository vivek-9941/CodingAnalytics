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
    long id;
    private String handle;
    private String realName;
    private Integer globalRanking;
    private String country;
    private Integer totalSolved;
    private LocalDateTime lastUpdated;

    @ElementCollection
    @CollectionTable(
            name = "codeforces_rating_history",
            joinColumns = @JoinColumn(name = "codeforces_id"))
    private List<Rating> CodeforcesRatings;

    @OneToOne
    private User user;
}
