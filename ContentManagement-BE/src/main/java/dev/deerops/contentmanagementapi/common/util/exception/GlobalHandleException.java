package dev.deerops.contentmanagementapi.common.util.exception;

import dev.deerops.contentmanagementapi.common.util.result.ApiResponse;
import dev.deerops.contentmanagementapi.common.util.result.ApiResponseHelper;
import dev.deerops.contentmanagementapi.content.model.util.exception.ContentLimitExceededException;
import dev.deerops.contentmanagementapi.content.model.util.exception.NotFoundContent;
import dev.deerops.contentmanagementapi.content.model.util.exception.NullOrEmptyContentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandleException {

    @ExceptionHandler(NullOrEmptyContentException.class)
    public ResponseEntity<?> handleNullOrEmptyContentException() {
        return new ResponseEntity<>(ApiResponseHelper.NULL_OR_EMPTY_CONTENT(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundContent.class)
    public ResponseEntity<?> handleNotFoundContentException() {
        return new ResponseEntity<>(ApiResponseHelper.NOT_FOUND_CONTENT(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ContentLimitExceededException.class)
    public ResponseEntity<?> handleContentLimitExceededException() {
        return new ResponseEntity<>(ApiResponseHelper.EXCEEDED_CONTENT(), HttpStatus.BAD_REQUEST);
    }

}
