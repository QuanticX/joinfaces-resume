package org.joinfaces.resume.cv.portfolio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.joinfaces.resume.common.entity.AbstractEntity;
import org.joinfaces.resume.cv.logo.dto.LogoDto;
import org.joinfaces.resume.cv.logo.entity.LogoEntity;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class PortfolioEntity extends AbstractEntity {
    @OneToOne
    @JoinColumn(name = "sample_id")
    private LogoEntity sample;
    private String title;
    @URL
    private String webSite;
    private String description;
    @PastOrPresent
    private LocalDate date_of_creation;
}
