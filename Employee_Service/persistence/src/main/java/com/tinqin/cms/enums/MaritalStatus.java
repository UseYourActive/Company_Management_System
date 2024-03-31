package com.tinqin.cms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MaritalStatus {
    DIVORCED("Divorced"),
    SEPARATED("Separated"),
    SINGLE("Single"),
    MARRIED("Married"),
    WIDOWED("Widowed");

    private final String maritalStatus;
}
