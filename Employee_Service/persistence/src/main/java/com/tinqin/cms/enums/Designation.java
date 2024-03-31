package com.tinqin.cms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Job title
@Getter
@AllArgsConstructor
public enum Designation {
    SOFTWARE_ENGINEER("Software engineer"),
    QUALITY_ASSURANCE("Quality assurance"),
    JUNIOR_DEVELOPER("Junior developer"),
    SENIOR_DEVELOPER("Senior developer"),
    TEAM_LEAD("Team lead"),
    PROJECT_MANAGER("Project manager"),
    SOFTWARE_ARCHITECT("Software architect");

    private final String designation;
}
