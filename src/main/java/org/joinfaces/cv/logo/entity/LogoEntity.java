package org.joinfaces.cv.logo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.*;
import org.joinfaces.common.entity.AbstractEntity;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class LogoEntity extends AbstractEntity {
    private String fileName;
    private String contentType;
    @Lob
    private byte[] contents;
}
