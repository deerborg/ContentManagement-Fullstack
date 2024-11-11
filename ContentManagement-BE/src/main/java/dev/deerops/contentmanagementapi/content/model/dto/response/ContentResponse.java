package dev.deerops.contentmanagementapi.content.model.dto.response;

import java.time.LocalDate;

public class ContentResponse {

    private String contentTitle;

    private String contentDescription;

    private LocalDate publishDate;

    public ContentResponse() {
    }

    public ContentResponse(String contentTitle, String contentDescription, LocalDate publishDate) {
        this.contentTitle = contentTitle;
        this.contentDescription = contentDescription;
        this.publishDate = publishDate;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
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
