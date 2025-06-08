package org.vivek.platform.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.vivek.platform.Model.Leetcode.Leetcode;
import org.vivek.platform.Model.User;

public interface LeetcodeService {
    void fetchapi(String username, User user) throws JsonProcessingException;
    Leetcode getLeetcode(String username  ,User user) throws JsonProcessingException;
    boolean schedule(Leetcode leetcode);
}
