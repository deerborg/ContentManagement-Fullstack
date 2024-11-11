/*
 * Copyright (c) 2024. Furkan Aydemir.
 *
 * This file is part of the ContentManagement project. Unauthorized copying, distribution, or transmission of this file,
 * in any form, is strictly prohibited without prior written permission from Furkan Aydemir.
 *
 */

package dev.deerops.contentmanagementapi.content.config;

public class ContentEndPointConstants {
    public static final String BASE_CONTENT_PATH = "/content";
    public static final String CREATE_NEW_CONTENT = "/private/create";
    public static final String GET_CONTENT_BY_CONTENT_ID = "/private/content/{contentId}";
    public static final String GET_ALL_CONTENT = "/private/contents";
    public static final String GET_CONTENT_DETAILS_BY_CONTENT_ID = "/private/contents/{contentId}/details";
    public static final String GET_ALL_CONTENT_DETAILS = "/private/contents/details";
    public static final String PUBLISH_CONTENT = "/private/update/published";
    public static final String GET_PUBLISHED_CONTENTS = "/public/contents/published";
    public static final String GET_UNPUBLISHED_CONTENTS = "/private/contents/unpublished";
    public static final String UNPUBLISH_CONTENT = "/private/update/unpublished";
    public static final String UPDATE_NEWLY_ADDED_CONTENT = "/private/update";
    public static final String UPDATE_ALL_DETAIL_CONTENT = "/update/details";
    public static final String DELETE_CONTENT_BY_ID = "/content/{contentId}";
}
