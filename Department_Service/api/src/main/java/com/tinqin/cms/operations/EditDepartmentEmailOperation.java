package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

public interface EditDepartmentEmailOperation extends OperationProcessor<EditDepartmentEmailOperation.EditDepartmentEmailResponse, EditDepartmentEmailOperation.EditDepartmentEmailRequest> {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class EditDepartmentEmailRequest implements OperationInput {

        @NotBlank(message = "ID must not be blank")
        @Schema(description = "ID of the department", example = "12345678-1234-1234-1234-123456789abc")
        private String id;

        @Email(message = "Invalid email format")
        @Schema(description = "New email address of the department", example = "engineering@example.com")
        private String email;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class EditDepartmentEmailResponse implements OperationOutput {
        private String id;
        private String name;
        private String description;
        private String budget;
        private String location;
        private String phoneNumber;
        private String email;
    }
}
