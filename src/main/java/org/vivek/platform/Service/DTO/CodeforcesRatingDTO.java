package org.vivek.platform.Service.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CodeforcesRatingDTO {
    private String status;
    private List<Result> result;
    @Data
    public static class  Result{
        private Integer contestId;
        private String contestName;
        private Integer rank;
        private Integer ratingUpdateTimeSeconds;
        private Integer oldRating;
        private Integer newRating;
    }
}
