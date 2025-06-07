package org.vivek.platform.Service.DTO;

import java.util.List;

public class LeetcodeDTO {


        public Data data;

        public static class Data {
            public MatchedUser matchedUser;
        }

        public static class MatchedUser {
            public String username;
            public SubmitStatsGlobal submitStatsGlobal;
            public Profile profile;
            public TagProblemCounts tagProblemCounts;
        }

        public static class SubmitStatsGlobal {
            public List<SubmissionStat> acSubmissionNum;
        }

        public static class SubmissionStat {
            public String difficulty;
            public int count;
            public int submissions;
        }

        public static class Profile {
            public String realName;
            public Integer ranking;
            public String countryName;
        }

        public static class TagProblemCounts {
            public List<TagInfo> fundamental;
            public List<TagInfo> intermediate;
            public List<TagInfo> advanced;
        }

        public static class TagInfo {
            public String tagName;
            public String tagSlug;
            public int problemsSolved;
        }

}
