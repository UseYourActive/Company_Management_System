package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

import static com.tinqin.cms.operations.FindEmployeesBySalaryRangeOperation.*;

public interface FindEmployeesBySalaryRangeOperation extends OperationProcessor<FindEmployeesBySalaryRangeResponse, FindEmployeesBySalaryRangeRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindEmployeesBySalaryRangeRequest implements OperationInput {
        private String minSalary;
        private String maxSalary;
    }

    class FindEmployeesBySalaryRangeResponse implements OperationOutput {
    }
}
