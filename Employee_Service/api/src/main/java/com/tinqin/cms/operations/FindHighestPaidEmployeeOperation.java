package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;

import static com.tinqin.cms.operations.FindHighestPaidEmployeeOperation.*;

public interface FindHighestPaidEmployeeOperation extends OperationProcessor<FindHighestPaidEmployeeResponse, FindHighestPaidEmployeeRequest> {
    class FindHighestPaidEmployeeRequest implements OperationInput {
    }

    class FindHighestPaidEmployeeResponse implements OperationOutput {
    }
}
