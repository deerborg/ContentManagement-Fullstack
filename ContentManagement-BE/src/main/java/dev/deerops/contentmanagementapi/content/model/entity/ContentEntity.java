package dev.deerops.contentmanagementapi.content.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.deerops.contentmanagementapi.user.model.entity.UserEntity;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;

@Entity
@Table(name = "content")
public class ContentEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String contentId;

    private String contentTitle;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String contentDescription;

    private LocalDate publishDate;

    private LocalDate unpublishDate;

    private boolean visibleContent;

    @ManyToOne
    @JsonIgnore
    private UserEntity user;

    public ContentEntity(String contentId, String contentTitle, String contentDescription, LocalDate publishDate, LocalDate unpublishDate, boolean visibleContent, UserEntity user) {
        this.contentId = contentId;
        this.contentTitle = contentTitle;
        this.contentDescription = contentDescription;
        this.publishDate = publishDate;
        this.unpublishDate = unpublishDate;
        this.visibleContent = visibleContent;
        this.user = user;
    }



    public ContentEntity() {
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentDescription() {
        return contentDescription;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public LocalDate getUnpublishDate() {
        return unpublishDate;
    }

    public void setUnpublishDate(LocalDate unpublishDate) {
        this.unpublishDate = unpublishDate;
    }

    public boolean isVisibleContent() {
        return visibleContent;
    }

    public void setVisibleContent(boolean visibleContent) {
        this.visibleContent = visibleContent;
    }

    @Override
    public String toString() {
        return "ContentEntity{" +
                "contentId='" + contentId + '\'' +
                ", contentTitle='" + contentTitle + '\'' +
                ", contentDescription='" + contentDescription + '\'' +
                ", publishDate=" + publishDate +
                ", unpublishDate=" + unpublishDate +
                ", visibleContent=" + visibleContent +
                ", user=" + user +
                '}';
    }
}
