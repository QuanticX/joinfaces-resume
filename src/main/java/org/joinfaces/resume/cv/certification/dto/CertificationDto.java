package org.joinfaces.resume.cv.certification.dto;

import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.With;
import org.hibernate.validator.constraints.URL;
import org.joinfaces.resume.common.dto.AbstractDto;
import org.joinfaces.resume.cv.logo.dto.LogoDto;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class CertificationDto extends AbstractDto {
 private String name;
 @PastOrPresent
 private LocalDate date;
 private LogoDto badge;
 @URL
 private String link;
 private String description;

}
