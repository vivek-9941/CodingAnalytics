package org.vivek.platform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vivek.platform.Model.Codechef.Codechef;
import org.vivek.platform.Model.User;
import org.vivek.platform.Service.CodechefService;

@RestController
@RequestMapping("/api/codechef")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class CodechefController {
    @Autowired
    CodechefService codechefService;

    @PostMapping("/get")
    public ResponseEntity<?> getCodechef(@RequestParam String handle, @RequestBody User user) {
        Codechef result =  codechefService.getCodechef(handle, user);
        if(result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
