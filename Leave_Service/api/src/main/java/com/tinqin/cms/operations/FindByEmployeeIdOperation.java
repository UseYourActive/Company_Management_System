package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

import java.util.List;

public interface FindByEmployeeIdOperation extends OperationProcessor<FindByEmployeeIdOperation.FindByEmployeeIdResponse, FindByEmployeeIdOperation.FindByEmployeeIdRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindByEmployeeIdRequest implements OperationInput {
        private String employeeId;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindByEmployeeIdResponse implements OperationOutput {
        private List<FindByEmployeeIdResponseDTO> findByEmployeeIdResponseDTOS;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindByEmployeeIdResponseDTO {
        private String id;
        private String employeeId;
        private String startDate;
        private String endDate;
        private String leaveType;
        private String status;
    }
}
