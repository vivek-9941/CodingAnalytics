package org.vivek.platform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vivek.platform.Model.Codeforces.Codeforces;
import org.vivek.platform.Model.Codeforces.CodeforcesSubmissions;
import org.vivek.platform.Model.User;
import org.vivek.platform.Service.CodeforcesService;
import org.vivek.platform.Service.Implementation.codeforcessubmissionService;

@RestController
@RequestMapping("/api/codeforces")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class CodeforcesController {
    @Autowired
    private CodeforcesService codeforcesService;

    @Autowired
    codeforcessubmissionService codeforcessubmissionService;

    @PostMapping("/get")
    public ResponseEntity<?> getCodeforces(@RequestParam String handle,@RequestBody User user) {
        Codeforces cf =codeforcesService.getCodeforces(handle,user);
        if(cf != null){
            return ResponseEntity.ok(cf);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/submission")
    public ResponseEntity<?> getcodeforcesSubmissions(String handle, User user) {
        CodeforcesSubmissions cf  = codeforcessubmissionService.getVerdictRatingStats(handle,user);
        if(cf != null){
            return ResponseEntity.ok(cf);
        }
        return ResponseEntity.notFound().build();
    }

}
