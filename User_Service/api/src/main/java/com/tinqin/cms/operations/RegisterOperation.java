package com.tinqin.cms.operations;

import com.tinqin.cms.annotations.validators.StrongPassword;
import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.tinqin.cms.operations.RegisterOperation.*;

public interface RegisterOperation extends OperationProcessor<RegisterResponse, RegisterRequest> {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    class RegisterRequest implements OperationInput {
        @Schema(description = "Username", example = "john_doe")
        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
        private String username;

        @StrongPassword
        @Schema(description = "Password", example = "Password123")
        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
        private String password;

        @StrongPassword
        @Schema(description = "Repeated password", example = "Password123")
        @NotBlank(message = "Repeated password is required")
        @Size(min = 8, max = 50, message = "Repeated password must be between 8 and 50 characters")
        private String repeatedPassword;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    class RegisterResponse implements OperationOutput {
        private String id;
        private String username;
        private String role;
        private String jwt;
    }
}
