package org.joinfaces.cv.logo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.joinfaces.common.dto.AbstractDto;

@Builder
@Getter
@Setter
public class LogoDto extends AbstractDto {
    private String fileName;
    private String contentType;
    private byte[] contents;
}
