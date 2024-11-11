/*
 * Copyright (c) 2024. Furkan Aydemir.
 *
 * This file is part of the ContentManagement project. Unauthorized copying, distribution, or transmission of this file,
 * in any form, is strictly prohibited without prior written permission from Furkan Aydemir.
 *
 */

package dev.deerops.contentmanagementapi.user.controller;

import dev.deerops.contentmanagementapi.common.util.result.ApiResponse;
import dev.deerops.contentmanagementapi.user.model.dto.request.LoginRequest;
import dev.deerops.contentmanagementapi.user.model.dto.response.UserResponse;
import dev.deerops.contentmanagementapi.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/public/login")
    public ResponseEntity<ApiResponse<UserResponse>> login(@RequestBody LoginRequest loginRequest) {
        return userService.authenticateUser(loginRequest);
    }
}
