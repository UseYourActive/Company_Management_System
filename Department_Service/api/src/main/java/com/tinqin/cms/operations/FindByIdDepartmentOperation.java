package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public interface FindByIdDepartmentOperation extends OperationProcessor<FindByIdDepartmentOperation.FindByIdDepartmentResponse, FindByIdDepartmentOperation.FindByIdDepartmentRequest> {
    @Builder
    @Getter
    class FindByIdDepartmentRequest implements OperationInput {
        @NotBlank(message = "ID cannot be blank")
        @Schema(description = "Unique identifier of the department", example = "123e4567-e89b-12d3-a456-426614174000")
        private String id;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    class FindByIdDepartmentResponse implements OperationOutput {
        private String id;
        private String name;
        private String description;
        private String budget;
        private String location;
        private String phoneNumber;
        private String email;
    }
}
