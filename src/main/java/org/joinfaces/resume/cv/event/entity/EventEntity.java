package org.joinfaces.resume.cv.event.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.joinfaces.resume.common.entity.AbstractEntity;
import org.joinfaces.resume.cv.logo.entity.LogoEntity;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class EventEntity extends AbstractEntity {
 private String name;
 @OneToOne
 @JoinColumn(name = "logo_id")
 private LogoEntity logo;
 @PastOrPresent
 private LocalDate date;
 private String role;
 private String description;
}
