//package org.vivek.platform.utility;
//
//import org.vivek.platform.Model.AppUser;
//import org.vivek.platform.Service.AppUserService;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//@Component
//public class Utility_class {
//
//    public static String getCurrentUsername() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || !authentication.isAuthenticated() ||
//                authentication instanceof AnonymousAuthenticationToken) {
//            throw new IllegalStateException("No authenticated user found");
//        }
//
//        return authentication.getName();
//    }
//
//    public AppUser getCurrentUser(AppUserService appUserService) {
//        String username = getCurrentUsername();
//        return appUserService.findByUsername(username);
//
//    }
//}