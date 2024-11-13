package com.gsuleimanov.expenses.model;

import jakarta.persistence.*;
import lombok.Getter;
import java.time.OffsetDateTime;

@MappedSuperclass
@Getter
public abstract class AuditableDao {
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = OffsetDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = OffsetDateTime.now();
    }
}