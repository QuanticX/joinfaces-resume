package org.joinfaces.resume.cv.certification.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.joinfaces.resume.common.entity.AbstractEntity;
import org.joinfaces.resume.cv.logo.entity.LogoEntity;

import java.time.LocalDate;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class CertificationEntity extends AbstractEntity {
 private String name;
 @PastOrPresent
 private LocalDate date;
 @OneToOne
 @JoinColumn(name = "badge_id")
 private LogoEntity badge;
 @URL
 private String link;
 private String description;

}
