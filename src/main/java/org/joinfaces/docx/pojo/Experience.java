package org.joinfaces.docx.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Experience {

	private String jobTitle;

	private String jobPeriode;

	private String jobClient;

	private String jobProject;

	private String jobDescription;

	private List<JobFonction> jobFonctions;

	private String jobTeamAndMethod;

	private List<JobTask> jobTasks;

	private String jobSkills;
}
