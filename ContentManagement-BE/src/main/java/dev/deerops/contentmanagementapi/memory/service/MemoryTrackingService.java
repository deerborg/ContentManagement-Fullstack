/*
 * Copyright (c) 2024. Furkan Aydemir.
 *
 * This file is part of the ContentManagement project. Unauthorized copying, distribution, or transmission of this file,
 * in any form, is strictly prohibited without prior written permission from Furkan Aydemir.
 *
 */

package dev.deerops.contentmanagementapi.memory.service;

import dev.deerops.contentmanagementapi.memory.entity.MemoryUsageRecordEntity;
import dev.deerops.contentmanagementapi.memory.repository.MemoryUsageRepository;
import dev.deerops.contentmanagementapi.memory.util.MemoryUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MemoryTrackingService {
    private final MemoryUsageRepository usageRepository;

    public MemoryTrackingService(MemoryUsageRepository usageRepository) {
        this.usageRepository = usageRepository;
    }

    @Scheduled(fixedRate = 10000)
    public void trackMemory(){

        long heapMemory = MemoryUtil.getHeapMemoryUsage();
        long nonHeapMemory = MemoryUtil.getNonHeapMemoryUsage();

        MemoryUsageRecordEntity memoryUsageRecordEntity = new MemoryUsageRecordEntity();

        memoryUsageRecordEntity.setHeapMemoryUsage(heapMemory);
        memoryUsageRecordEntity.setNonHeapMemoryUsage(nonHeapMemory);
        memoryUsageRecordEntity.setTimestamp(LocalDateTime.now());

        usageRepository.save(memoryUsageRecordEntity);
    }

}
