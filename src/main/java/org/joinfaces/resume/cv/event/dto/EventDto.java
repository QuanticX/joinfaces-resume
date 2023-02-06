package org.joinfaces.resume.cv.event.dto;

import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.joinfaces.resume.common.dto.AbstractDto;
import org.joinfaces.resume.cv.logo.dto.LogoDto;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class EventDto extends AbstractDto {
 private String name;
 private LogoDto logo;
 @PastOrPresent
 private LocalDate date;
 private String role;
 private String description;
}
