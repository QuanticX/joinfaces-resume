package org.joinfaces.cv.resume.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.joinfaces.common.entity.AbstractEntity;
import org.joinfaces.cv.certification.entity.CertificationEntity;
import org.joinfaces.cv.experience.entity.ExperienceEntity;
import org.joinfaces.cv.formation.entity.FormationEntity;
import org.joinfaces.cv.information.entity.InformationEntity;
import org.joinfaces.cv.language.entity.LanguageEntity;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(indexes = {
        @Index(name = "idx_resumeentity_email", columnList = "email")
})
public class ResumeEntity extends AbstractEntity {
    @Email
    private String email;
    @OneToOne
    @JoinColumn(name = "information_id")
    private InformationEntity information;
    @OneToMany
    private List<FormationEntity> formations;
    @OneToMany
    private List<LanguageEntity> languages;
    @OneToMany
    private List<ExperienceEntity> experiences;
    @OneToMany
    private List<CertificationEntity> certifications;
}
