package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

import static com.tinqin.cms.operations.FindEmployeeByUniqueCivilianNumberOperation.*;

public interface FindEmployeeByUniqueCivilianNumberOperation extends OperationProcessor<FindEmployeeByUniqueCivilianNumberResponse, FindEmployeeByUniqueCivilianNumberRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindEmployeeByUniqueCivilianNumberRequest implements OperationInput {
        private String uniqueCivilianNumber;
    }

    class FindEmployeeByUniqueCivilianNumberResponse implements OperationOutput {
    }
}
