package org.vivek.platform.Service.Implementation;

import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.vivek.platform.Model.Codechef.Codechef;
import org.vivek.platform.Model.Codechef.CodechefDTO;
import org.vivek.platform.Model.Codechef.CodechefRatingDTO;
import org.vivek.platform.Model.Codeforces.Rating;
import org.vivek.platform.Model.User;
import org.vivek.platform.Repository.CodeChefRepository;
import org.vivek.platform.Service.CodechefService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CodechefserviceImpl implements CodechefService {
    private final WebClient CodeChefClient;
    private final CodeChefRepository codeChefRepository;
    public CodechefserviceImpl(@Qualifier("CodeChefClient") WebClient codeChefClient, CodeChefRepository codeChefRepository) {
        this.CodeChefClient = codeChefClient;
        this.codeChefRepository = codeChefRepository;
    }

    @Override
    public void fetchapi(String username, User user) {
        CodechefDTO codechefDTO = CodeChefClient.get()
                .uri("/"+username)
                .retrieve()
                .bodyToMono(CodechefDTO.class)
                .block();

        if(codechefDTO != null) {
            Codechef  codechef = Codechef.builder()
                    .realName(codechefDTO.getName())
                    .Currentrating(codechefDTO.getCurrentRating())
                    .globalRanking(codechefDTO.getGlobalRank())
                    .country(codechefDTO.getCountryName())
                    .lastUpdated(LocalDateTime.now())
                    .CodechefRatings(mapRating(codechefDTO.getRatingData()))
                    .user(user)
                    .handle(username)
                    .build();

            Codechef existing = codeChefRepository.findByUser(user);
            if(existing != null) {
                    codechef.setId(existing.getId());

            }
            user.setCc(codechef);
            codeChefRepository.save(codechef);
        }
    }

    public List<Rating> mapRating(List<CodechefRatingDTO> list){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return list.stream().map(r -> {
            Rating rating = new Rating();
            rating.setRating(Integer.parseInt(r.getRating()));
            LocalDateTime fullDateTime = LocalDateTime.parse(r.getEnd_date(), formatter);
            rating.setRatingDate(fullDateTime.toLocalDate());

            return rating;
        }).collect(Collectors.toList());
    }

    @Override
    public Codechef getCodechef(String username, User user) {
        Codechef cc =  codeChefRepository.findByUser(user);
        if(cc == null) {
            fetchapi(username, user);
        }
        else if(schedule(cc)){
            fetchapi(username, user);
        }
        else{
            return cc;
        }
        return codeChefRepository.findByUser(user);
    }

    @Override
    public boolean schedule( Codechef codechef) {
        return (LocalDateTime.now().isAfter(codechef.getLastUpdated().plusHours(24))) ;
    }
}
