package dev.deerops.contentmanagementapi.content.controller;

import dev.deerops.contentmanagementapi.common.util.result.ApiResponse;
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
@RequestMapping("/content")
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping("/private/create")
    public ResponseEntity<ApiResponse<ContentResponse>> createNewContent(@RequestBody CreateNewContentRequest contentRequest) {

        return contentService.createNewContent(contentRequest);
    }

    @GetMapping("/private/content/{contentId}")
    public ResponseEntity<ApiResponse<ContentResponse>> getContentByContentIdForPath(@PathVariable String contentId) {
        return contentService.getContentByContentIdForPath(contentId);
    }

    @GetMapping("/private/contents")
    public ResponseEntity<ApiResponse<List<ContentResponse>>> getAllContentInContentResponse() {
        return contentService.getAllContentInContentResponse();
    }

    @GetMapping("/private/contents/{contentId}/details")
    public ResponseEntity<ApiResponse<ContentDetailsResponse>> getContentDetailsByContentIdForPath(@PathVariable String contentId) {
        return contentService.getContentDetailsByContentIdForPath(contentId);
    }

    @GetMapping("/private/contents/details")
    public ResponseEntity<ApiResponse<List<ContentDetailsResponse>>> getAllContentInContentDetailsResponse() {
        return contentService.getAllContentInContentDetailsResponse();
    }

    @PutMapping("/private/update/published")
    public ResponseEntity<ApiResponse<ContentResponse>> publishContent(@RequestBody VisibleContentRequest visibleContentRequest) {
        return contentService.publishContent(visibleContentRequest);
    }

    @GetMapping("/public/contents/published")
    public ResponseEntity<ApiResponse<List<ContentResponse>>> getJustPublishedContents() {
        return contentService.getJustPublishedContents();
    }

    @GetMapping("/private/contents/unpublished")
    public ResponseEntity<ApiResponse<List<ContentResponse>>> getJustUnpublishedContents(){
        return contentService.getJustUnpublishedContents();
    }

    @PutMapping("/private/update/unpublished")
    public ResponseEntity<ApiResponse<ContentResponse>> unpublishContent(@RequestBody VisibleContentRequest visibleContentRequest){
        return contentService.unpublishContent(visibleContentRequest);
    }

    @PutMapping("/private/update")
    public ResponseEntity<ApiResponse<ContentResponse>> updateNewlyAddedContent(@RequestBody UpdateNewlyAddedContentRequest updateNewlyAddedContentRequest){
        return contentService.updateNewlyAddedContent(updateNewlyAddedContentRequest);
    }

    @PutMapping("/update/details")
    public ResponseEntity<ApiResponse<ContentResponse>> updateAllDetailContent(@RequestBody UpdateContentAllDetailsRequest updateContentAllDetailsRequest){
        return contentService.updateAllDetailContent(updateContentAllDetailsRequest);
    }




}
