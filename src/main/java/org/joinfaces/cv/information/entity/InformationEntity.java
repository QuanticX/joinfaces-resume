package org.joinfaces.cv.information.entity;

import jakarta.persistence.Entity;
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

	private String skillsFramworksLanguages;

	private String skillsOs;

	private String skillsMethods;

	private String skillsDb;

	private String skillsTools;


}
