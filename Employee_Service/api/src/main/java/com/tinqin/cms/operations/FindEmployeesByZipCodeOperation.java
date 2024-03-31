package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

import static com.tinqin.cms.operations.FindEmployeesByZipCodeOperation.*;

public interface FindEmployeesByZipCodeOperation extends OperationProcessor<FindEmployeesByZipCodeResponse, FindEmployeesByZipCodeRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindEmployeesByZipCodeRequest implements OperationInput {
        private String zipCode;
    }

    class FindEmployeesByZipCodeResponse implements OperationOutput {
    }
}
