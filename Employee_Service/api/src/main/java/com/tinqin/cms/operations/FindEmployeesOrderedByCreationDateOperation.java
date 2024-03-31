package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;

import static com.tinqin.cms.operations.FindEmployeesOrderedByCreationDateOperation.*;

public interface FindEmployeesOrderedByCreationDateOperation extends OperationProcessor<FindEmployeesOrderedByCreationDateResponse, FindEmployeesOrderedByCreationDateRequest> {
    class FindEmployeesOrderedByCreationDateRequest implements OperationInput {
    }

    class FindEmployeesOrderedByCreationDateResponse implements OperationOutput {
    }
}
