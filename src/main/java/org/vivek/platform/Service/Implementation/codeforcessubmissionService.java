package org.vivek.platform.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vivek.platform.Model.Codeforces.CodeforcesSubmissions;
import org.vivek.platform.Model.User;
import org.vivek.platform.Repository.CodeforcesSubmissionRepository;

import java.util.Map;
@Service
public class codeforcessubmissionService {

    @Autowired
    private CodeforcesSubmissionsclient codeforcesService;
    @Autowired
    private CodeforcesSubmissionRepository repo;

    public CodeforcesSubmissions getVerdictRatingStats(String handle, User user) {
    CodeforcesSubmissions submissions = new CodeforcesSubmissions();
        Map<String , Integer> mpp =  codeforcesService.getVerdictWiseStats(handle);
        submissions.setCompilationError(mpp.getOrDefault("COMPILATION_ERROR", 0));
        submissions.setMemeoryLimit(mpp.getOrDefault("MEMORY_LIMIT_EXCEEDED", 0));
        submissions.setUser(user);
        submissions.setOK(mpp.getOrDefault("OK", 0));
        submissions.setRuntimeError(mpp.getOrDefault("RUNTIME_ERROR", 0));
        submissions.setTimeLimit(mpp.getOrDefault("TIME_LIMIT_EXCEEDED", 0));
        submissions.setWrongAnswer(mpp.getOrDefault("WRONG_ANSWER", 0));



        Map<Integer , Integer> map=  codeforcesService.getSolvedRatingCountInRange(handle, 800, 1800);
        submissions.set_800(map.getOrDefault("800", 0));
        submissions.set_900(map.getOrDefault("900", 0));
        submissions.set_1000(map.getOrDefault("1000", 0));
        submissions.set_1100(map.getOrDefault("1100", 0));
        submissions.set_1200(map.getOrDefault("1200", 0));
        submissions.set_1300(map.getOrDefault("1300", 0));
        submissions.set_1400(map.getOrDefault("1400", 0));
        submissions.set_1500(map.getOrDefault("1500", 0));
        submissions.set_1600(map.getOrDefault("1600", 0));
        submissions.set_1700(map.getOrDefault("1700", 0));
        submissions.set_1800(map.getOrDefault("1800", 0));

        repo.save(submissions);
        return submissions;
    }


}
