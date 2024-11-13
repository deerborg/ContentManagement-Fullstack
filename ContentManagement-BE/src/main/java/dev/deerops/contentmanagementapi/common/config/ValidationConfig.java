/*
 * Copyright (c) 2024. Furkan Aydemir.
 *
 * This file is part of the ContentManagement project. Unauthorized copying, distribution, or transmission of this file,
 * in any form, is strictly prohibited without prior written permission from Furkan Aydemir.
 *
 */

package dev.deerops.contentmanagementapi.common.config;

import dev.deerops.contenthelper.validation.validation.EntityValidation;
import dev.deerops.contenthelper.validation.validation.impl.EntityValidationImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfig {

    @Bean
    public EntityValidation entityValidation() {
        return new EntityValidationImpl();
    }

}
