package dev.deerops.contentmanagementapi.common.util.result;

import dev.deerops.contentmanagementapi.content.model.dto.response.ContentResponse;

import java.util.List;

public class ApiResponseHelper {

    // Success Response

    public static <T> ApiResponse<T> CREATE(T data) {
        return new ApiResponse<>(true, "Create content", data);
    }

    public static <T> ApiResponse<T>  UPDATE(T data) {
        return new ApiResponse<>(true, "Update content", data);
    }

    public static <T> ApiResponse<T> GET_CONTENT(T data) {
        return new ApiResponse<>(true, "Content is", data);
    }

    public static <T> ApiResponse <T> GET_CONTENT_LIST(T data) {
        return new ApiResponse<>(true, "Content list", data);
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


}
