package dev.deerops.contentmanagementapi.user.service.impl;

import dev.deerops.contentmanagementapi.common.util.result.ApiResponse;
import dev.deerops.contentmanagementapi.common.util.result.ApiResponseHelper;
import dev.deerops.contentmanagementapi.user.model.converter.UserConverter;
import dev.deerops.contentmanagementapi.user.model.dto.request.CreateNewUserRequest;
import dev.deerops.contentmanagementapi.user.model.dto.response.UserResponse;
import dev.deerops.contentmanagementapi.user.model.entity.Role;
import dev.deerops.contentmanagementapi.user.model.entity.UserEntity;
import dev.deerops.contentmanagementapi.user.model.util.validation.UserValidation;
import dev.deerops.contentmanagementapi.user.repository.UserRepository;
import dev.deerops.contentmanagementapi.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;

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
    public ResponseEntity<ApiResponse<UserResponse>> createNewUser(CreateNewUserRequest request) {

        userValidation.checkAllParameterForRequestClass(request);
        userValidation.phoneFormatValidation(request.getPhone());
        userValidation.emailFormatValidation(request.getEmail());

        UserEntity userEntity = userConverter.fromCrateNewUserRequestToEntity(request);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setCreationDate(LocalDate.now());

        setCreateDefaultAccount(userEntity);

        UserResponse userResponse = userConverter.fromEntityToUserResponse(userRepository.save(userEntity));

        return new ResponseEntity<>(ApiResponseHelper.CREATE(userResponse), HttpStatus.CREATED);
    }

    private void setCreateDefaultAccount(UserEntity userEntity) {

        userEntity.setRole(Collections.singletonList(Role.ADMIN));
        userEntity.setAccountNonExpired(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setCredentialsNonExpired(true);
        userEntity.setEnabled(true);
    }


}
