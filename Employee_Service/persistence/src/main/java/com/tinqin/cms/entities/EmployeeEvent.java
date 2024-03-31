package com.tinqin.cms.entities;

import com.tinqin.cms.enums.EventType;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEvent {
    private UUID employeeId;
    private EventType eventType;
    private String eventDescription;
}
