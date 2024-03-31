package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

public interface RejectLeaveOperation extends OperationProcessor<RejectLeaveOperation.RejectLeaveResponse, RejectLeaveOperation.RejectLeaveRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class RejectLeaveRequest implements OperationInput {
        private String id;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class RejectLeaveResponse implements OperationOutput {
        private Boolean isSuccessfullyRejected;
    }
}
