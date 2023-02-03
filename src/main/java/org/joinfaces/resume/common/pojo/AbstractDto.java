package org.joinfaces.resume.common.pojo;

import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class AbstractDto {
    protected Long id;
    protected Long version;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}
