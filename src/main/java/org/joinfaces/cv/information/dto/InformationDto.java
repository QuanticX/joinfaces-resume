package org.joinfaces.cv.information.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.joinfaces.common.dto.AbstractDto;

@Builder
@Getter
@Setter
public class InformationDto extends AbstractDto {

	private String name;

	private String title;

	private Integer yearOfExperience;

	private String subTitle;

	private String skillsWork;

	private String skillsFramworksLanguages;

	private String skillsOs;

	private String skillsMethods;

	private String skillsDb;

	private String skillsTools;


}
