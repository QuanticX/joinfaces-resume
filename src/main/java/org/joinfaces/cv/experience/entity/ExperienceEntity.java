package org.joinfaces.cv.experience.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.*;
import org.joinfaces.common.entity.AbstractEntity;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ExperienceEntity extends AbstractEntity {

	private String jobTitle;

	private String jobPeriode;

	private String jobClient;

	private String jobProject;

	private String jobDescription;

	@ElementCollection
	private List<String> jobFonctions;

	private String jobTeamAndMethod;

	@ElementCollection
	private List<String> jobTasks;

	private String jobSkills;
}