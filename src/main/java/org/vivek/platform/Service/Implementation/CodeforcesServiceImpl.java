package org.vivek.platform.Service.Implementation;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.vivek.platform.Model.Codeforces.Codeforces;
import org.vivek.platform.Model.Codeforces.Rating;
import org.vivek.platform.Model.User;
import org.vivek.platform.Repository.CodeforcesRepository;
import org.vivek.platform.Service.CodeforcesService;
import org.vivek.platform.Service.DTO.CodeforcesRatingDTO;
import org.vivek.platform.Service.DTO.CodeforcesUserInfoResponse;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.Collectors;
@Service
public class CodeforcesServiceImpl implements CodeforcesService {
    private final WebClient CodeforcesClient;
    private final CodeforcesRepository codeforcesRepository;

    public CodeforcesServiceImpl(WebClient CodeforcesClient, CodeforcesRepository codeforcesRepository) {
        this.CodeforcesClient = CodeforcesClient;
        this.codeforcesRepository = codeforcesRepository;
    }

    @Override
    public void fetchapi(String username, User user) {
        CodeforcesUserInfoResponse userinfo = CodeforcesClient.get()
                .uri("/user.info?handles=" + username)
                .retrieve()
                .bodyToMono(CodeforcesUserInfoResponse.class)
                .block();

        CodeforcesRatingDTO ratinginfo = CodeforcesClient.get()
                .uri("/user.rating?handle=" + username)
                .retrieve()
                .bodyToMono(CodeforcesRatingDTO.class)
                .block();
        if (ratinginfo != null && userinfo != null && userinfo.getResult() != null && !userinfo.getResult().isEmpty()) {
            CodeforcesUserInfoResponse.Result info = userinfo.getResult().get(0);
            Codeforces codeforces = Codeforces.builder()
                    .handle(info.getHandle())
                    .rating(info.getRating())
                    .maxRating(info.getMaxRating())
                    .globalrank(info.getRank())
                    .lastUpdated(LocalDateTime.now())
                    .user(user)
                    .CodeforcesRatings(ratinginfo.getResult().stream().map(r -> Rating.builder()
                                    .ratingDate(Instant.ofEpochSecond(r.getRatingUpdateTimeSeconds())
                                            .atZone(ZoneId.systemDefault())
                                            .toLocalDate())
                                    .rating(r.getNewRating())
                                    .build()

                            )
                            .collect(Collectors.toList()))
                    .build();

            Codeforces existing = codeforcesRepository.findByHandle(codeforces.getHandle());
            if(existing != null) {
                codeforces.setId(existing.getId());
            }
            codeforcesRepository.save(codeforces);
        }

    }

    @Override
    public void schedule() {

    }

    @Override
    public Codeforces getCodeforces(User user) {
        return codeforcesRepository.getCodeforcesByUser(user);
    }
}
