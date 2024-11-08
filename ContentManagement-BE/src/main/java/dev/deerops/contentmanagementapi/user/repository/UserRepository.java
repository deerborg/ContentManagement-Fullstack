package dev.deerops.contentmanagementapi.user.repository;

import dev.deerops.contentmanagementapi.user.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {
    UserEntity findByUsername(String username);
}
