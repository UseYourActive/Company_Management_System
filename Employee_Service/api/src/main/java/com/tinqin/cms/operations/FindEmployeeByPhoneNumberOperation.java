package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

import static com.tinqin.cms.operations.FindEmployeeByPhoneNumberOperation.*;

public interface FindEmployeeByPhoneNumberOperation extends OperationProcessor<FindEmployeeByPhoneNumberResponse, FindEmployeeByPhoneNumberRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindEmployeeByPhoneNumberRequest implements OperationInput {
        private String phoneNumber;
    }

    class FindEmployeeByPhoneNumberResponse implements OperationOutput {
    }
}
