package dev.deerops.contentmanagementapi.content.model.util.exception;

public class NullOrEmptyContentException extends RuntimeException{
    public NullOrEmptyContentException(String message) {
        super(message);
    }

    public NullOrEmptyContentException() {
    }
}
