package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import com.tinqin.cms.base.OperationInput;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

public interface EditDepartmentNameOperation extends OperationProcessor<EditDepartmentNameOperation.EditDepartmentNameResponse, EditDepartmentNameOperation.EditDepartmentNameRequest> {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class EditDepartmentNameRequest implements OperationInput {

        @NotBlank(message = "ID must not be blank")
        @Schema(description = "ID of the department", example = "12345678-1234-1234-1234-123456789abc")
        private String id;

        @NotBlank(message = "Name must not be blank")
        @Schema(description = "New name of the department", example = "Engineering Department")
        private String name;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class EditDepartmentNameResponse implements OperationOutput {
        private String id;
        private String name;
        private String description;
        private String budget;
        private String location;
        private String phoneNumber;
        private String email;
    }
}
