package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

import java.util.List;

public interface FindByStartBetweenDatesOperation extends OperationProcessor<FindByStartBetweenDatesOperation.FindByStartBetweenDatesResponse, FindByStartBetweenDatesOperation.FindByStartBetweenDatesRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindByStartBetweenDatesRequest implements OperationInput {
        private String startDate;
        private String endDate;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindByStartBetweenDatesResponse implements OperationOutput {
        private List<FindByStartBetweenDatesResponseDTO> findByStartBetweenDatesResponseDTOS;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindByStartBetweenDatesResponseDTO {
        private String id;
        private String employeeId;
        private String startDate;
        private String endDate;
        private String leaveType;
        private String status;
    }
}
