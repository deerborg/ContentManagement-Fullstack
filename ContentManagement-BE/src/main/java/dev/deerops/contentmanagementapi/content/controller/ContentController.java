package dev.deerops.contentmanagementapi.content.controller;

import dev.deerops.contentmanagementapi.common.util.result.ApiResponse;
import dev.deerops.contentmanagementapi.content.config.ContentEndPointConstants;
import dev.deerops.contentmanagementapi.content.model.dto.request.CreateNewContentRequest;
import dev.deerops.contentmanagementapi.content.model.dto.request.UpdateContentAllDetailsRequest;
import dev.deerops.contentmanagementapi.content.model.dto.request.UpdateNewlyAddedContentRequest;
import dev.deerops.contentmanagementapi.content.model.dto.request.VisibleContentRequest;
import dev.deerops.contentmanagementapi.content.model.dto.response.ContentDetailsResponse;
import dev.deerops.contentmanagementapi.content.model.dto.response.ContentResponse;
import dev.deerops.contentmanagementapi.content.service.ContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ContentEndPointConstants.BASE_CONTENT_PATH)
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping(ContentEndPointConstants.CREATE_NEW_CONTENT)
    public ResponseEntity<ApiResponse<ContentResponse>> createNewContent(@RequestBody CreateNewContentRequest contentRequest) {
        return contentService.createNewContent(contentRequest);
    }

    @GetMapping(ContentEndPointConstants.GET_CONTENT_BY_CONTENT_ID)
    public ResponseEntity<ApiResponse<ContentResponse>> getContentByContentIdForPath(@PathVariable String contentId) {
        return contentService.getContentByContentIdForPath(contentId);
    }

    @GetMapping(ContentEndPointConstants.GET_ALL_CONTENT)
    public ResponseEntity<ApiResponse<List<ContentResponse>>> getAllContentInContentResponse() {
        return contentService.getAllContentInContentResponse();
    }

    @GetMapping(ContentEndPointConstants.GET_CONTENT_DETAILS_BY_CONTENT_ID)
    public ResponseEntity<ApiResponse<ContentDetailsResponse>> getContentDetailsByContentIdForPath(@PathVariable String contentId) {
        return contentService.getContentDetailsByContentIdForPath(contentId);
    }

    @GetMapping(ContentEndPointConstants.GET_ALL_CONTENT_DETAILS)
    public ResponseEntity<ApiResponse<List<ContentDetailsResponse>>> getAllContentInContentDetailsResponse() {
        return contentService.getAllContentInContentDetailsResponse();
    }

    @PutMapping(ContentEndPointConstants.PUBLISH_CONTENT)
    public ResponseEntity<ApiResponse<ContentResponse>> publishContent(@RequestBody VisibleContentRequest visibleContentRequest) {
        return contentService.publishContent(visibleContentRequest);
    }

    @GetMapping(ContentEndPointConstants.GET_PUBLISHED_CONTENTS)
    public ResponseEntity<ApiResponse<List<ContentResponse>>> getJustPublishedContents() {
        return contentService.getJustPublishedContents();
    }

    @GetMapping(ContentEndPointConstants.GET_UNPUBLISHED_CONTENTS)
    public ResponseEntity<ApiResponse<List<ContentResponse>>> getJustUnpublishedContents(){
        return contentService.getJustUnpublishedContents();
    }

    @PutMapping(ContentEndPointConstants.UNPUBLISH_CONTENT)
    public ResponseEntity<ApiResponse<ContentResponse>> unpublishContent(@RequestBody VisibleContentRequest visibleContentRequest){
        return contentService.unpublishContent(visibleContentRequest);
    }

    @PutMapping(ContentEndPointConstants.UPDATE_NEWLY_ADDED_CONTENT)
    public ResponseEntity<ApiResponse<ContentResponse>> updateNewlyAddedContent(@RequestBody UpdateNewlyAddedContentRequest updateNewlyAddedContentRequest){
        return contentService.updateNewlyAddedContent(updateNewlyAddedContentRequest);
    }

    @PutMapping(ContentEndPointConstants.UPDATE_ALL_DETAIL_CONTENT)
    public ResponseEntity<ApiResponse<ContentResponse>> updateAllDetailContent(@RequestBody UpdateContentAllDetailsRequest updateContentAllDetailsRequest){
        return contentService.updateAllDetailContent(updateContentAllDetailsRequest);
    }

    @DeleteMapping(ContentEndPointConstants.DELETE_CONTENT_BY_ID)
    public ResponseEntity<ApiResponse<?>> deleteByIdForContent(@PathVariable String contentId){
        return contentService.deleteByIdForContent(contentId);
    }
    
}
