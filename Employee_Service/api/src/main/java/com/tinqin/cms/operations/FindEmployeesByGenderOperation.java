package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

import static com.tinqin.cms.operations.FindEmployeesByGenderOperation.*;

public interface FindEmployeesByGenderOperation extends OperationProcessor<FindEmployeesByGenderResponse, FindEmployeesByGenderRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindEmployeesByGenderRequest implements OperationInput {
        private String gender;
    }

    class FindEmployeesByGenderResponse implements OperationOutput {
    }
}
