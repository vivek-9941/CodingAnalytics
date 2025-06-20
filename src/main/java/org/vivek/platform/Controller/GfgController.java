package org.vivek.platform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vivek.platform.Model.Gfg.GfgProfile;
import org.vivek.platform.Model.User;
import org.vivek.platform.Service.GfgService;

@RestController
@RequestMapping("/api/gfg")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class GfgController {
    @Autowired
    GfgService gfgService;

    @PostMapping("/get")
    public ResponseEntity<?> getCodechef(@RequestParam String handle, @RequestBody User user) {
        GfgProfile result =  gfgService.getgfg(handle, user);
        if(result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
