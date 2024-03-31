package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

import static com.tinqin.cms.operations.FindEmployeesByDesignationOperation.*;

public interface FindEmployeesByDesignationOperation extends OperationProcessor<FindEmployeesByDesignationResponse, FindEmployeesByDesignationRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindEmployeesByDesignationRequest implements OperationInput {
        private String designation;
    }

    class FindEmployeesByDesignationResponse implements OperationOutput {
    }
}
