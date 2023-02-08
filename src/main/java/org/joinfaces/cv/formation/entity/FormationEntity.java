package org.joinfaces.cv.formation.entity;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.joinfaces.common.entity.AbstractEntity;

import java.time.Year;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class FormationEntity extends AbstractEntity {
 private String name;
 @PastOrPresent
 private Year date;

}
