package org.joinfaces.cv.resume.dto;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.joinfaces.common.dto.AbstractDto;
import org.joinfaces.cv.certification.entity.CertificationEntity;
import org.joinfaces.cv.experience.entity.ExperienceEntity;
import org.joinfaces.cv.formation.entity.FormationEntity;
import org.joinfaces.cv.information.entity.InformationEntity;
import org.joinfaces.cv.language.entity.LanguageEntity;

import java.util.List;

@Builder
@Getter
@Setter
public class ResumeDto extends AbstractDto {
    @Email
    private String email;
    private InformationEntity information;
    private List<FormationEntity> formations;
    private List<LanguageEntity> languages;
    private List<ExperienceEntity> experiences;
    private List<CertificationEntity> certifications;
}
