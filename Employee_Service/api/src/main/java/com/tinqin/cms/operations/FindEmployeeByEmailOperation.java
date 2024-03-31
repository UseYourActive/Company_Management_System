package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

import static com.tinqin.cms.operations.FindEmployeeByEmailOperation.*;

public interface FindEmployeeByEmailOperation extends OperationProcessor<FindEmployeeByEmailResponse, FindEmployeeByEmailRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindEmployeeByEmailRequest implements OperationInput {
        private String email;
    }

    @Getter
    @Setter
    @NoArgsConstructor
//@AllArgsConstructor
//@Builder
    class FindEmployeeByEmailResponse implements OperationOutput {
    }
}
