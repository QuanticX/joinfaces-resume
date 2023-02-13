package org.joinfaces.cv.formation.dto;

import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.joinfaces.common.dto.AbstractDto;

import java.time.Year;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormationDto extends AbstractDto {
 private String name;
 @PastOrPresent
 private Year date;

 @Override
 public FormationDto clone(){
  return FormationDto.builder().date(date).name(name).build();
 }

}
