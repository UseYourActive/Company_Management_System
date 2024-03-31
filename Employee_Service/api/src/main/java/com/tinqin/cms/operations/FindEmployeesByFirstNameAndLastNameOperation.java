package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

import static com.tinqin.cms.operations.FindEmployeesByFirstNameAndLastNameOperation.*;

public interface FindEmployeesByFirstNameAndLastNameOperation extends OperationProcessor<FindEmployeesByFirstNameAndLastNameResponse, FindEmployeesByFirstNameAndLastNameRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindEmployeesByFirstNameAndLastNameRequest implements OperationInput {
        private String firstName;
        private String lastName;
    }

    class FindEmployeesByFirstNameAndLastNameResponse implements OperationOutput {
    }

}
