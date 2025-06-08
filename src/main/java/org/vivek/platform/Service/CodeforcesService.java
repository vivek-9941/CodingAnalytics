package org.vivek.platform.Service;

import org.aspectj.apache.bcel.classfile.Code;
import org.vivek.platform.Model.Codeforces.Codeforces;
import org.vivek.platform.Model.User;

public interface CodeforcesService {
    void fetchapi(String username, User user);
    boolean schedule(Codeforces codeforces);
    Codeforces getCodeforces(String username,User user);

}
