package dev.deerops.contentmanagementapi.content.model.util.exception;

public class NotFoundContent extends RuntimeException {
    public NotFoundContent(String message) {
        super(message);
    }

    public NotFoundContent() {
    }
}
