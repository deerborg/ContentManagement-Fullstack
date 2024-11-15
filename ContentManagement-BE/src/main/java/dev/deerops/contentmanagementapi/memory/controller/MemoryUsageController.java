/*
 * Copyright (c) 2024. Furkan Aydemir.
 *
 * This file is part of the ContentManagement project. Unauthorized copying, distribution, or transmission of this file,
 * in any form, is strictly prohibited without prior written permission from Furkan Aydemir.
 *
 */

package dev.deerops.contentmanagementapi.memory.controller;

import dev.deerops.contentmanagementapi.memory.service.MemoryTrackingService;
import dev.deerops.contentmanagementapi.memory.util.MemoryUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memory")
public class MemoryUsageController {

    private final MemoryTrackingService memoryTrackingService;

    public MemoryUsageController(MemoryTrackingService memoryTrackingService) {
        this.memoryTrackingService = memoryTrackingService;
    }

    @GetMapping("/memory-usage")
    public String getMemoryUsage() {
        long heapMemoryUsage = MemoryUtil.getHeapMemoryUsage();
        long nonHeapMemoryUsage = MemoryUtil.getNonHeapMemoryUsage();

        float heapToMb = (float) heapMemoryUsage / (1024 * 1024);
        float nonHeapToMb = (float) nonHeapMemoryUsage / (1024 * 1024);

        return "Heap Memory Usage: " + heapToMb + " MB, Non-Heap Memory Usage: " + nonHeapToMb + " MB";
    }


}
