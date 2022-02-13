package com.example.coincalculator.config.entity;

import com.example.coincalculator.config.listener.EntityModifierUpdatingListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(EntityModifierUpdatingListener.class)
public abstract class AbstractAuditEntity<I> {

    @Transient
    private String randomId;

    @Column
    private Long creatingUserId;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

}
