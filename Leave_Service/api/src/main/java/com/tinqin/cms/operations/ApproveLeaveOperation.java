package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

public interface ApproveLeaveOperation extends OperationProcessor<ApproveLeaveOperation.ApproveLeaveResponse, ApproveLeaveOperation.ApproveLeaveRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class ApproveLeaveRequest implements OperationInput {
        private String id;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class ApproveLeaveResponse implements OperationOutput {
        private Boolean successfullyApprovedLeave;
    }
}
