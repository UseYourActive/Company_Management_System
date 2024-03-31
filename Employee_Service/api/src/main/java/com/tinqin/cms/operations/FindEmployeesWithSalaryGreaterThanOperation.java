package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

import static com.tinqin.cms.operations.FindEmployeesWithSalaryGreaterThanOperation.*;

public interface FindEmployeesWithSalaryGreaterThanOperation extends OperationProcessor<FindEmployeesWithSalaryGreaterThanResponse, FindEmployeesWithSalaryGreaterThanRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindEmployeesWithSalaryGreaterThanRequest implements OperationInput {
        private String minSalary;
    }

    class FindEmployeesWithSalaryGreaterThanResponse implements OperationOutput {
    }
}
