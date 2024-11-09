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
import dev.deerops.contentmanagementapi.content.model.util.exception.NotFoundContent;
import dev.deerops.contentmanagementapi.content.model.util.validation.ContentValidation;
import dev.deerops.contentmanagementapi.content.repository.ContentRepository;
import dev.deerops.contentmanagementapi.content.service.ContentService;
import dev.deerops.contentmanagementapi.user.model.entity.UserEntity;
import dev.deerops.contentmanagementapi.user.model.entity.enums.Role;
import dev.deerops.contentmanagementapi.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;

    private final ContentConverter contentConverter;

    private final UserRepository userRepository;

    public ContentServiceImpl(ContentRepository contentRepository, ContentConverter contentConverter, UserRepository userRepository) {
        this.contentRepository = contentRepository;
        this.contentConverter = contentConverter;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<ApiResponse<ContentResponse>> createNewContent(CreateNewContentRequest createNewContentRequest) {

        if (contentRepository.count() >= loggedInUser().getContentMaxLimit()) {
            throw new ContentLimitExceededException();
        }

        ContentEntity contentEntity = contentConverter.fromCreateNewContentRequestToEntity(createNewContentRequest);

        ContentValidation.contentTitleAndDescriptionValidation(
                contentEntity.getContentTitle(), contentEntity.getContentDescription()
        );

        contentEntity.setVisibleContent(false);

        contentEntity.setUser(loggedInUser());

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


        ContentEntity contentEntity = getContentOrThrowIfNotFound(updateNewlyAddedContentRequest.getContentId());

        ContentEntity updatedContentEntity = contentConverter.fromUpdateNewlyAddedContentRequestToEntity(contentEntity, updateNewlyAddedContentRequest);

        ContentResponse contentResponse = contentConverter.fromEntityToContentResponse(contentRepository.saveAndFlush(updatedContentEntity));

        return new ResponseEntity<>(ApiResponseHelper.UPDATE(contentResponse), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ApiResponse<ContentResponse>> updateAllDetailContent(UpdateContentAllDetailsRequest updateContentAllDetailsRequest) {
        ContentValidation.contentIdValidation(updateContentAllDetailsRequest.getContentId());

        ContentValidation.contentTitleAndDescriptionValidation
                (updateContentAllDetailsRequest.getContentTitle(), updateContentAllDetailsRequest.getContentDescription()
                );

        ContentEntity contentEntity = getContentOrThrowIfNotFound(updateContentAllDetailsRequest.getContentId());

        ContentEntity updateContentEntity = contentConverter.fromUpdateContentAllDetailsRequestToEntity(contentEntity, updateContentAllDetailsRequest);

        ContentResponse contentResponse = contentConverter.fromEntityToContentResponse(contentRepository.save(updateContentEntity));

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

    @Override
    public ResponseEntity<ApiResponse<?>> deleteByIdForContent(String contentId) {
        ContentValidation.contentIdValidation(contentId);

        ContentEntity contentEntity =
                ContentValidation.validateContentExistenceForOptionalEntity(contentRepository.findById(contentId));

        contentRepository.delete(contentEntity);
        return new ResponseEntity<>(ApiResponseHelper.OK(), HttpStatus.OK);
    }

    private ContentEntity getContentOrThrowIfNotFound(String contentId) {
        return contentRepository.findById(contentId).orElseThrow(NotFoundContent::new);
    }

    private UserEntity loggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserEntity) {
            return (UserEntity) authentication.getPrincipal();
        }

        throw new RuntimeException("You are not logged in");
    }
}
