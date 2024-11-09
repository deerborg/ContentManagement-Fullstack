package dev.deerops.contentmanagementapi.user.controller;

import dev.deerops.contentmanagementapi.common.util.result.ApiResponse;
import dev.deerops.contentmanagementapi.user.model.dto.request.CreateNewUserRequest;
import dev.deerops.contentmanagementapi.user.model.dto.response.UserDetailsResponse;
import dev.deerops.contentmanagementapi.user.model.dto.response.UserResponse;
import dev.deerops.contentmanagementapi.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/public/create")
    public ResponseEntity<ApiResponse<UserResponse>> createNewUser(CreateNewUserRequest request){
        return userService.createUser(request);
    }

    @GetMapping("/private/online-users")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllOnlineUser(){
        return userService.getAllOnlineUser();
    }

    @GetMapping("/private/{username}/details")
    public ResponseEntity<ApiResponse<UserDetailsResponse>> getUserDetailsByUseNamed(@PathVariable String username){
        return userService.getUserDetailsByUseNamed(username);
    }
}
