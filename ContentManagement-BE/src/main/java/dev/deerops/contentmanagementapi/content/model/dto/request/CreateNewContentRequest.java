package dev.deerops.contentmanagementapi.content.model.dto.request;

import jakarta.persistence.Lob;

import java.time.LocalDate;

public class CreateNewContentRequest {
    private String contentTitle;

    private String contentDescription;


    public CreateNewContentRequest(String contentTitle, String contentDescription) {
        this.contentTitle = contentTitle;
        this.contentDescription = contentDescription;
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
