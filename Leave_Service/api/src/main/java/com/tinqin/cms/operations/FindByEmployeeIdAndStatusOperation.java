package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

import java.util.List;

public interface FindByEmployeeIdAndStatusOperation extends OperationProcessor<FindByEmployeeIdAndStatusOperation.FindByEmployeeIdAndStatusResponse, FindByEmployeeIdAndStatusOperation.FindByEmployeeIdAndStatusRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindByEmployeeIdAndStatusRequest implements OperationInput {
        private String employeeId;
        private String status;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindByEmployeeIdAndStatusResponse implements OperationOutput {
        private List<FindByEmployeeIdAndStatusResponseDTO> findByEmployeeIdAndStatusResponseDTOS;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindByEmployeeIdAndStatusResponseDTO {
        private String id;
        private String employeeId;
        private String startDate;
        private String endDate;
        private String leaveType;
        private String status;
    }
}
