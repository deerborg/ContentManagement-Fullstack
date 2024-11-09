package dev.deerops.contentmanagementapi.content.model.converter;

import dev.deerops.contentmanagementapi.content.model.dto.request.CreateNewContentRequest;
import dev.deerops.contentmanagementapi.content.model.dto.request.UpdateContentAllDetailsRequest;
import dev.deerops.contentmanagementapi.content.model.dto.request.UpdateNewlyAddedContentRequest;
import dev.deerops.contentmanagementapi.content.model.dto.request.VisibleContentRequest;
import dev.deerops.contentmanagementapi.content.model.dto.response.ContentDetailsResponse;
import dev.deerops.contentmanagementapi.content.model.dto.response.ContentResponse;
import dev.deerops.contentmanagementapi.content.model.entity.ContentEntity;
import org.springframework.stereotype.Component;

@Component
public class ContentConverter {


    public ContentEntity fromCreateNewContentRequestToEntity(CreateNewContentRequest request) {
        ContentEntity contentEntity = new ContentEntity();

        contentEntity.setContentTitle(request.getContentTitle());

        contentEntity.setContentDescription(request.getContentDescription());

        return contentEntity;
    }


    public ContentEntity fromUpdateNewlyAddedContentRequestToEntity(ContentEntity existEntity,UpdateNewlyAddedContentRequest request) {

        existEntity.setContentTitle(request.getContentTitle());

        existEntity.setContentDescription(request.getContentDescription());

        return existEntity;
    }

    public ContentEntity fromUpdateContentAllDetailsRequestToEntity(ContentEntity existEntity,UpdateContentAllDetailsRequest request) {

        existEntity.setContentId(request.getContentId());

        existEntity.setVisibleContent(request.isVisibleContent());

        existEntity.setPublishDate(request.getPublishDate());

        existEntity.setUnpublishDate(request.getUnpublishDate());

        existEntity.setContentTitle(request.getContentTitle());

        existEntity.setContentDescription(request.getContentDescription());

        return existEntity;
    }


    public ContentResponse fromEntityToContentResponse(ContentEntity contentEntity) {
        ContentResponse contentResponse = new ContentResponse();

        contentResponse.setContentTitle(contentEntity.getContentTitle());

        contentResponse.setContentDescription(contentEntity.getContentDescription());

        return contentResponse;
    }



    public ContentDetailsResponse fromEntityToContentDetailsResponse(ContentEntity contentEntity) {
        ContentDetailsResponse contentDetailsResponse = new ContentDetailsResponse();

        contentDetailsResponse.setContentTitle(contentEntity.getContentTitle());

        contentDetailsResponse.setContentDescription(contentEntity.getContentDescription());

        contentDetailsResponse.setVisibleContent(contentEntity.isVisibleContent());

        contentDetailsResponse.setContentId(contentEntity.getContentId());

        contentDetailsResponse.setPublishDate(contentEntity.getPublishDate());

        return contentDetailsResponse;
    }

}
