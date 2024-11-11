/*
 * Copyright (c) 2024. Furkan Aydemir.
 *
 * This file is part of the ContentManagement project. Unauthorized copying, distribution, or transmission of this file,
 * in any form, is strictly prohibited without prior written permission from Furkan Aydemir.
 *
 */

package dev.deerops.contentmanagementapi.user.config;

import dev.deerops.contentmanagementapi.user.model.entity.UserEntity;
import dev.deerops.contentmanagementapi.user.repository.UserRepository;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class UserSessionListener {

    private final UserRepository userRepository;

    public UserSessionListener(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventListener
    public void handleAuthenticationSuccess(AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();
        UserEntity user = userRepository.findByUsername(username);
        if (user != null) {
            user.setOnline(true);
            userRepository.save(user);
        }
    }

    @EventListener
    public void handleLogoutSuccess(LogoutSuccessEvent event) {
        String username = event.getAuthentication().getName();
        UserEntity user = userRepository.findByUsername(username);
        if (user != null) {
            user.setOnline(false);
            user.setLastLoginDate(LocalDateTime.now());
            userRepository.save(user);
        }
    }

}
