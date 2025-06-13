package org.vivek.platform.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vivek.platform.Model.Leetcode.Leetcode;
import org.vivek.platform.Model.Leetcode.Ratings.RankingInfo;
import org.vivek.platform.Model.User;
import org.vivek.platform.Service.Implementation.LeetcodeRatingserivce;
import org.vivek.platform.Service.Implementation.LeetcodeServiceImpl;

@RestController
@RequestMapping("/api/leetcode")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class LeetcodeController {
    @Autowired
    private LeetcodeRatingserivce ratingserivce;
    @Autowired
    LeetcodeServiceImpl leetcodeService;

    @PostMapping("/ranking")
    public ResponseEntity<?> fetchsubmissions(String username) throws JsonProcessingException {
        RankingInfo info =  ratingserivce.fetchRecent100(username);

        if(info != null) {
            return new ResponseEntity<>(info, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/submission/stats")
    public ResponseEntity<?> getleetcodesubmissionStats(String handle , User user) throws JsonProcessingException {
      Leetcode lc =  leetcodeService.getLeetcode(handle, user);
      if(lc.getSubmissionStats() != null) {
          return new ResponseEntity<>(lc.getSubmissionStats(), HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/tag/stats")
    public ResponseEntity<?> getleetcodetagstats(String handle , User user) throws JsonProcessingException {
        Leetcode lc =  leetcodeService.getLeetcode(handle, user);
        if(lc.getTagStats() != null) {
            return new ResponseEntity<>(lc.getTagStats(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/get")
    public ResponseEntity<?> getleetcode(@RequestParam String handle ,@RequestBody  User user) throws JsonProcessingException {
        Leetcode lc =  leetcodeService.getLeetcode(handle, user);
        System.out.println("leetcodesent");
        if(lc!= null) {
            return new ResponseEntity<>(lc, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
