package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

public interface CreateLeaveOperation extends OperationProcessor<CreateLeaveOperation.CreateLeaveResponse, CreateLeaveOperation.CreateLeaveRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class CreateLeaveRequest implements OperationInput {
        private String employeeId;
        private String startDate;
        private String endDate;
        private String leaveType;
        private String status;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class CreateLeaveResponse implements OperationOutput {
        private String id;
        private String employeeId;
        private String startDate;
        private String endDate;
        private String leaveType;
        private String status;
    }
}
