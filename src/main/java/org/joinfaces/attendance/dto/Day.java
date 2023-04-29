package org.joinfaces.attendance.dto;

import lombok.Getter;
import lombok.Setter;
import org.joinfaces.common.dto.AbstractDto;

@Setter
@Getter
public class Day extends AbstractDto {
    private boolean morning = false;
    private boolean afternoon = false;
}
