package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;

import static com.tinqin.cms.operations.FindTopThreeByOrderBySalaryAmountDescOperation.*;

public interface FindTopThreeByOrderBySalaryAmountDescOperation extends OperationProcessor<FindTopThreeByOrderBySalaryAmountDescResponse, FindTopThreeByOrderBySalaryAmountDescRequest> {
    class FindTopThreeByOrderBySalaryAmountDescRequest implements OperationInput {
    }

    class FindTopThreeByOrderBySalaryAmountDescResponse implements OperationOutput {
    }
}
