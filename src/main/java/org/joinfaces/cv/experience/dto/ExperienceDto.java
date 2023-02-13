package org.joinfaces.cv.experience.dto;

import lombok.*;
import org.joinfaces.common.dto.AbstractDto;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDto extends AbstractDto {

	private String jobTitle;

	private String jobPeriode;

	private String jobClient;

	private String jobProject;

	private String jobDescription;
	
	private List<String> jobFonctions;

	private String jobTeamAndMethod;

	private List<String> jobTasks;

	private String jobSkills;

	@Override
	public ExperienceDto clone(){
		return ExperienceDto.builder()
				.jobTitle(jobTitle)
				.jobPeriode(jobPeriode)
				.jobClient(jobClient)
				.jobProject(jobProject)
				.jobDescription(jobDescription)
				.jobFonctions(jobFonctions.stream().map(String::new).toList())
				.jobTeamAndMethod(jobTeamAndMethod)
				.jobTasks(jobTasks.stream().map(String::new).toList())
				.jobSkills(jobSkills)
				.build();
	}
}
