package com.tinqin.cms.operations;

import com.tinqin.cms.annotations.validators.ValidBigDecimal;
import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

public interface EditDepartmentBudgetOperation extends OperationProcessor<EditDepartmentBudgetOperation.EditDepartmentBudgetResponse, EditDepartmentBudgetOperation.EditDepartmentBudgetRequest> {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class EditDepartmentBudgetRequest implements OperationInput {

        @NotBlank(message = "ID must not be blank")
        @Schema(description = "ID of the department", example = "12345678-1234-1234-1234-123456789abc")
        private String id;

        @ValidBigDecimal
        @Schema(description = "New budget of the department", example = "10000.00")
        private String budget;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class EditDepartmentBudgetResponse implements OperationOutput {
        private String id;
        private String name;
        private String description;
        private String budget;
        private String location;
        private String phoneNumber;
        private String email;
    }
}
