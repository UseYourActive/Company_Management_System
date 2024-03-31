package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

public interface EditDepartmentLocationOperation extends OperationProcessor<EditDepartmentLocationOperation.EditDepartmentLocationResponse, EditDepartmentLocationOperation.EditDepartmentLocationRequest> {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class EditDepartmentLocationRequest implements OperationInput {

        @NotBlank(message = "ID must not be blank")
        @Schema(description = "ID of the department", example = "12345678-1234-1234-1234-123456789abc")
        private String id;

        @NotBlank(message = "Location must not be blank")
        @Schema(description = "New location of the department", example = "Building B, Floor 2")
        private String location;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class EditDepartmentLocationResponse implements OperationOutput {
        private String id;
        private String name;
        private String description;
        private String budget;
        private String location;
        private String phoneNumber;
        private String email;
    }
}
