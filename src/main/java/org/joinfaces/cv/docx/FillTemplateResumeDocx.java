package org.joinfaces.cv.docx;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.joinfaces.cv.certification.entity.CertificationEntity;
import org.joinfaces.cv.docx.pojo.*;
import org.joinfaces.cv.experience.entity.ExperienceEntity;
import org.joinfaces.cv.formation.entity.FormationEntity;
import org.joinfaces.cv.language.entity.LanguageEntity;
import org.joinfaces.cv.resume.entity.ResumeEntity;

import com.deepoove.poi.XWPFTemplate;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;

@Component
public class FillTemplateResumeDocx {

	private static final String in = "Dossier_de_Competences_template.docx";
	private static final String out = "/temp/"+"out_template.docx";

	public StreamedContent fillTemplateAndReturnStream(ResumeDataV2 data) throws IOException {

		XWPFTemplate template = XWPFTemplate.compile(this.getClass().getClassLoader().getResourceAsStream(in)).render(data);


		FileOutputStream outputStream = new FileOutputStream(out);
		template.write(outputStream);
		outputStream.flush();
		outputStream.close();
		template.close();

		StreamedContent streamedContent =
		 DefaultStreamedContent.builder()
				.name(data.getName()+"-" + data.getTitle()+".docx")
				.contentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
				.stream(() -> {
					try {
						return new ByteArrayInputStream(new FileInputStream(out).readAllBytes());
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				})
				.build();
		return streamedContent;
	}

	public ResumeDataV2 mapResume(ResumeEntity resume){
		ResumeDataV2 data = new ResumeDataV2();

		data.setName(resume.getInformation().getName());
		data.setTitle(resume.getInformation().getTitle());
		data.setYearOfExperience(String.valueOf(resume.getInformation().getYearOfExperience()));
		data.setSubTitle(resume.getInformation().getSubTitle());
		data.setSkillsWork(resume.getInformation().getSkillsWork());
		data.setSkillsFramworksLanguages(resume.getInformation().getSkillsFramworksLanguages());
		data.setSkillsOs(resume.getInformation().getSkillsOs());
		data.setSkillsMethods(resume.getInformation().getSkillsMethods());
		data.setSkillsDb(resume.getInformation().getSkillsDb());
		data.setSkillsTools(resume.getInformation().getSkillsTools());

		List<Language> languages = new ArrayList<>(resume.getLanguages().size());
		List<LanguageLevel> languageLevels = new ArrayList<>(resume.getLanguages().size());
		for (LanguageEntity language: resume.getLanguages()) {
			languages.add(new Language(language.getName()));
			languageLevels.add(new LanguageLevel(language.getLevel()));
		}
		data.setLanguages(languages);
		data.setLanguageLevels(languageLevels);

		List<FormationYear> formationYears = new ArrayList<>(resume.getFormations().size());
		List<FormationTitle> formationTitles = new ArrayList<>(resume.getFormations().size());
		for (FormationEntity formation: resume.getFormations()) {
			formationYears.add(new FormationYear(formation.getDate().toString()));
			formationTitles.add(new FormationTitle(formation.getName()));
		}
		data.setFormationYears(formationYears);
		data.setFormationTitles(formationTitles);

		List<Experience> experiences = new ArrayList<>(resume.getExperiences().size());
		for (ExperienceEntity experienceEntity: resume.getExperiences()) {
			List<JobFonction> jobFonctions = experienceEntity.getJobFonctions().stream().map(
					string -> new JobFonction(string)).collect(Collectors.toList());
			List<JobTask> jobTasks = experienceEntity.getJobFonctions().stream().map(
					string -> new JobTask(string)).collect(Collectors.toList());
			Experience experience = new Experience();
			experience.setJobFonctions(jobFonctions);
			experience.setJobTasks(jobTasks);
			experience.setJobTitle(experienceEntity.getJobTitle());
			experience.setJobPeriode(experienceEntity.getJobPeriode());
			experience.setJobClient(experienceEntity.getJobClient());
			experience.setJobProject(experienceEntity.getJobProject());
			experience.setJobDescription(experienceEntity.getJobDescription());
			experience.setJobTeamAndMethod(experienceEntity.getJobTeamAndMethod());
			experience.setJobSkills(experienceEntity.getJobSkills());
			experiences.add(experience);
		}
		data.setExperiences(experiences);


		List<CertificationYear> certificationYears = new ArrayList<>(resume.getCertifications().size());
		List<CertificationTitle> certificationTitles = new ArrayList<>(resume.getCertifications().size());
		for (CertificationEntity certification: resume.getCertifications()) {
			certificationYears.add(new CertificationYear(certification.getDate().toString()));
			certificationTitles.add(new CertificationTitle(certification.getName()));
		}
		data.setCertificationYears(certificationYears);
		data.setCertificationTitles(certificationTitles);
		return data;
	}

	private ResumeDataV2 getResumeDataV2() {
		ResumeDataV2 datas = new ResumeDataV2();

		datas.setName("M. Ab.");
		datas.setYearOfExperience("4");
		datas.setTitle("DEVELOPPEUR : JAVA EE SPRING ANGULAR");
		datas.setSubTitle("ANALYSTE, DATA SCIENTIST, CONCEPTION ET DEVELOPPEMENT");
		datas.setSkillsWork("COMMUNICATION, TRAVAIL EN EQUIPE, ADAPTABILITE, RIGUEUR, ANALYSE, CREATIVITE");
		datas.setSkillsFramworksLanguages("Java SE 8/11, Groovy, Scala, HTML5, JavaScript, JSON, XML/XPATH, "
				+ "TypeScript, SQL, PL/SQL, Python, C, CSS, Matlab, R, Angular, Hibernate, Java"
				+ "EE 7/8, JPA, REST, SOAP, JSP, JSF, Spring, Twitter Bootstrap, Spring Boot, "
				+ "Node.Js, Material, Animation, Criteria API, JPQL, JMS, RxJS, JDBC, Ansible, "
				+ "JSF, Web Socket, NgRx, Redux, Spring Boot / Web & Reactive Web & Web"
				+ "Services / Data / Kafka / Cloud & Cloud Stream / Batch / Retry / DevTools"
				+ "/ WebSocket / Validation / LDAP / Oauth2 / Security , Hadoop, Spark,"
				+ "NestJS, JUnit, Jasmine, Protractor, Selenium, Karma, JMeter, Mockito, "
				+ "Liquidbase, Flyway, Tomcat, Jetty, Undertow, WildFly, Netty");
		datas.setSkillsOs("Windows, Linux (Ubuntu/Cent OS), Mac OS");
		datas.setSkillsMethods("Cascade, Agile : SCRUM/KANABAN, Spirale, TDD, Merise, QQOQCCP, UML");
		datas.setSkillsDb("Oracle, SQL Server, PostgreSQL, MongoDB, Cassandra, Hive, HBase, ElasticSearch, H2");
		datas.setSkillsTools("JIRA, Confluence, Maven, Gradle, NPM, Sonar Qube, Memory Analyzer Tool, "
				+ "PuTTY, Docker, Elastic Stack, Prometheus, Kubernetes, Amazon Web "
				+ "Services, Heroku, Azure, Cloud Foundry, Google Cloud Platform, VMWare, "
				+ "Hyper-V, VirtualBox, Nexus, Jenkins, Sketch UP, Adobe XD, Adobe "
				+ "Photoshop, Adobe Illustrator, Qlik Sense, Grafana, Power BI, Excel, Eclipse, "
				+ "IntelliJ IDEA, Visual Studio Code, Gitlab, Bamboo, Github");

		Language anglais = new Language();
		anglais.setLanguage("Anglais");
		Language italien = new Language();
		italien.setLanguage("Italien");
		datas.setLanguages(Arrays.asList(anglais, italien));
		LanguageLevel courant = new LanguageLevel();
		courant.setLanguageLevel("Courant");
		datas.setLanguageLevels(Arrays.asList(courant, courant));

		datas.setFormationYears(Arrays.asList(new FormationYear("2018"), new FormationYear("2016"), new FormationYear("2016")));
		datas.setFormationTitles(Arrays.asList(new FormationTitle("Master Informatique : Fiabilité et Sécurité Logicielle"),
				new FormationTitle("Licence Mathématiques, Parcours Math-Info (Major)"), new FormationTitle("Licence Informatique")));

		Experience experience = new Experience();
		experience.setJobTitle("Consulant Java EE Angular");
		experience.setJobPeriode("De septembre 2022 à aujourd’hui");
		experience.setJobClient("EN MISSION CHEZ L’AGIRC-ARRCO DEPUIS SCALIAN");
		experience.setJobProject("SCL-ALICE");
		experience.setJobDescription("Projet principal de l’AGIRC-ARRCO pour la mise en retraite complémentaire.");
		experience.setJobFonctions(Arrays.asList(new JobFonction("-Possibilités de créer un dossier de droit direct."),
				new JobFonction("-Possibilités de créer un dossier de droit indirect.")));
		experience.setJobTeamAndMethod("Équipe de 25 personnes en Agile en sous équipes de 7-8 personnes");
		experience.setJobTasks(Arrays.asList(
				new JobTask("-Connection aux apis REST/SOAP du service tiers pour effectuer le paiement et la récupération d’informations."),
				new JobTask("-Création d’api REST Spring aux services internes Angular."), new JobTask("-Création des tests automatisé END to END."),
				new JobTask("-Débogage de l’application back-end et front-end.")));
		experience.setJobSkills("JAVA 8, SPRING 4/5, SPRING DATA, SPRING BATCH, TOMCAT 9, JAVA EE 8, "
				+ "POSTRESQL 11, MATTERMOST, POSTMAN, SPRING SWAGGER, SPRING HAL, "
				+ "SPRING MVC, JPA 2.3, MAVEN, GITLAB, KANBAN, AGILE, HIBERNATE, REST, JSON, "
				+ "ANGULAR 7-9, PROTRACTOR, JASMINE, KARMA, ECLIPSE, INTELLIJ, DOCKER, " + "VAADIN");

		datas.setExperiences(Arrays.asList(experience));
		return datas;
	}

}
