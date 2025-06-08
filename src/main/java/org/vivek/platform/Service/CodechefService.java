package org.vivek.platform.Service;

import org.vivek.platform.Model.Codechef.CodechefRatingDTO;
import org.vivek.platform.Model.Codeforces.Rating;
import org.vivek.platform.Model.User;

import java.util.List;

public interface CodechefService {
    void fetchapi(String username, User user);
    void schedule();
    List<Rating> mapRating(List<CodechefRatingDTO> list);
}
