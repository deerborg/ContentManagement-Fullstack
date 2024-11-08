package dev.deerops.contentmanagementapi.user.model.dto.response;

import java.time.LocalDate;

public class UserResponse {

    private String userId;

    private String username;

    private LocalDate creationDate;


    public UserResponse(String userId, String username, LocalDate creationDate) {
        this.userId = userId;
        this.username = username;
        this.creationDate = creationDate;
    }

    public UserResponse() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
