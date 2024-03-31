package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

import static com.tinqin.cms.operations.FindEmployeesByIdsOperation.*;

public interface FindEmployeesByIdsOperation extends OperationProcessor<FindEmployeesByIdsResponse, FindEmployeesByIdsRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindEmployeesByIdsRequest implements OperationInput {
        private String ids;
    }

    class FindEmployeesByIdsResponse implements OperationOutput {
    }
}
