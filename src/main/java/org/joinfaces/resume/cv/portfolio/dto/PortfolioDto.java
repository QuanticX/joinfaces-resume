package org.joinfaces.resume.cv.portfolio.dto;

import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.joinfaces.resume.common.dto.AbstractDto;
import org.joinfaces.resume.cv.logo.dto.LogoDto;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class PortfolioDto extends AbstractDto {
    private LogoDto sample;
    private String title;
    @URL
    private String webSite;
    private String description;
    @PastOrPresent
    private LocalDate date_of_creation;
}
