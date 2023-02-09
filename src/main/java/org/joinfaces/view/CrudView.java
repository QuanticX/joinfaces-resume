package org.joinfaces.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.event.ActionListener;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.joinfaces.cv.information.dto.InformationDto;
import org.joinfaces.cv.resume.dto.ResumeDto;
import org.joinfaces.cv.resume.service.ResumeService;
import org.primefaces.PrimeFaces;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@SessionScoped
public class CrudView implements Serializable {

    private List<ResumeDto> resumes;

    private transient ResumeDto selectedResume;

    private transient List<ResumeDto> selectedResumes;

    public List<ResumeDto> getResumes() {
        return resumes;
    }

    public void setResumes(List<ResumeDto> resumes) {
        this.resumes = resumes;
    }

    public ResumeDto getSelectedResume() {
        return selectedResume;
    }

    public void setSelectedResume(ResumeDto selectedResume) {
        this.selectedResume = selectedResume;
    }


    public List<ResumeDto> getSelectedResumes() {
        return selectedResumes;
    }



    public void setSelectedResumes(List<ResumeDto> selectedResumes) {
        this.selectedResumes = selectedResumes;
    }

    @Inject
    private ResumeService resumeService;

    @PostConstruct
    public void init() {
        //this.resumes = this.resumeService.readAll();
        this.resumes = new ArrayList<>();
        this.resumes.add(ResumeDto.builder().email("abdelnabi.mohamed.13015@gmail.com")
                .information(InformationDto.builder()
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

                ).build()
        );

    }

    public void openNew() {
        this.selectedResume = new ResumeDto();
        this.selectedResume.setInformation(new InformationDto());
        this.selectedResume.setCertifications(new ArrayList<>());
        this.selectedResume.setExperiences(new ArrayList<>());
        this.selectedResume.setLanguages(new ArrayList<>());
        this.selectedResume.setExperiences(new ArrayList<>());
    }

    public void saveResume() {
        if (this.selectedResume.getId() == null) {
            this.resumes.add(/*this.resumeService.add*/(this.selectedResume));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Resume Added"));
        }
        else {
            //this.resumeService.update(this.selectedResume);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Resume Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageResumeDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-resumes");
    }

    public void deleteResume() {
        this.resumes.remove(this.selectedResume);
        // this.resumeService.delete(this.selectedResume);
        this.selectedResumes.remove(this.selectedResume);
        this.selectedResume = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Resume Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-resumes");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedResumes()) {
            int size = this.selectedResumes.size();
            return size > 1 ? size + " Resumes selected" : "1 Resume selected";
        }

        return "Delete";
    }

    public boolean hasSelectedResumes() {
        return this.selectedResumes != null && !this.selectedResumes.isEmpty();
    }

    public void deleteSelectedResumes() {
        //this.selectedResumes.stream().forEach(selectedResume -> this.resumeService.delete(selectedResume));
        this.resumes.removeAll(this.selectedResumes);
        this.selectedResumes = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Resumes Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-resumes");
        PrimeFaces.current().executeScript("PF('dtResumes').clearFilters()");
    }

}
