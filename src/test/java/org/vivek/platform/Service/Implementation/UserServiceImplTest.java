package org.vivek.platform.Service.Implementation;

import org.aspectj.weaver.patterns.IVerificationRequired;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.vivek.platform.Model.User;
import org.vivek.platform.Repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testSave(){
        User user = new User();
        user.setUsername("vivek");
        user.setEmail("vivek@gmail.com");
        userService.save(user);
        verify(userRepository, times(1)).save(user);
    }
    @Test
    void testCheckAlreadyPresent_username(){
        Mockito.when(userRepository.findByUsername("vivek")).thenReturn(new User());
        Mockito.when(userRepository.findByEmail("vivek@gmail.com")).thenReturn(null);
        boolean result = userService.checkAlreadyPresent("vivek", "vivek@gmail.com");
        assertTrue(result);
    }

    @Test
    void testCheckAlreadyPresent_email(){
        Mockito.when(userRepository.findByUsername("vivek")).thenReturn(null);
        Mockito.when(userRepository.findByEmail("vivek@gmail.com")).thenReturn(new User());
        boolean result = userService.checkAlreadyPresent("vivek", "vivek@gmail.com");
        assertTrue(result);
    }

    @Test
    void testCheckNoUserPresent(){
        Mockito.when(userRepository.findByUsername("vivek")).thenReturn(null);
        Mockito.when(userRepository.findByEmail("vivek@gmail.com")).thenReturn(null);
        boolean result = userService.checkAlreadyPresent("vivek", "vivek@gmail.com");
        assertFalse(result);
    }

    @Test
    void testFindByEmail(){

        User user = new User();
        user.setEmail("vivek@gmail.com");
        Mockito.when(userRepository.findByEmail("vivek@gmail.com")).thenReturn(user);

        User result = userService.findByEmail("vivek@gmail.com");
        assertNotNull(result);
        assertEquals("vivek@gmail.com", result.getEmail());
    }
}