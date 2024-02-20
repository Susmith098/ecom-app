package com.susmith.ecomapp.Service;

import com.susmith.ecomapp.Auth.dto.UserDto;
import com.susmith.ecomapp.Auth.dto.UserDtoWithoutRole;
import com.susmith.ecomapp.Repository.UserRepository;
import com.susmith.ecomapp.Entity.Role;
import com.susmith.ecomapp.Entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){

        return userRepository.findAll();

    }


    public User getUserById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }


    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }



    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepository.findByname(name).orElseThrow(()-> new UsernameNotFoundException("Username not found"));
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication: " + authentication);
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        System.out.println("principal: "+ principal);
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            String email = username;
            return userRepository.findByEmail(email).orElse(null);
        }

        return null;
    }
}
