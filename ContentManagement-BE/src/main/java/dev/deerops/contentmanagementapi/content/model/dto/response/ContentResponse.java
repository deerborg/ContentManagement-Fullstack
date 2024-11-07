package dev.deerops.contentmanagementapi.content.model.dto.response;

public class ContentResponse {

    private String contentTitle;

    private String contentDescription;

    public ContentResponse() {
    }

    public ContentResponse(String contentTitle, String contentDescription) {
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
