package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

import java.util.List;

public interface FindAllLeavesOperation extends OperationProcessor<FindAllLeavesOperation.FindAllLeavesResponse, FindAllLeavesOperation.FindAllLeavesRequest> {
    @Getter
    @Setter
    @Builder
    class FindAllLeavesRequest implements OperationInput {
        private Integer pageNumber;
        private Integer numberOfBooksPerPage;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindAllLeavesResponse implements OperationOutput {
        private List<FindAllLeavesResponseDTO> findAllLeavesResponseDTOS;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindAllLeavesResponseDTO {
        private String id;
        private String employeeId;
        private String startDate;
        private String endDate;
        private String leaveType;
        private String status;
    }
}
