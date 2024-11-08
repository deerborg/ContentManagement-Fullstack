package dev.deerops.contentmanagementapi.content.service;

import dev.deerops.contentmanagementapi.common.util.result.ApiResponse;
import dev.deerops.contentmanagementapi.content.model.dto.request.CreateNewContentRequest;
import dev.deerops.contentmanagementapi.content.model.dto.request.UpdateContentAllDetailsRequest;
import dev.deerops.contentmanagementapi.content.model.dto.request.UpdateNewlyAddedContentRequest;
import dev.deerops.contentmanagementapi.content.model.dto.request.VisibleContentRequest;
import dev.deerops.contentmanagementapi.content.model.dto.response.ContentDetailsResponse;
import dev.deerops.contentmanagementapi.content.model.dto.response.ContentResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ContentService {

    ResponseEntity<ApiResponse<ContentResponse>> createNewContent(CreateNewContentRequest createNewContentRequest);

    ResponseEntity<ApiResponse<ContentResponse>> updateNewlyAddedContent(UpdateNewlyAddedContentRequest updateNewlyAddedContentRequest);

    ResponseEntity<ApiResponse<ContentResponse>> updateAllDetailContent(UpdateContentAllDetailsRequest updateContentAllDetailsRequest);

    ResponseEntity<ApiResponse<ContentResponse>> getContentByContentIdForPath(String contentId);

    ResponseEntity<ApiResponse<List<ContentResponse>>> getAllContentInContentResponse();

    ResponseEntity<ApiResponse<ContentDetailsResponse>> getContentDetailsByContentIdForPath(String contentId);

    ResponseEntity<ApiResponse<List<ContentDetailsResponse>>> getAllContentInContentDetailsResponse();

    ResponseEntity<ApiResponse<ContentResponse>> publishContent(VisibleContentRequest visibleContentRequest);

    ResponseEntity<ApiResponse<List<ContentResponse>>> getJustPublishedContents();

    ResponseEntity<ApiResponse<List<ContentResponse>>> getJustUnpublishedContents();

    ResponseEntity<ApiResponse<ContentResponse>> unpublishContent(VisibleContentRequest visibleContentRequest);

    ResponseEntity<ApiResponse<?>> deleteByIdForContent(String contentId);

}
