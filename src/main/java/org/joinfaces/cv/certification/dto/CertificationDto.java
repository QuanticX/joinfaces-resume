package org.joinfaces.cv.certification.dto;

import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.joinfaces.common.dto.AbstractDto;

import java.time.Year;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CertificationDto extends AbstractDto {
 private String name;
 @PastOrPresent
 private Year date;

 @Override
 public CertificationDto clone(){
  return CertificationDto.builder().date(date).name(name).build();
 }

}
