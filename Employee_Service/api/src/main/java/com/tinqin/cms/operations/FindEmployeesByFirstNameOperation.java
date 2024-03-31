package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

import static com.tinqin.cms.operations.FindEmployeesByFirstNameOperation.*;

public interface FindEmployeesByFirstNameOperation extends OperationProcessor<FindEmployeesByFirstNameResponse, FindEmployeesByFirstNameRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindEmployeesByFirstNameRequest implements OperationInput {
        private String firstName;
    }

    class FindEmployeesByFirstNameResponse implements OperationOutput {
    }
}
