package org.joinfaces.cv.language.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.joinfaces.common.dto.AbstractDto;

@Builder
@Getter
@Setter
public class LanguageDto extends AbstractDto {
 private String name;
 private String level;

 @Override
 public LanguageDto clone(){
  return LanguageDto.builder().name(name).level(level).build();
 }
}
