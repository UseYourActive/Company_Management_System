package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

import static com.tinqin.cms.operations.FindEmployeesByEmployeeDetailsMaritalStatusOperation.*;

public interface FindEmployeesByEmployeeDetailsMaritalStatusOperation extends OperationProcessor<FindEmployeesByEmployeeDetailsMaritalStatusResponse, FindEmployeesByEmployeeDetailsMaritalStatusRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindEmployeesByEmployeeDetailsMaritalStatusRequest implements OperationInput {
        private String maritalStatus;
    }

    class FindEmployeesByEmployeeDetailsMaritalStatusResponse implements OperationOutput {
    }
}
