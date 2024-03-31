package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

import java.util.List;

public interface FindLeavesByEmployeeIdAndDateRangeOperation extends OperationProcessor<FindLeavesByEmployeeIdAndDateRangeOperation.FindLeavesByEmployeeIdAndDateRangeResponse, FindLeavesByEmployeeIdAndDateRangeOperation.FindLeavesByEmployeeIdAndDateRangeRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindLeavesByEmployeeIdAndDateRangeRequest implements OperationInput {
        private String employeeId;
        private String startDate;
        private String endDate;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindLeavesByEmployeeIdAndDateRangeResponse implements OperationOutput {
        private List<FindLeavesByEmployeeIdAndDateRangeResponseDTO> findLeavesByEmployeeIdAndDateRangeResponseDTOS;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindLeavesByEmployeeIdAndDateRangeResponseDTO {
        private String id;
        private String employeeId;
        private String startDate;
        private String endDate;
        private String leaveType;
        private String status;
    }
}
