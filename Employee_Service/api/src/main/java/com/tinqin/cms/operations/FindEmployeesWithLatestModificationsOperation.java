package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;

import static com.tinqin.cms.operations.FindEmployeesWithLatestModificationsOperation.*;

public interface FindEmployeesWithLatestModificationsOperation extends OperationProcessor<FindEmployeesWithLatestModificationsResponse, FindEmployeesWithLatestModificationsRequest> {
    class FindEmployeesWithLatestModificationsRequest implements OperationInput {
    }

    class FindEmployeesWithLatestModificationsResponse implements OperationOutput {
    }
}
