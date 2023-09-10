package com.jaywright.template.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@ToString
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @ToString.Include
    @Id
    @GeneratedValue
    protected UUID id;

    @CreationTimestamp
    private OffsetDateTime createdDate;

    @UpdateTimestamp
    private OffsetDateTime modifiedDate;

    protected BaseEntity() {
        this.id = UUID.randomUUID();
        this.createdDate = OffsetDateTime.now();
        this.modifiedDate = OffsetDateTime.now();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof BaseEntity)) {
            return false;
        }

        return id.equals(((BaseEntity) obj).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
