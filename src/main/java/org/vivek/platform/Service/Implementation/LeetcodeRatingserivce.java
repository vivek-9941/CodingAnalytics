package org.vivek.platform.Service.Implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.vivek.platform.Model.Leetcode.Ratings.RankingInfo;
import org.vivek.platform.Model.Leetcode.Ratings.UserContestRankingHistory;
import org.vivek.platform.Repository.UserContestRankingInfoRepository;
import org.vivek.platform.config.WebConfig;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LeetcodeRatingserivce {
    @Autowired
    private WebClient leetcodeWebclient;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserContestRankingInfoRepository rankingInfoRepository;

    public List<JsonNode> fetchRecent100(String username) throws JsonProcessingException {
        String query =  """
        query userContestRankingInfo($username: String!) {
            userContestRanking(username: $username) {
                attendedContestsCount
                rating
                globalRanking
                totalParticipants
                topPercentage
                badge { name }
            }
            userContestRankingHistory(username: $username) {
                attended
                trendDirection
                problemsSolved
                totalProblems
                finishTimeInSeconds
                rating
                ranking
                contest { title startTime }
            }
        }
    """;
        Map<String, Object> payloadd= Map.of("operationName", "userContestRankingInfo",
                "variables", Map.of("username", username),
                "query", query);

        String response = leetcodeWebclient.post()
                .uri("/graphql")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE) // ✅ recommended
                .header(HttpHeaders.USER_AGENT, "Mozilla/5.0")
                .bodyValue(payloadd)
                .retrieve()
                .bodyToMono(String.class)
                .block();


        JsonNode root = objectMapper.readTree(response);
        JsonNode contestRanking = root.at("/data/userContestRanking");

        RankingInfo info = RankingInfo.builder()
                .username(username)
                .attendedContestsCount(contestRanking.path("attendedContestsCount").asInt())
                .rating(contestRanking.path("rating").asDouble())
                .globalRanking(contestRanking.path("globalRanking").asInt())
                .topPercentage(contestRanking.path("topPercentage").asDouble())
                .lastupdated(LocalDateTime.now())
                .build();

        JsonNode history = root.at("/data/userContestRankingHistory");

        List<UserContestRankingHistory> historyList = new ArrayList<>();
        for (int i = history.size() - 1; i >= 0 && i >= history.size() - 100; i--) {
            JsonNode node = history.get(i);
            if (!node.path("attended").asBoolean()) continue;

            UserContestRankingHistory entry = UserContestRankingHistory.builder()

                    .trendDirection(node.path("trendDirection").asText())
                    .problemsSolved(node.path("problemsSolved").asInt())
                    .totalProblems(node.path("totalProblems").asInt())
                    .finishTimeInSeconds(node.path("finishTimeInSeconds").asLong())
                    .rating(node.path("rating").asDouble())
                    .ranking(node.path("ranking").asInt())
                    .contestTitle(node.path("contest").path("title").asText())
                    .contestDateTime(Instant.ofEpochSecond(node.path("contest").path("startTime").asLong())
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime())
                    .userRankingInfo(info)// link back to parent
                    .build();

            historyList.add(entry);
        }

        info.setContestHistory(historyList);

        rankingInfoRepository.save(info); // cascades history entries
}
