package org.joinfaces.cv.resume.dto;

import jakarta.validation.constraints.Email;
import lombok.*;
import org.joinfaces.common.dto.AbstractDto;
import org.joinfaces.cv.certification.dto.CertificationDto;
import org.joinfaces.cv.experience.dto.ExperienceDto;
import org.joinfaces.cv.formation.dto.FormationDto;
import org.joinfaces.cv.information.dto.InformationDto;
import org.joinfaces.cv.language.dto.LanguageDto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDto extends AbstractDto implements Serializable {
    @Email
    private String email;
    private InformationDto information;
    private List<FormationDto> formations;
    private List<LanguageDto> languages;
    private List<ExperienceDto> experiences;
    private List<CertificationDto> certifications;
    @Override
    public ResumeDto clone() {
        List<FormationDto> formations = this.formations.stream().map(dto -> dto.clone()).collect(Collectors.toList());
        List<LanguageDto> languages = this.languages.stream().map(dto -> dto.clone()).collect(Collectors.toList());
        List<ExperienceDto> experiences = this.experiences.stream().map(dto -> dto.clone()).collect(Collectors.toList());
        List<CertificationDto> certifications = this.certifications.stream().map(dto -> dto.clone()).collect(Collectors.toList());
        return new ResumeDto(email, information.clone(),formations,languages,experiences,certifications );
    }
}
