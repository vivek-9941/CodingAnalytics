package org.vivek.platform.Service;

import org.vivek.platform.Model.Codeforces.Codeforces;
import org.vivek.platform.Model.User;

public interface CodeforcesService {
    void fetchapi(String username, User user);
    void schedule();
    Codeforces getCodeforces(User user);

}
