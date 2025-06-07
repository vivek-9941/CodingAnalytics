package org.vivek.platform.Model.Codeforces;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Rating {
    private LocalDate ratingDate;
    private Integer rating;
}
