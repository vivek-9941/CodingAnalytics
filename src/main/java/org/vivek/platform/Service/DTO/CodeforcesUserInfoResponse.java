package org.vivek.platform.Service.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CodeforcesUserInfoResponse {
    private String status;
    private List<Result> result;

    @Data
    public static class Result{
        private String handle;
        private String rank;
        private Integer rating;
        private Integer maxRating;
    }
}
