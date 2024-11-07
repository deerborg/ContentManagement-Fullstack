package dev.deerops.contentmanagementapi.content.model.dto.request;

import java.time.LocalDate;

public class UpdateContentAllDetailsRequest {

    private String contentId;

    private String contentTitle;

    private String contentDescription;

    private LocalDate publishDate;

    private LocalDate unpublishDate;

    private boolean visibleContent;

    public UpdateContentAllDetailsRequest(String contentId, String contentTitle, String contentDescription, LocalDate publishDate, LocalDate unpublishDate, boolean visibleContent) {
        this.contentId = contentId;
        this.contentTitle = contentTitle;
        this.contentDescription = contentDescription;
        this.publishDate = publishDate;
        this.unpublishDate = unpublishDate;
        this.visibleContent = visibleContent;
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
}
