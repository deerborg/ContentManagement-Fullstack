package dev.deerops.contentmanagementapi.user.model.converter;

import dev.deerops.contentmanagementapi.user.model.dto.request.CreateNewUserRequest;
import dev.deerops.contentmanagementapi.user.model.dto.response.UserDetailsResponse;
import dev.deerops.contentmanagementapi.user.model.dto.response.UserResponse;
import dev.deerops.contentmanagementapi.user.model.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserEntity fromCrateNewUserRequestToEntity(CreateNewUserRequest request){

        UserEntity userEntity = new UserEntity();

        userEntity.setName(request.getName());

        userEntity.setLastName(request.getLastName());

        userEntity.setEmail(request.getEmail());

        userEntity.setPhone(request.getPhone());

        userEntity.setAddress(request.getAddress());

        userEntity.setUsername(request.getUsername());

        userEntity.setPassword(request.getPassword());

        return userEntity;
    }

    public UserResponse fromEntityToUserResponse(UserEntity entity){

        UserResponse userResponse = new UserResponse();

        userResponse.setUserId(entity.getUserId());

        userResponse.setUsername(entity.getUsername());

        userResponse.setCreationDate(entity.getCreationDate());

        return userResponse;
    }

    public UserDetailsResponse fromEntityToUserDetailsResponse(UserEntity entity){

        UserDetailsResponse userDetailsResponse = new UserDetailsResponse();

        userDetailsResponse.setUserId(entity.getUserId());

        userDetailsResponse.setName(entity.getName());

        userDetailsResponse.setLastName(entity.getLastName());

        userDetailsResponse.setEmail(entity.getEmail());

        userDetailsResponse.setPhone(entity.getPhone());

        userDetailsResponse.setAddress(entity.getAddress());

        userDetailsResponse.setUsername(entity.getUsername());

        userDetailsResponse.setContentMaxLimit(entity.getContentMaxLimit());

        userDetailsResponse.setCreationDate(entity.getCreationDate());

        userDetailsResponse.setAccountNonExpired(entity.isAccountNonExpired());

        userDetailsResponse.setAccountNonLocked(entity.isAccountNonLocked());

        userDetailsResponse.setCredentialsNonExpired(entity.isCredentialsNonExpired());

        userDetailsResponse.setEnabled(entity.isEnabled());

        userDetailsResponse.setOnline(entity.isOnline());

        userDetailsResponse.setLastLoginDate(entity.getLastLoginDate());

        return userDetailsResponse;
    }

}
