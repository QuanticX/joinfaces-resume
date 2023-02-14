package org.joinfaces.cv.resume.service;

import org.joinfaces.common.config.ModelMapperConverter;
import org.joinfaces.common.repository.AbstractRepository;
import org.joinfaces.common.service.CoreService;
import org.joinfaces.cv.certification.dto.CertificationDto;
import org.joinfaces.cv.certification.entity.CertificationEntity;
import org.joinfaces.cv.certification.repository.CertificationRepository;
import org.joinfaces.cv.experience.dto.ExperienceDto;
import org.joinfaces.cv.experience.entity.ExperienceEntity;
import org.joinfaces.cv.experience.repository.ExperienceRepository;
import org.joinfaces.cv.formation.dto.FormationDto;
import org.joinfaces.cv.formation.entity.FormationEntity;
import org.joinfaces.cv.formation.repository.FormationRepository;
import org.joinfaces.cv.information.dto.InformationDto;
import org.joinfaces.cv.information.entity.InformationEntity;
import org.joinfaces.cv.information.repository.InformationRepository;
import org.joinfaces.cv.language.dto.LanguageDto;
import org.joinfaces.cv.language.entity.LanguageEntity;
import org.joinfaces.cv.language.repository.LanguageRepository;
import org.joinfaces.cv.resume.dto.ResumeDto;
import org.joinfaces.cv.resume.entity.ResumeEntity;
import org.joinfaces.cv.resume.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResumeService implements Serializable {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private InformationRepository informationRepository;

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private CertificationRepository certificationRepository;

    private ResumeDto map(ResumeEntity resumeEntity) {
        ResumeDto resumeDto = ResumeDto.builder()
                .languages(resumeEntity.getLanguages().stream()
                        .map(languageEntity -> {
                            LanguageDto languageDto = LanguageDto.builder()
                                    .name(languageEntity.getName())
                                    .level(languageEntity.getLevel())
                                    .build();
                            languageDto.setId(languageEntity.getId());
                            return languageDto;
                        })
                        .collect(Collectors.toList())
                ).experiences(resumeEntity.getExperiences().stream().map(experienceEntity -> {
                                    ExperienceDto experienceDto = ExperienceDto.builder()
                                            .jobSkills(experienceEntity.getJobSkills())
                                            .jobTasks(experienceEntity.getJobTasks())
                                            .jobTeamAndMethod(experienceEntity.getJobTeamAndMethod())
                                            .jobFonctions(experienceEntity.getJobFonctions())
                                            .jobDescription(experienceEntity.getJobDescription())
                                            .jobProject(experienceEntity.getJobProject())
                                            .jobClient(experienceEntity.getJobClient())
                                            .jobPeriode(experienceEntity.getJobPeriode())
                                            .jobTitle(experienceEntity.getJobTitle())
                                            .build();
                                    experienceDto.setId(experienceEntity.getId());
                                    return experienceDto;
                                })
                                .collect(Collectors.toList())
                ).information(InformationDto.builder()
                        .name(resumeEntity.getInformation().getName())
                        .skillsDb(resumeEntity.getInformation().getSkillsDb())
                        .skillsFramworksLanguages(resumeEntity.getInformation().getSkillsFramworksLanguages())
                        .skillsMethods(resumeEntity.getInformation().getSkillsMethods())
                        .skillsOs(resumeEntity.getInformation().getSkillsOs())
                        .skillsTools(resumeEntity.getInformation().getSkillsTools())
                        .skillsWork(resumeEntity.getInformation().getSkillsWork())
                        .subTitle(resumeEntity.getInformation().getSubTitle())
                        .title(resumeEntity.getInformation().getTitle())
                        .yearOfExperience(resumeEntity.getInformation().getYearOfExperience())
                        .build()
                ).email(resumeEntity.getEmail()
                ).formations(resumeEntity.getFormations().stream().map(formationEntity -> {
                                    FormationDto formationDto = FormationDto.builder()
                                            .name(formationEntity.getName())
                                            .date(formationEntity.getDate())
                                            .build();
                                    formationDto.setId(formationEntity.getId());
                                    return formationDto;
                                }
                        ).collect(Collectors.toList())
                ).certifications(resumeEntity.getCertifications().stream().map(certification -> {
                                    CertificationDto certificationDto = CertificationDto.builder()
                                            .name(certification.getName())
                                            .date(certification.getDate())
                                            .build();
                                    certificationDto.setId(certification.getId());
                                    return certificationDto;
                                }
                        ).collect(Collectors.toList())
                ).build();
        resumeDto.setId(resumeEntity.getId());
        resumeDto.getInformation().setId(resumeEntity.getInformation().getId());
        return resumeDto;
    }

    private ResumeEntity map(ResumeDto dto) {
        ResumeEntity resumeresumeEntity = ResumeEntity.builder()
                .languages(dto.getLanguages().stream()
                        .map(dto1 -> {
                            LanguageEntity languageEntity = LanguageEntity.builder()
                                    .name(dto1.getName())
                                    .level(dto1.getLevel())
                                    .build();
                            languageEntity.setId(dto1.getId());
                            return languageEntity;
                        })
                        .collect(Collectors.toList())
                ).experiences(dto.getExperiences().stream().map(dto1 -> {
                                    ExperienceEntity experienceEntity = ExperienceEntity.builder()
                                            .jobSkills(dto1.getJobSkills())
                                            .jobTasks(dto1.getJobTasks())
                                            .jobTeamAndMethod(dto1.getJobTeamAndMethod())
                                            .jobFonctions(dto1.getJobFonctions())
                                            .jobDescription(dto1.getJobDescription())
                                            .jobProject(dto1.getJobProject())
                                            .jobClient(dto1.getJobClient())
                                            .jobPeriode(dto1.getJobPeriode())
                                            .jobTitle(dto1.getJobTitle())
                                            .build();
                                    experienceEntity.setId(dto1.getId());
                                    return experienceEntity;
                                })
                                .collect(Collectors.toList())
                ).information(InformationEntity.builder()
                        .name(dto.getInformation().getName())
                        .skillsDb(dto.getInformation().getSkillsDb())
                        .skillsFramworksLanguages(dto.getInformation().getSkillsFramworksLanguages())
                        .skillsMethods(dto.getInformation().getSkillsMethods())
                        .skillsOs(dto.getInformation().getSkillsOs())
                        .skillsTools(dto.getInformation().getSkillsTools())
                        .skillsWork(dto.getInformation().getSkillsWork())
                        .subTitle(dto.getInformation().getSubTitle())
                        .title(dto.getInformation().getTitle())
                        .yearOfExperience(dto.getInformation().getYearOfExperience())
                        .build()
                ).email(dto.getEmail()
                ).formations(dto.getFormations().stream().map(dto1 -> {
                                    FormationEntity formationEntity = FormationEntity.builder()
                                            .name(dto1.getName())
                                            .date(dto1.getDate())
                                            .build();
                                    formationEntity.setId(dto1.getId());
                                    return formationEntity;
                                }
                        ).collect(Collectors.toList())
                ).certifications(dto.getCertifications().stream().map(certification -> {
                                    CertificationEntity certificationEntity = CertificationEntity.builder()
                                            .name(certification.getName())
                                            .date(certification.getDate())
                                            .build();
                                    certificationEntity.setId(certification.getId());
                                    return certificationEntity;
                                }
                        ).collect(Collectors.toList())
                ).build();
        resumeresumeEntity.setId(dto.getId());
        resumeresumeEntity.getInformation().setId(dto.getInformation().getId());
        return resumeresumeEntity;
    }

    
    public ResumeEntity add(ResumeEntity resumeEntity) {
        resumeEntity.setInformation(informationRepository.saveAndFlush(resumeEntity.getInformation()));
        resumeEntity.setCertifications(resumeEntity.getCertifications().stream().map(
                certification -> certificationRepository.saveAndFlush(certification)
        ).collect(Collectors.toList()));
        resumeEntity.setExperiences(resumeEntity.getExperiences().stream().map(
                experienceEntity -> experienceRepository.saveAndFlush(experienceEntity)
        ).collect(Collectors.toList()));
        resumeEntity.setFormations(resumeEntity.getFormations().stream().map(
                formationEntity -> formationRepository.saveAndFlush(formationEntity)
        ).collect(Collectors.toList()));
        resumeEntity.setLanguages(resumeEntity.getLanguages().stream().map(
                languageEntity -> languageRepository.saveAndFlush(languageEntity)
        ).toList());
        return resumeRepository.saveAndFlush(resumeEntity);
    }

    
    public List<ResumeEntity> readAll() {
        return resumeRepository.findAll();
    }

    
    public ResumeEntity read(Long id) {
        return resumeRepository.getReferenceById(id);
    }

    
    public List<ResumeEntity> readAllIn(List<Long> ids) {
        return resumeRepository.findAllByIdIn(ids);
    }

    
    public ResumeEntity update(ResumeEntity x) {
        informationRepository.flush();
        certificationRepository.flush();
        experienceRepository.flush();
        formationRepository.flush();
        languageRepository.flush();
        resumeRepository.flush();
        return x;
    }

    
    public void delete(Long id) {
        resumeRepository.deleteById(id);
    }

    
    public void delete(ResumeEntity x) {
        resumeRepository.delete(x);
    }
}
