package com.example.coincalculator.config.listener;

import com.example.coincalculator.config.entity.AbstractAuditEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.Date;
import java.time.Instant;

/**
 * Updates the creating/updating user & date/time
 */
public class EntityModifierUpdatingListener {

    @PrePersist
    public void crateDate(AbstractAuditEntity entity) {
        entity.setCreatedAt(Date.from(Instant.now()));
    }

    @PreUpdate
    public void updateDate(AbstractAuditEntity entity) {
        entity.setUpdatedAt(Date.from(Instant.now()));
    }

}

