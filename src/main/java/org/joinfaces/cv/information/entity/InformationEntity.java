package org.joinfaces.cv.information.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.*;
import org.joinfaces.common.entity.AbstractEntity;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class InformationEntity extends AbstractEntity {

	private String name;

	private String title;

	private Integer yearOfExperience;

	private String subTitle;

	private String skillsWork;

	@Lob
	private String skillsFramworksLanguages;
	@Lob
	private String skillsOs;
	@Lob
	private String skillsMethods;
	@Lob
	private String skillsDb;
	@Lob
	private String skillsTools;


}
