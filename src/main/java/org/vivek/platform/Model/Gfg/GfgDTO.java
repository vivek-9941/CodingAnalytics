package org.vivek.platform.Model.Gfg;

import lombok.Data;
import java.util.Map;

@Data
public class GfgDTO {
    private Info info;
    private Map<String, DifficultyStats> solvedStats;

    @Data
    public static class Info {
        private String userName;
        private String fullName;
        private String profilePicture;
        private String institute;
        private Integer instituteRank;
        private Integer currentStreak;
        private Integer maxStreak;
        private Integer codingScore;
        private Integer monthlyScore;
        private Integer totalProblemsSolved;
    }

    @Data
    public static class DifficultyStats {
        private int count;
    }
}
