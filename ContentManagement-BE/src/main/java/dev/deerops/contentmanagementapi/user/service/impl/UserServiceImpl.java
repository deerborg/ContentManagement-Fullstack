package dev.deerops.contentmanagementapi.user.service.impl;

import dev.deerops.contentmanagementapi.common.util.result.ApiResponse;
import dev.deerops.contentmanagementapi.common.util.result.ApiResponseHelper;
import dev.deerops.contentmanagementapi.user.model.converter.UserConverter;
import dev.deerops.contentmanagementapi.user.model.dto.request.CreateNewUserRequest;
import dev.deerops.contentmanagementapi.user.model.dto.response.UserDetailsResponse;
import dev.deerops.contentmanagementapi.user.model.dto.response.UserResponse;
import dev.deerops.contentmanagementapi.user.model.entity.enums.Role;
import dev.deerops.contentmanagementapi.user.model.entity.UserEntity;
import dev.deerops.contentmanagementapi.user.model.util.exception.NotFoundUserException;
import dev.deerops.contentmanagementapi.user.model.util.validation.UserValidation;
import dev.deerops.contentmanagementapi.user.repository.UserRepository;
import dev.deerops.contentmanagementapi.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;
    private final UserValidation userValidation;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter, PasswordEncoder passwordEncoder, UserValidation userValidation) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
        this.userValidation = userValidation;
    }

    @Override
    public ResponseEntity<ApiResponse<UserResponse>> createUser(CreateNewUserRequest request) {

        validateUserRequest(request);

        UserEntity userEntity = userConverter.fromCrateNewUserRequestToEntity(request);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setCreationDate(LocalDate.now());

        setCreateDefaultAccount(userEntity);
        createContentSharingLimit(userEntity);

        UserResponse userResponse = userConverter.fromEntityToUserResponse(userRepository.save(userEntity));

        return new ResponseEntity<>(ApiResponseHelper.CREATE(userResponse), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllOnlineUser() {
        List<UserResponse> userResponseList = userRepository.findByOnlineTrue().stream().map(userConverter::fromEntityToUserResponse).toList();
        return new ResponseEntity<>(ApiResponseHelper.ONLINE_USER_LIST(userResponseList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<UserDetailsResponse>> getUserDetailsByUseNamed(String username) {
        UserDetailsResponse userDetailsResponse =
                userConverter.fromEntityToUserDetailsResponse(findByUsernameOrThrow(username));
        return new ResponseEntity<>(ApiResponseHelper.GET_USER(userDetailsResponse),HttpStatus.OK);
    }

    private void setCreateDefaultAccount(UserEntity userEntity) {

        userEntity.setRole(Collections.singletonList(Role.ADMIN));
        userEntity.setAccountNonExpired(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setCredentialsNonExpired(true);
        userEntity.setEnabled(true);
    }
    private void createContentSharingLimit(UserEntity userEntity) {
        if(userEntity.getRole().contains(Role.MODERATOR) || userEntity.getRole().contains(Role.ADMIN) || userEntity.getRole().contains(Role.STAFF)){
            userEntity.setContentMaxLimit(5);
        }
    }

    private void validateUserRequest(CreateNewUserRequest request) {
        userValidation.checkAllParameterForRequestClass(request);
        userValidation.phoneFormatValidation(request.getPhone());
        userValidation.emailFormatValidation(request.getEmail());
        userValidation.uniqueUserNameValidation(request.getUsername());
    }

    private UserEntity findByUsernameOrThrow(String username){
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new NotFoundUserException();
        }
        return userEntity;
    }


}
