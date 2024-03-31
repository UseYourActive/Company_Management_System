package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

import static com.tinqin.cms.operations.FindEmployeesByDesignationOrderBySalaryOperation.*;

public interface FindEmployeesByDesignationOrderBySalaryOperation extends OperationProcessor<FindEmployeesByDesignationOrderBySalaryResponse, FindEmployeesByDesignationOrderBySalaryRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindEmployeesByDesignationOrderBySalaryRequest implements OperationInput {
        private String designation;
    }

    class FindEmployeesByDesignationOrderBySalaryResponse implements OperationOutput {
    }
}
