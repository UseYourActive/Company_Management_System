package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

public interface DeleteLeaveOperation extends OperationProcessor<DeleteLeaveOperation.DeleteLeaveResponse, DeleteLeaveOperation.DeleteLeaveRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class DeleteLeaveRequest implements OperationInput {
        private String id;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class DeleteLeaveResponse implements OperationOutput {
        private Boolean isSuccessful;
    }
}
