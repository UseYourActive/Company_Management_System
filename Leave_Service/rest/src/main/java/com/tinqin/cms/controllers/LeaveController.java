package com.tinqin.cms.controllers;

import com.tinqin.cms.operations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "CRUD REST APIs for Leave Management",
        description = "CRUD REST API"
)
@RequiredArgsConstructor
@RestController
@Validated
@RefreshScope
@Slf4j
@CrossOrigin
@RequestMapping(path = "/leave")
public class LeaveController {
    private final FindAllLeavesOperation findAllLeavesOperation;
    private final FindByEmployeeIdOperation findByEmployeeIdOperation;
    private final FindByEmployeeIdAndStatusOperation findByEmployeeIdAndStatusOperation;
    private final FindByStartBetweenDatesOperation findByStartBetweenDatesOperation;
    private final FindLeavesByEmployeeIdAndDateRangeOperation findLeavesByEmployeeIdAndDateRangeOperation;
    private final ApproveLeaveOperation approveLeaveOperation;
    private final RejectLeaveOperation rejectLeaveOperation;
    private final DeleteLeaveOperation deleteLeaveOperation;
    private final CreateLeaveOperation createLeaveOperation;
    private final EditLeaveOperation editLeaveOperation;

    //region GET
    //endregion

    //region POST
    //endregion

    //region PUT
    //endregion

    //region DELETE
    //endregion
}
