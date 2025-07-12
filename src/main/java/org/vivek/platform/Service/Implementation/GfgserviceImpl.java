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

        if (response == null || response.getInfo() == null) {
            System.out.println("GFG API returned null or missing 'info' for user: " + username);
            return; // Or log and skip
        }

        GfgDTO.Info info = response.getInfo();
        GfgDTO.DifficultyStats defaultStats = new GfgDTO.DifficultyStats();

        GfgProfile profile = GfgProfile.builder()
                .username(info.getUserName())
                .fullName(info.getFullName())
                .profilePicture(info.getProfilePicture())
                .institute(info.getInstitute())
                .instituteRank(info.getInstituteRank())
                .currentStreak(info.getCurrentStreak())
                .maxStreak(info.getMaxStreak())
                .codingScore(info.getCodingScore())
                .monthlyScore(info.getMonthlyScore())
                .totalProblemsSolved(info.getTotalProblemsSolved())
                .basicCount(response.getSolvedStats() != null ?
                        response.getSolvedStats().getOrDefault("basic", defaultStats).getCount() : 0)
                .easyCount(response.getSolvedStats() != null ?
                        response.getSolvedStats().getOrDefault("easy", defaultStats).getCount() : 0)
                .mediumCount(response.getSolvedStats() != null ?
                        response.getSolvedStats().getOrDefault("medium", defaultStats).getCount() : 0)
                .hardCount(response.getSolvedStats() != null ?
                        response.getSolvedStats().getOrDefault("hard", defaultStats).getCount() : 0)
                .build();

        GfgProfile existing = repo.findByUsername(username);
        if (existing != null) {
            profile.setId(existing.getId());
        }

        profile.setLastUpdated(LocalDateTime.now());
        repo.save(profile);
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
