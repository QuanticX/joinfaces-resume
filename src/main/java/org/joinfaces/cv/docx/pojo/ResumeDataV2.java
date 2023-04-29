package org.joinfaces.cv.docx.pojo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumeDataV2 {

	private String name;

	private String title;

	private String yearOfExperience;

	private String subTitle;

	private String skillsWork;

	private String skillsFramworksLanguages;

	private String skillsOs;

	private String skillsMethods;

	private String skillsDb;

	private String skillsTools;

	private List<Language> languages;

	private List<LanguageLevel> languageLevels;

	private List<FormationYear> formationYears;

	private List<FormationTitle> formationTitles;

	private List<Experience> experiences;

	private List<CertificationYear> certificationYears;

	private List<CertificationTitle> certificationTitles;

}
