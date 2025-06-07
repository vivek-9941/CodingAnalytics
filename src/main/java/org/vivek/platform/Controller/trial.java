package org.vivek.platform.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vivek.platform.Model.Leetcode.LeetCodeSubmissionStats;
import org.vivek.platform.Model.Leetcode.Leetcode;
import org.vivek.platform.Model.User;
import org.vivek.platform.Repository.LeetcodeRepository;
import org.vivek.platform.Service.Implementation.CodeforcesServiceImpl;
import org.vivek.platform.Service.Implementation.LeetcodeClient;
import org.vivek.platform.Service.Implementation.LeetcodeServiceImpl;
import org.vivek.platform.Service.Implementation.UserServiceImpl;
import org.vivek.platform.Service.UserService;

@RestController
public class trial {
    @Autowired
    LeetcodeServiceImpl client;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    CodeforcesServiceImpl codeforcesService;

    @PostMapping("/leet")
    public ResponseEntity<?> getUserFullProfile(@RequestParam String username) throws JsonProcessingException {
        User newUser = User.builder()
                .email("vivek@example.com")
                .username("Vivek Sharma")
                .build();
        userService.save(newUser);
        client.fetchapi(username, newUser);
        codeforcesService.fetchapi("vivek_9941", newUser);
        return null;
    }

    @Autowired
    private LeetcodeRepository repo;

    @GetMapping("/get")
    public void getprofile() {

        User user = new User();
        user.setUsername("Vivek Sharma");
        user.setEmail("vivek@example.com");
        user.setId(3L);

        User user2 = new User();
        user2.setUsername("Vivek Sharma");
        user2.setEmail("vivek@example.com");
        user2.setId(2L);

//
//        Leetcode lcv = repo.findByHandleAndUser("vivek_M5CS", user).orElseThrow();
//        Leetcode lck = repo.findByHandleAndUser("kartik_mane", user2).orElseThrow();

// ✅ Update fields manually or via method

// ... update whatever else is needed

//        repo.save(lcv); // ✅ Hibernate will treat this as an UPDATE

        /// now just copy all the values from exxisting to new 
    }
}
