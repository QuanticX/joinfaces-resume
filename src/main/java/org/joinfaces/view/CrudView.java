package org.joinfaces.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import org.joinfaces.cv.certification.entity.CertificationEntity;
import org.joinfaces.cv.experience.entity.ExperienceEntity;
import org.joinfaces.cv.formation.entity.FormationEntity;
import org.joinfaces.cv.information.entity.InformationEntity;
import org.joinfaces.cv.language.entity.LanguageEntity;
import org.joinfaces.cv.resume.entity.ResumeEntity;
import org.joinfaces.cv.resume.service.ResumeService;
import org.joinfaces.cv.docx.FillTemplateResumeDocx;
import org.primefaces.PrimeFaces;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@SessionScoped
public class CrudView implements Serializable {



    private List<ResumeEntity> resumes;

    private ResumeEntity selectedResume;

    private List<ResumeEntity> selectedResumes;

    public List<ResumeEntity> getResumes() {
        return resumes;
    }

    public void setResumes(List<ResumeEntity> resumes) {
        this.resumes = resumes;
    }

    public ResumeEntity getSelectedResume() {
        return selectedResume;
    }

    public void setSelectedResume(ResumeEntity selectedResume) {
        this.selectedResume = selectedResume;
    }


    public List<ResumeEntity> getSelectedResumes() {
        return selectedResumes;
    }


    public void setSelectedResumes(List<ResumeEntity> selectedResumes) {
        this.selectedResumes = selectedResumes;
    }

    @Inject
    private ResumeService resumeService;

    @Inject
    private FillTemplateResumeDocx fillTemplateResumeDocx;

    @PostConstruct
    public void init() {
        this.resumes = this.resumeService.readAll();
        if (this.resumes.isEmpty()) {
            this.resumes.add(this.resumeService.add(ResumeEntity.builder().email("abdelnabi.mohamed.13015@gmail.com")
                    .information(InformationEntity.builder()
                            .name("M. Ab.")
                            .yearOfExperience(Integer.valueOf("4"))
                            .title("DEVELOPPEUR : JAVA EE SPRING ANGULAR")
                            .subTitle("ANALYSTE, DATA SCIENTIST, CONCEPTION ET DEVELOPPEMENT")
                            .skillsWork("COMMUNICATION, TRAVAIL EN EQUIPE, ADAPTABILITE, RIGUEUR, ANALYSE, CREATIVITE")
                            .skillsFramworksLanguages("Java SE 8/11, Groovy, Scala, HTML5, JavaScript, JSON, XML/XPATH, "
                                    + "TypeScript, SQL, PL/SQL, Python, C, CSS, Matlab, R, Angular, Hibernate, Java"
                                    + "EE 7/8, JPA, REST, SOAP, JSP, JSF, Spring, Twitter Bootstrap, Spring Boot, "
                                    + "Node.Js, Material, Animation, Criteria API, JPQL, JMS, RxJS, JDBC, Ansible, "
                                    + "JSF, Web Socket, NgRx, Redux, Spring Boot / Web & Reactive Web & Web"
                                    + "Services / Data / Kafka / Cloud & Cloud Stream / Batch / Retry / DevTools"
                                    + "/ WebSocket / Validation / LDAP / Oauth2 / Security , Hadoop, Spark,"
                                    + "NestJS, JUnit, Jasmine, Protractor, Selenium, Karma, JMeter, Mockito, "
                                    + "Liquidbase, Flyway, Tomcat, Jetty, Undertow, WildFly, Netty")
                            .skillsOs("Windows, Linux (Ubuntu/Cent OS), Mac OS")
                            .skillsMethods("Cascade, Agile : SCRUM/KANABAN, Spirale, TDD, Merise, QQOQCCP, UML")
                            .skillsDb("Oracle, SQL Server, PostgreSQL, MongoDB, Cassandra, Hive, HBase, ElasticSearch, H2")
                            .skillsTools("JIRA, Confluence, Maven, Gradle, NPM, Sonar Qube, Memory Analyzer Tool, "
                                    + "PuTTY, Docker, Elastic Stack, Prometheus, Kubernetes, Amazon Web "
                                    + "Services, Heroku, Azure, Cloud Foundry, Google Cloud Platform, VMWare, "
                                    + "Hyper-V, VirtualBox, Nexus, Jenkins, Sketch UP, Adobe XD, Adobe "
                                    + "Photoshop, Adobe Illustrator, Qlik Sense, Grafana, Power BI, Excel, Eclipse, "
                                    + "IntelliJ IDEA, Visual Studio Code, Gitlab, Bamboo, Github")
                            .build()

                    ).languages(new ArrayList<>())
                    .formations(new ArrayList<>())
                    .experiences(new ArrayList<>())
                    .certifications(new ArrayList<>())
                    .languages(new ArrayList<>()).build())
            );
        }
    }

    public void openNew() {
        this.selectedResume = new ResumeEntity();
        this.selectedResume.setInformation(new InformationEntity());
        this.selectedResume.setFormations(new ArrayList<>());
        this.selectedResume.setCertifications(new ArrayList<>());
        this.selectedResume.setExperiences(new ArrayList<>());
        this.selectedResume.setLanguages(new ArrayList<>());
    }

    public StreamedContent download() throws IOException {
        return fillTemplateResumeDocx.fillTemplateAndReturnStream(fillTemplateResumeDocx.mapResume(this.selectedResume));
    }


    public void saveResume() {
        if (this.selectedResume.getId() == null) {
            this.resumes.add(this.resumeService.add(this.selectedResume));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("CV ajouté"));
        } else {
            this.resumeService.update(this.selectedResume);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("CV mis à jour"));
        }

        PrimeFaces.current().executeScript("PF('manageResumeDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-resumes");
    }

    public void deleteResume() {
        this.resumes.remove(this.selectedResume);
        this.resumeService.delete(this.selectedResume);
        this.selectedResumes.remove(this.selectedResume);
        this.selectedResume = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("CV supprimé"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-resumes");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedResumes()) {
            int size = this.selectedResumes.size();
            return size > 1 ? size + " CVs sélectionnés" : "1 CV sélectionné";
        }

        return "Supprimer";
    }

    public boolean hasSelectedResumes() {
        return this.selectedResumes != null && !this.selectedResumes.isEmpty();
    }

    public void deleteSelectedResumes() {
        this.selectedResumes.stream().forEach(selectedResume -> this.resumeService.delete(selectedResume));
        this.resumes.removeAll(this.selectedResumes);
        this.selectedResumes = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Resumes Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-resumes");
        PrimeFaces.current().executeScript("PF('dtResumes').clearFilters()");
    }

    public void createLanguage() {
        this.selectedResume.getLanguages().add(new LanguageEntity("", ""));
    }

    public void createFormation() {
        this.selectedResume.getFormations().add(new FormationEntity());
    }

    public void createCertification() {
        this.selectedResume.getCertifications().add(new CertificationEntity());
    }

    public void createExperience() {
        this.selectedResume.getExperiences().add(new ExperienceEntity("", "", "", "", "", new ArrayList<>(), "", new ArrayList<>(), ""));
    }

    public void createFonction(ExperienceEntity experienceEntity) {
        experienceEntity.getJobFonctions().add("");
    }

    public void createTask(ExperienceEntity experienceEntity) {
        experienceEntity.getJobTasks().add("");
    }
}
