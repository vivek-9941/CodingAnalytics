package org.vivek.platform.Service.Implementation;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class CodeforcesSubmissionsclient {

    private final WebClient webClient;

    public CodeforcesSubmissionsclient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://codeforces.com/api").build();
    }

    // Method 1: Get verdict-wise count (OK, WA, TLE, etc.)
    public Map<String, Integer> getVerdictWiseStats(String handle) {
        List<Map<String, Object>> allSubmissions = fetchAllSubmissions(handle);

        Map<String, Integer> verdictCount = new TreeMap<>();
        for (Map<String, Object> submission : allSubmissions) {
            String verdict = (String) submission.get("verdict");
            if (verdict == null) continue;

            verdictCount.put(verdict, verdictCount.getOrDefault(verdict, 0) + 1);
        }
        return verdictCount;
    }

    // Method 2: Get list of ratings of accepted problems in a given range
    public  Map<Integer, Integer> getSolvedRatingCountInRange(String handle, int minRating, int maxRating) {
        List<Map<String, Object>> allSubmissions = fetchAllSubmissions(handle);

        Set<String> uniqueProblems = new HashSet<>();
        Map<Integer, Integer> ratingCount = new TreeMap<>();

        for (Map<String, Object> submission : allSubmissions) {
            if (!"OK".equals(submission.get("verdict"))) continue;

            Map<String, Object> problem = (Map<String, Object>) submission.get("problem");
            String key = submission.get("contestId") + "_" + problem.get("index");

            if (!uniqueProblems.add(key)) continue;

            Object ratingObj = problem.get("rating");
            if (ratingObj != null) {
                int rating = (int) ratingObj;
                if (rating >= minRating && rating <= maxRating) {
                    ratingCount.put(rating, ratingCount.getOrDefault(rating, 0) + 1);
                }
            }
        }

        return ratingCount;
    }

    // Utility: fetch all user submissions
    private List<Map<String, Object>> fetchAllSubmissions(String handle) {
        int from = 1;
        int count = 1000;
        List<Map<String, Object>> allSubmissions = new ArrayList<>();

        while (true) {
            String url = "/user.status?handle=" + handle + "&from=" + from + "&count=" + count;

            Map<String, Object> response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            List<Map<String, Object>> result = (List<Map<String, Object>>) response.get("result");
            if (result == null || result.isEmpty()) break;

            allSubmissions.addAll(result);
            if (result.size() < count) break;

            from += count;
        }

        return allSubmissions;
    }
}
