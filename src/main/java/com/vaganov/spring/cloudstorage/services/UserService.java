package com.vaganov.spring.cloudstorage.services;


import com.vaganov.spring.cloudstorage.entities.SystemUser;
import com.vaganov.spring.cloudstorage.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String username);
    boolean save(SystemUser systemUser);
}
