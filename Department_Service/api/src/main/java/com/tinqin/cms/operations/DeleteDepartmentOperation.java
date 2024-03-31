package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

public interface DeleteDepartmentOperation extends OperationProcessor<DeleteDepartmentOperation.DeleteDepartmentResponse, DeleteDepartmentOperation.DeleteDepartmentRequest> {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class DeleteDepartmentRequest implements OperationInput {

        @NotBlank(message = "ID must not be blank")
        @Schema(description = "ID of the department to delete", example = "12345678-abcd-90ab-cdef-1234567890ab")
        private String id;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class DeleteDepartmentResponse implements OperationOutput {
        private Boolean successfullyDeletedDepartment;
    }
}
