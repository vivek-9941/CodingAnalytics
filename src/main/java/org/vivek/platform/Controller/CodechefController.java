package org.vivek.platform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vivek.platform.Model.Codechef.Codechef;
import org.vivek.platform.Model.User;
import org.vivek.platform.Service.CodechefService;

@RestController
@RequestMapping("/api/codechef")
public class CodechefController {
    @Autowired
    CodechefService codechefService;

    @PostMapping("fetch")
    public ResponseEntity<?> getCodechef(String username, User user) {
        Codechef result =  codechefService.getCodechef(username, user);
        if(result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
