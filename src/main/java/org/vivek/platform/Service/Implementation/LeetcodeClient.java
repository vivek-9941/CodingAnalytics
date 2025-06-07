package org.vivek.platform.Service.Implementation;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class LeetcodeClient {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final RestTemplate restTemplate;

    public LeetcodeClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Value("${leetcode.graphql.url}")

    private String url;

    public ResponseEntity<String> fetchUserProfile(String username) throws JsonProcessingException {

        String query  = "query getUserFullProfile($username: String!) { " +
                "matchedUser(username: $username) { " +
                "username " +
                "submitStatsGlobal { acSubmissionNum { difficulty count submissions } } " +
                "profile { realName ranking userAvatar reputation school countryName company jobTitle skillTags aboutMe } " +
                "tagProblemCounts { " +
                "advanced { tagName tagSlug problemsSolved } " +
                "intermediate { tagName tagSlug problemsSolved } " +
                "fundamental { tagName tagSlug problemsSolved } " +
                "} " +
                "} " +
                "}";
        Map<String , Object> variables = new HashMap<>();
        variables.put("username", username);
        Map<String , Object> payload  = new HashMap<>();
        payload.put("query", query);
        payload.put("variables", variables);
        payload.put("operationName", "getUserFullProfile");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(payload), headers);
        ResponseEntity<String> ans=  restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                String.class
        );
        System.out.println(ans.getBody());
        return ans;
    }


}
