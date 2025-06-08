package org.vivek.platform.Service;

import org.vivek.platform.Model.Codechef.Codechef;
import org.vivek.platform.Model.Codechef.CodechefRatingDTO;
import org.vivek.platform.Model.Codeforces.Rating;
import org.vivek.platform.Model.User;

import java.util.List;

public interface CodechefService {
    void fetchapi(String username, User user);
    boolean schedule(Codechef codechef);
    List<Rating> mapRating(List<CodechefRatingDTO> list);
    Codechef getCodechef(String Username,User user);
}
