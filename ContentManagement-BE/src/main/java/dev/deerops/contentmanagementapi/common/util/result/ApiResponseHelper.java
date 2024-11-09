package dev.deerops.contentmanagementapi.common.util.result;

import dev.deerops.contentmanagementapi.content.model.dto.response.ContentResponse;

import java.util.List;

public class ApiResponseHelper {

    // Success Response

    public static <T> ApiResponse<T> CREATE(T data) {
        return new ApiResponse<>(true, "Created data", data);
    }

    public static <T> ApiResponse<T>  UPDATE(T data) {
        return new ApiResponse<>(true, "Updated data", data);
    }

    public static <T> ApiResponse<T> GET_CONTENT(T data) {
        return new ApiResponse<>(true, "Content is", data);
    }

    public static <T> ApiResponse <T> GET_CONTENT_LIST(T data) {
        return new ApiResponse<>(true, "Content list", data);
    }

    public static <T> ApiResponse <T> GET_USER(T data) {
        return new ApiResponse<>(true, "User is", data);
    }

    public static <T>  ApiResponse <T> ONLINE_USER_LIST(T data) {
        return new ApiResponse<>(true, "Content list",data);
    }

    public static  ApiResponse OK() {
        return new ApiResponse<>(true, "Content list");
    }

    // Exceptions Response

    public static ExceptionResponse NULL_OR_EMPTY_CONTENT() {
        return new ExceptionResponse(false, "Null or empty content");
    }

    public static ExceptionResponse NOT_FOUND_CONTENT() {
        return new ExceptionResponse(false, "Not found content");
    }

    public static ExceptionResponse EXCEEDED_CONTENT(){
        return new ExceptionResponse(false, "Exceeded content, max 5");
    }

    public static ExceptionResponse NULL_OR_EMPTY_USER_FIELD() {
        return new ExceptionResponse(false, "Null or empty user field");
    }

    public static ExceptionResponse INVALID_MAIL_FORMAT() {
        return new ExceptionResponse(false, "Invalid mail format");
    }

    public static ExceptionResponse INVALID_PHONE_FORMAT() {
        return new ExceptionResponse(false, "Invalid phone format");
    }

    public static ExceptionResponse UN_UNIQUE_USERNAME() {
        return new ExceptionResponse(false, "Username already exist");
    }

    public static ExceptionResponse NOT_FOUND_USER() {
        return new ExceptionResponse(false, "Not found user");
    }
}
