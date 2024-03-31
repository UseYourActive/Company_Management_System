package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

import static com.tinqin.cms.operations.FindEmployeesByLastNameOperation.*;

public interface FindEmployeesByLastNameOperation extends OperationProcessor<FindEmployeesByLastNameResponse, FindEmployeesByLastNameRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindEmployeesByLastNameRequest implements OperationInput {
        private String lastName;
    }

    class FindEmployeesByLastNameResponse implements OperationOutput {
    }
}
