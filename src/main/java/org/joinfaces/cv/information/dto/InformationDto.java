package org.joinfaces.cv.information.dto;

import lombok.*;
import org.joinfaces.common.dto.AbstractDto;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

	@Override
	public InformationDto clone() {
		return new InformationDto(name,title,yearOfExperience,subTitle,skillsWork,
				skillsFramworksLanguages,skillsOs,skillsMethods,skillsDb,skillsTools);
	}
}
