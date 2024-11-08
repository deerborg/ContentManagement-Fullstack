package dev.deerops.contentmanagementapi.user.model.converter;

import dev.deerops.contentmanagementapi.user.model.dto.request.CreateNewUserRequest;
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

}
