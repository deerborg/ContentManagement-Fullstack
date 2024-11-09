package dev.deerops.contentmanagementapi.user.service;

import dev.deerops.contentmanagementapi.common.util.result.ApiResponse;
import dev.deerops.contentmanagementapi.user.model.dto.request.CreateNewUserRequest;
import dev.deerops.contentmanagementapi.user.model.dto.response.UserResponse;
import dev.deerops.contentmanagementapi.user.model.entity.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<ApiResponse<UserResponse>> createNewUser(CreateNewUserRequest request);
}
