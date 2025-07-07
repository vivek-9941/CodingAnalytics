package org.vivek.platform.Service.Implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.vivek.platform.Model.Leetcode.Leetcode;
import org.vivek.platform.Model.User;
import org.vivek.platform.Repository.LeetcodeRepository;
import org.vivek.platform.Service.DTO.LeetcodeMappingService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LeetcodeServiceImplTest {
    @Mock
    private LeetcodeClient client;
    @Mock private LeetcodeMappingService mappingService;
    @Mock private LeetcodeRepository leetcodeRepository;
    @Mock private ObjectMapper objectMapper;

    @InjectMocks
    private LeetcodeServiceImpl leetcodeService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testSchedule_morethan24hrs(){
        Leetcode lc = new Leetcode();
        lc.setLastUpdated(LocalDateTime.now().minusHours(36));
        assertTrue(leetcodeService.schedule(lc));
    }

    @Test
    void testSchedule_lessthan24hrs(){
        Leetcode lc = new Leetcode();
        lc.setLastUpdated(LocalDateTime.now().minusHours(6));
        assertFalse(leetcodeService.schedule(lc));
    }
//test for when already present and no need for fetching new record
@Test
void testLeetcodeExisting_Cached() throws JsonProcessingException {
    Leetcode lc = new Leetcode();
    lc.setLastUpdated(LocalDateTime.now());

    User user = new User();
    String handle = "vivek";

    Mockito.when(leetcodeRepository.findByUser(any(User.class))).thenReturn(lc);

    Leetcode result = leetcodeService.getLeetcode(handle, user);
//    System.out.println(result);
    assertEquals(lc, result);
}


}