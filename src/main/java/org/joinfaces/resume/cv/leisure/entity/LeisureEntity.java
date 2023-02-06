package org.joinfaces.resume.cv.leisure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.*;
import org.joinfaces.resume.common.entity.AbstractEntity;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class LeisureEntity extends AbstractEntity {
 private String type;
 private String details;

}
