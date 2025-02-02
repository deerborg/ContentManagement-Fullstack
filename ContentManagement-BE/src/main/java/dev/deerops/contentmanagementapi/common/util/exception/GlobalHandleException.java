package dev.deerops.contentmanagementapi.common.util.exception;


import dev.deerops.contenthelper.validation.validExcepiton.*;
import dev.deerops.contentmanagementapi.common.util.result.ApiResponseHelper;
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

    @ExceptionHandler(EmptyOrNullFieldForUserException.class)
    public ResponseEntity<?> handleEmptyOrNullFieldForUserException() {
        return new ResponseEntity<>(ApiResponseHelper.NULL_OR_EMPTY_USER_FIELD(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidMailFormatException.class)
    public ResponseEntity<?> handleInvalidMailFormatException() {
        return new ResponseEntity<>(ApiResponseHelper.INVALID_MAIL_FORMAT(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPhoneFormatException.class)
    public ResponseEntity<?> handleInvalidPhoneFormatException() {
        return new ResponseEntity<>(ApiResponseHelper.INVALID_PHONE_FORMAT(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnUniqueUsernameException.class)
    public ResponseEntity<?> handleUnUniqueUsernameException() {
        return new ResponseEntity<>(ApiResponseHelper.UN_UNIQUE_USERNAME(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<?> handleNotFoundUserException() {
        return new ResponseEntity<>(ApiResponseHelper.NOT_FOUND_USER(), HttpStatus.NOT_FOUND);
    }
}
