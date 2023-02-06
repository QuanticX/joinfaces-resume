package org.joinfaces.resume.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class AbstractDto implements Serializable {
    protected Long id;
    protected Long version;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}
