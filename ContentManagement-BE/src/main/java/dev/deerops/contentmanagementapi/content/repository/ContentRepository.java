package dev.deerops.contentmanagementapi.content.repository;

import dev.deerops.contentmanagementapi.content.model.entity.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<ContentEntity,String> {
    List<ContentEntity> findByVisibleContentIsTrue();
    List<ContentEntity> findByVisibleContentIsFalse();
}
