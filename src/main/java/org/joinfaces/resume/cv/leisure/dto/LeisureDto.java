package org.joinfaces.resume.cv.leisure.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.joinfaces.resume.common.dto.AbstractDto;

@Builder
@Getter
@Setter
public class LeisureDto extends AbstractDto {
 private String type;
 private String details;

}
