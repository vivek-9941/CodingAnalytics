package org.vivek.platform.Service;

import org.vivek.platform.Model.Gfg.GfgProfile;
import org.vivek.platform.Model.User;


public interface GfgService {
    void fetchapi(String username, User user);
    boolean schedule(GfgProfile gfg);
    GfgProfile getgfg(String Username,User user);
}
