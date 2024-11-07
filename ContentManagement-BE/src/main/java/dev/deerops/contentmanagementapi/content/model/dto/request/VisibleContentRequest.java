package dev.deerops.contentmanagementapi.content.model.dto.request;

public class VisibleContentRequest {
    private String contentId;

    private boolean visibleContent;

    public VisibleContentRequest(String contentId, boolean visibleContent) {
        this.contentId = contentId;
        this.visibleContent = visibleContent;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public boolean isVisibleContent() {
        return visibleContent;
    }

    public void setVisibleContent(boolean visibleContent) {
        this.visibleContent = visibleContent;
    }
}
