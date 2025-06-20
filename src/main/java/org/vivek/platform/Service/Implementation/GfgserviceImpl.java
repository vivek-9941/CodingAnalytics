package org.vivek.platform.Service.Implementation;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.vivek.platform.Model.Gfg.GfgDTO;
import org.vivek.platform.Model.Gfg.GfgProfile;
import org.vivek.platform.Model.User;
import org.vivek.platform.Repository.GfgRepository;
import org.vivek.platform.Service.GfgService;

import java.time.LocalDateTime;

@Service
public class GfgserviceImpl implements GfgService {

    private final GfgRepository repo;
    private final WebClient gfgClient;

    public GfgserviceImpl(GfgRepository repo, WebClient gfgClient) {
        this.repo = repo;
        this.gfgClient = gfgClient;
    }

    @Transactional
    @Override
    public void fetchapi(String username, User user) {
        GfgDTO response = gfgClient.get()
                .uri("/" + username)
                .retrieve()
                .bodyToMono(GfgDTO.class)
                .block();

        if (response != null) {
            GfgProfile profile = GfgProfile.builder()
                    .username(response.getInfo().getUserName())
                    .fullName(response.getInfo().getFullName())
                    .profilePicture(response.getInfo().getProfilePicture())
                    .institute(response.getInfo().getInstitute())
                    .instituteRank(response.getInfo().getInstituteRank())
                    .currentStreak(response.getInfo().getCurrentStreak())
                    .maxStreak(response.getInfo().getMaxStreak())
                    .codingScore(response.getInfo().getCodingScore())
                    .monthlyScore(response.getInfo().getMonthlyScore())
                    .totalProblemsSolved(response.getInfo().getTotalProblemsSolved())
                    .basicCount(response.getSolvedStats().getOrDefault("basic", new GfgDTO.DifficultyStats()).getCount())
                    .easyCount(response.getSolvedStats().getOrDefault("easy", new GfgDTO.DifficultyStats()).getCount())
                    .mediumCount(response.getSolvedStats().getOrDefault("medium", new GfgDTO.DifficultyStats()).getCount())
                    .hardCount(response.getSolvedStats().getOrDefault("hard", new GfgDTO.DifficultyStats()).getCount())
                    .build();

            GfgProfile existing = repo.findByUsername(username);
            if (existing != null) {
                profile.setId(existing.getId());

            }
            profile.setLastUpdated(LocalDateTime.now());
            repo.save(profile);
        }
    }

    @Override
    public boolean schedule(GfgProfile gfg) {
        return (LocalDateTime.now().isAfter(gfg.getLastUpdated().plusHours(24)));
    }

    @Override
    public GfgProfile getgfg(String Username, User user) {
        GfgProfile cc = repo.findByUsername(Username);
        if (cc == null) {
            fetchapi(Username, user);
        } else if (schedule(cc)) {
            fetchapi(Username, user);
        } else {
            return cc;
        }
        return repo.findByUsername(Username);
    }

}
