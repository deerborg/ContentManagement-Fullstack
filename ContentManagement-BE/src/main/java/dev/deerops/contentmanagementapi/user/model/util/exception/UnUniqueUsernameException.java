/*
 * Copyright (c) 2024. Furkan Aydemir.
 *
 * This file is part of the ContentManagement project. Unauthorized copying, distribution, or transmission of this file,
 * in any form, is strictly prohibited without prior written permission from Furkan Aydemir.
 *
 */

package dev.deerops.contentmanagementapi.user.model.util.exception;

public class UnUniqueUsernameException extends RuntimeException {
    public UnUniqueUsernameException(String message) {
        super(message);
    }

    public UnUniqueUsernameException() {
    }
}
