package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;

public interface FindByStatusOperation extends OperationProcessor<FindByStatusOperation.FindByStatusResponse, FindByStatusOperation.FindByStatusRequest> {
    class FindByStatusRequest implements OperationInput {
    }

    class FindByStatusResponse implements OperationOutput {
    }
}
