package dev.deerops.contentmanagementapi.content.service.impl;

import dev.deerops.contentmanagementapi.common.util.result.ApiResponse;
import dev.deerops.contentmanagementapi.common.util.result.ApiResponseHelper;
import dev.deerops.contentmanagementapi.content.model.converter.ContentConverter;
import dev.deerops.contentmanagementapi.content.model.dto.request.CreateNewContentRequest;
import dev.deerops.contentmanagementapi.content.model.dto.request.UpdateContentAllDetailsRequest;
import dev.deerops.contentmanagementapi.content.model.dto.request.UpdateNewlyAddedContentRequest;
import dev.deerops.contentmanagementapi.content.model.dto.request.VisibleContentRequest;
import dev.deerops.contentmanagementapi.content.model.dto.response.ContentDetailsResponse;
import dev.deerops.contentmanagementapi.content.model.dto.response.ContentResponse;
import dev.deerops.contentmanagementapi.content.model.entity.ContentEntity;
import dev.deerops.contentmanagementapi.content.model.util.exception.ContentLimitExceededException;
import dev.deerops.contentmanagementapi.content.model.util.validation.ContentValidation;
import dev.deerops.contentmanagementapi.content.repository.ContentRepository;
import dev.deerops.contentmanagementapi.content.service.ContentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;

    private final ContentConverter contentConverter;

    @Value("${content.max.limit}")
    private int maxLimit;

    public ContentServiceImpl(ContentRepository contentRepository, ContentConverter contentConverter) {
        this.contentRepository = contentRepository;
        this.contentConverter = contentConverter;
    }

    @Override
    public ResponseEntity<ApiResponse<ContentResponse>> createNewContent(CreateNewContentRequest createNewContentRequest) {

        if (contentRepository.count() >= maxLimit) {
            throw new ContentLimitExceededException();
        }

        ContentEntity contentEntity = contentConverter.fromCreateNewContentRequestToEntity(createNewContentRequest);

        ContentValidation.contentTitleAndDescriptionValidation(
                contentEntity.getContentTitle(), contentEntity.getContentDescription()
        );

        contentEntity.setVisibleContent(false);

        ContentResponse contentResponse = contentConverter.fromEntityToContentResponse(
                contentRepository.save(contentEntity)
        );

        return new ResponseEntity<>(ApiResponseHelper.CREATE(contentResponse), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApiResponse<ContentResponse>> updateNewlyAddedContent(UpdateNewlyAddedContentRequest updateNewlyAddedContentRequest) {
        ContentValidation.contentIdValidation(updateNewlyAddedContentRequest.getContentId());

        ContentValidation.contentTitleAndDescriptionValidation(
                updateNewlyAddedContentRequest.getContentTitle(), updateNewlyAddedContentRequest.getContentDescription()
        );


        ContentValidation.validateContentExistenceForObject(contentRepository.findById(updateNewlyAddedContentRequest.getContentId()));

        ContentEntity contentEntity  = contentConverter.fromUpdateNewlyAddedContentRequestToEntity(updateNewlyAddedContentRequest);

        ContentResponse contentResponse = contentConverter.fromEntityToContentResponse(contentRepository.save(contentEntity));

        return new ResponseEntity<>(ApiResponseHelper.UPDATE(contentResponse), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ApiResponse<ContentResponse>> updateAllDetailContent(UpdateContentAllDetailsRequest updateContentAllDetailsRequest) {
        ContentValidation.contentIdValidation(updateContentAllDetailsRequest.getContentId());

        ContentValidation.contentTitleAndDescriptionValidation
                (updateContentAllDetailsRequest.getContentTitle(), updateContentAllDetailsRequest.getContentDescription()
                );

        ContentValidation.validateContentExistenceForObject(contentRepository.findById(updateContentAllDetailsRequest.getContentId()));

        ContentEntity contentEntity  = contentConverter.fromUpdateContentAllDetailsRequestToEntity(updateContentAllDetailsRequest);

        ContentResponse contentResponse = contentConverter.fromEntityToContentResponse(contentRepository.save(contentEntity));

        return new ResponseEntity<>(ApiResponseHelper.UPDATE(contentResponse), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<ContentResponse>> getContentByContentIdForPath(String contentId) {

        ContentValidation.contentIdValidation(contentId);

        ContentEntity contentEntity =
                ContentValidation.validateContentExistenceForOptionalEntity(contentRepository.findById(contentId));

        ContentResponse contentResponse = contentConverter.fromEntityToContentResponse(contentEntity);

        return new ResponseEntity<>(ApiResponseHelper.GET_CONTENT(contentResponse), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<List<ContentResponse>>> getAllContentInContentResponse() {
        List<ContentEntity> contentEntityList = contentRepository.findAll();

        List<ContentResponse> contentResponseList = contentEntityList.stream()
                .map(
                        contentConverter::fromEntityToContentResponse).toList();


        return new ResponseEntity<>(ApiResponseHelper.GET_CONTENT_LIST(contentResponseList), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ApiResponse<ContentDetailsResponse>> getContentDetailsByContentIdForPath(String contentId) {

        ContentValidation.contentIdValidation(contentId);

        ContentEntity contentEntity =
                ContentValidation.validateContentExistenceForOptionalEntity(contentRepository.findById(contentId));

        ContentDetailsResponse contentDetailsResponse = contentConverter.fromEntityToContentDetailsResponse(contentEntity);

        return new ResponseEntity<>(ApiResponseHelper.GET_CONTENT(contentDetailsResponse), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<List<ContentDetailsResponse>>> getAllContentInContentDetailsResponse() {
        List<ContentEntity> contentEntityList = contentRepository.findAll();

        List<ContentDetailsResponse> contentDetailsResponseList = contentEntityList.stream()
                .map(
                        contentConverter::fromEntityToContentDetailsResponse).toList();
        return new ResponseEntity<>(ApiResponseHelper.GET_CONTENT_LIST(contentDetailsResponseList), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ApiResponse<ContentResponse>> publishContent(VisibleContentRequest visibleContentRequest) {

        ContentValidation.contentIdValidation(visibleContentRequest.getContentId());

        ContentEntity contentEntity =
                ContentValidation.validateContentExistenceForOptionalEntity(
                        contentRepository.findById(visibleContentRequest.getContentId()));

        contentEntity.setVisibleContent(visibleContentRequest.isVisibleContent());

        if (visibleContentRequest.isVisibleContent()) {
            contentEntity.setPublishDate(LocalDate.now());
        }

        ContentResponse contentResponse = contentConverter.fromEntityToContentResponse(contentRepository.save(contentEntity));

        return new ResponseEntity<>(ApiResponseHelper.UPDATE(contentResponse), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ApiResponse<ContentResponse>> unpublishContent(VisibleContentRequest visibleContentRequest) {
        ContentValidation.contentIdValidation(visibleContentRequest.getContentId());

        ContentEntity contentEntity = ContentValidation.validateContentExistenceForOptionalEntity(
                contentRepository.findById(visibleContentRequest.getContentId())
        );

        contentEntity.setVisibleContent(visibleContentRequest.isVisibleContent());

        if (!visibleContentRequest.isVisibleContent()) {
            contentEntity.setUnpublishDate(LocalDate.now());
        }

        ContentResponse contentResponse = contentConverter.fromEntityToContentResponse(contentRepository.save(contentEntity));

        return new ResponseEntity<>(ApiResponseHelper.UPDATE(contentResponse), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ApiResponse<List<ContentResponse>>> getJustPublishedContents() {
        List<ContentEntity> contentEntityList = contentRepository.findByVisibleContentIsTrue();

        List<ContentResponse> contentResponseList = contentEntityList.stream()
                .map(contentConverter::fromEntityToContentResponse).toList();

        return new ResponseEntity<>(ApiResponseHelper.GET_CONTENT_LIST(contentResponseList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<List<ContentResponse>>> getJustUnpublishedContents() {
        List<ContentEntity> contentEntityList = contentRepository.findByVisibleContentIsFalse();

        List<ContentResponse> contentResponseList = contentEntityList.stream()
                .map(contentConverter::fromEntityToContentResponse).toList();

        return new ResponseEntity<>(ApiResponseHelper.GET_CONTENT_LIST(contentResponseList), HttpStatus.OK);
    }


}
