package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.Builder;
import lombok.Getter;

import static com.tinqin.cms.operations.DeleteEmployeeOperation.*;

public interface DeleteEmployeeOperation extends OperationProcessor<DeleteEmployeeResponse, DeleteEmployeeRequest> {
    @Getter
    @Builder
    class DeleteEmployeeRequest implements OperationInput {
        private String id;
    }

    @Builder
    @Getter
    class DeleteEmployeeResponse implements OperationOutput {
        private Boolean successfullyDeletedEmployee;
    }
}
