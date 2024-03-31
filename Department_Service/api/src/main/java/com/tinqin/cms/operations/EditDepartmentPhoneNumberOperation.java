package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

public interface EditDepartmentPhoneNumberOperation extends OperationProcessor<EditDepartmentPhoneNumberOperation.EditDepartmentPhoneNumberResponse, EditDepartmentPhoneNumberOperation.EditDepartmentPhoneNumberRequest> {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class EditDepartmentPhoneNumberRequest implements OperationInput {

        @NotBlank(message = "ID must not be blank")
        @Schema(description = "ID of the department", example = "12345678-1234-1234-1234-123456789abc")
        private String id;

        @Schema(description = "New phone number of the department", example = "+1234567890")
        private String phoneNumber;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class EditDepartmentPhoneNumberResponse implements OperationOutput {
        private String id;
        private String name;
        private String description;
        private String budget;
        private String location;
        private String phoneNumber;
        private String email;
    }
}
