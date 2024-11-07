package dev.deerops.contentmanagementapi.content.model.util.exception;

public class ContentLimitExceededException extends RuntimeException{
    public ContentLimitExceededException(String message) {
        super(message);
    }

    public ContentLimitExceededException() {
    }
}
