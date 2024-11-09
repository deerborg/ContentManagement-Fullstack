package dev.deerops.contentmanagementapi.content.model.util.validation;

import dev.deerops.contentmanagementapi.content.model.entity.ContentEntity;
import dev.deerops.contentmanagementapi.content.model.util.exception.NotFoundContent;
import dev.deerops.contentmanagementapi.content.model.util.exception.NullOrEmptyContentException;

import java.util.Optional;


public class ContentValidation {



    public static void contentTitleAndDescriptionValidation(String title, String description) {
        if (title == null || title.trim().isEmpty() || description == null || description.trim().isEmpty()) {
            throw new NullOrEmptyContentException();
        }
    }

    public static void contentIdValidation(String contentId) {
        if (contentId == null || contentId.trim().isEmpty()) {
            throw new NullOrEmptyContentException();
        }
    }


    public static <T> T validateContentExistenceForOptionalEntity(Optional<T> content) {
        return content.orElseThrow(NotFoundContent::new);
    }



}
