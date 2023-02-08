package org.joinfaces.cv.language.entity;


import jakarta.persistence.Entity;
import lombok.*;
import org.joinfaces.common.entity.AbstractEntity;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class LanguageEntity extends AbstractEntity {
 private String name;
 private String level;

}
