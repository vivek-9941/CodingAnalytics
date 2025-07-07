package org.vivek.platform.Service.Implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.vivek.platform.Model.Gfg.GfgDTO;
import org.vivek.platform.Model.Gfg.GfgProfile;
import org.vivek.platform.Model.User;
import org.vivek.platform.Repository.GfgRepository;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GfgserviceImplTest {
    @Mock
    private GfgRepository repo;
    @Mock
    private WebClient weblcClient;
    @Mock
    private WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec;
    @Mock
    private WebClient.RequestHeadersSpec<?> requestHeadersSpec;
    @Mock
    private WebClient.ResponseSpec responseSpec;
    @InjectMocks
    private GfgserviceImpl gfgservice;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testScheduleReturnTrue(){
        GfgProfile profile = new GfgProfile();
        profile.setLastUpdated(LocalDateTime.now().minusHours(26));
        assertTrue(gfgservice.schedule(profile));
    }


    @Test
    void testScheduleReturnFalse(){
        GfgProfile profile = new GfgProfile();
        profile.setLastUpdated(LocalDateTime.now().minusHours(6));
        assertFalse(gfgservice.schedule(profile));
    }

    @Test
    void testGet_DataFromRepoFetch(){
        String username = "test1";
        GfgProfile profile = new GfgProfile();
        profile.setLastUpdated(LocalDateTime.now().minusHours(1));
        Mockito.when(repo.findByUsername(username)).thenReturn(profile);
        GfgProfile result = gfgservice.getgfg(username, new User());
        assertEquals(profile, result);
    }


}