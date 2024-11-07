package dev.deerops.contentmanagementapi.content.model.dto.request;

import jakarta.persistence.Lob;

import java.time.LocalDate;

public class UpdateNewlyAddedContentRequest {

    private String contentId;

    private String contentTitle;

    private String contentDescription;

    public UpdateNewlyAddedContentRequest(String contentId, String contentTitle, String contentDescription) {
        this.contentId = contentId;
        this.contentTitle = contentTitle;
        this.contentDescription = contentDescription;
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
}
