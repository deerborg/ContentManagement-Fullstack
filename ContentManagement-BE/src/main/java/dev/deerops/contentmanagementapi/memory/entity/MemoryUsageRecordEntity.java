/*
 * Copyright (c) 2024. Furkan Aydemir.
 *
 * This file is part of the ContentManagement project. Unauthorized copying, distribution, or transmission of this file,
 * in any form, is strictly prohibited without prior written permission from Furkan Aydemir.
 *
 */

package dev.deerops.contentmanagementapi.memory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "memory_usage")
public class MemoryUsageRecordEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String memoryUsageId;

    private Long heapMemoryUsage;

    private Long nonHeapMemoryUsage;

    private LocalDateTime timestamp;

    public MemoryUsageRecordEntity() {
    }

    public MemoryUsageRecordEntity(String memoryUsageId, Long heapMemoryUsage, Long nonHeapMemoryUsage, LocalDateTime timestamp) {
        this.memoryUsageId = memoryUsageId;
        this.heapMemoryUsage = heapMemoryUsage;
        this.nonHeapMemoryUsage = nonHeapMemoryUsage;
        this.timestamp = timestamp;
    }

    public String getMemoryUsageId() {
        return memoryUsageId;
    }

    public void setMemoryUsageId(String memoryUsageId) {
        this.memoryUsageId = memoryUsageId;
    }

    public Long getHeapMemoryUsage() {
        return heapMemoryUsage;
    }

    public void setHeapMemoryUsage(Long heapMemoryUsage) {
        this.heapMemoryUsage = heapMemoryUsage;
    }

    public Long getNonHeapMemoryUsage() {
        return nonHeapMemoryUsage;
    }

    public void setNonHeapMemoryUsage(Long nonHeapMemoryUsage) {
        this.nonHeapMemoryUsage = nonHeapMemoryUsage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
