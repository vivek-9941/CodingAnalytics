package org.vivek.platform.Service.Implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.vivek.platform.Model.Leetcode.Leetcode;
import org.vivek.platform.Model.User;
import org.vivek.platform.Repository.LeetcodeRepository;
import org.vivek.platform.Service.DTO.LeetcodeDTO;
import org.vivek.platform.Service.DTO.LeetcodeMappingService;
import org.vivek.platform.Service.LeetcodeService;

@Service
public class LeetcodeServiceImpl implements LeetcodeService {
//    {
//        "operationName": "getUserFullProfile",
//            "variables": {
//        "username": "kartik_mane"
//    },
//        "query": "query getUserFullProfile($username: String!)
//        { matchedUser(username: $username) { username submitStatsGlobal { acSubmissionNum
//        { difficulty count submissions } } profile { realName ranking userAvatar reputation school
//        countryName company jobTitle skillTags aboutMe } tagProblemCounts { advanced { tagName tagSlug problemsSolved }
//        intermediate { tagName tagSlug problemsSolved } fundamental { tagName tagSlug problemsSolved } } } }"
//    }


    @Autowired
    private LeetcodeClient client;

    public LeetcodeServiceImpl(LeetcodeClient client) {
        this.client = client;
    }

    @Autowired
    private LeetcodeMappingService mappingService;

    @Autowired
    private LeetcodeRepository leetcodeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void fetchapi(String username, User user) throws JsonProcessingException {

        ResponseEntity<String> response = client.fetchUserProfile(username);
        if (response.getStatusCode().is2xxSuccessful()) {
            String body = response.getBody();
            LeetcodeDTO dto = objectMapper.readValue(body, LeetcodeDTO.class);

            // Map new data to entity
            Leetcode newEntity = mappingService.mapToEntity(dto, user);

            // Check if existing record present
            Leetcode existingEntity = leetcodeRepository.findByHandleAndUser(newEntity.getHandle(), user).orElse(null);

            if (existingEntity != null) {
                // Update ID so JPA treats this as update, not insert
                newEntity.setId(existingEntity.getId());

                // Optional: if you want to merge or selectively update fields in existingEntity,
                // you can do so here instead of full replace.

                // Also, if you want to update nested collections carefully (submissionStats, tagStats),
                // consider clearing or updating those collections here to avoid duplicates.
            }

            leetcodeRepository.save(newEntity);
        } else {
            throw new RuntimeException("Failed to fetch profile from Leetcode for: " + username);
        }
    }


    @Override
    public void schedule() {

    }
}
