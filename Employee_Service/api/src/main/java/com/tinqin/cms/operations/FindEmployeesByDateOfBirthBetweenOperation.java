package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

import static com.tinqin.cms.operations.FindEmployeesByDateOfBirthBetweenOperation.*;

public interface FindEmployeesByDateOfBirthBetweenOperation extends OperationProcessor<FindEmployeesByDateOfBirthBetweenResponse, FindEmployeesByDateOfBirthBetweenRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindEmployeesByDateOfBirthBetweenRequest implements OperationInput {
        private String startDate;
        private String endDate;
    }

    class FindEmployeesByDateOfBirthBetweenResponse implements OperationOutput {
    }
}
