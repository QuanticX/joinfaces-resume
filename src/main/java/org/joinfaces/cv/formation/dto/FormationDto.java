package org.joinfaces.cv.formation.dto;

import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.joinfaces.common.dto.AbstractDto;

import java.time.Year;

@Builder
@Getter
@Setter
public class FormationDto extends AbstractDto {
 private String name;
 @PastOrPresent
 private Year date;

}
