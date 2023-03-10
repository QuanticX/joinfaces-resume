package org.joinfaces.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public abstract class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @Version
    protected Long version;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    @PrePersist
    protected void prePersist(){
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate(){
        prePersist();
        updatedAt = LocalDateTime.now();
    }
}
