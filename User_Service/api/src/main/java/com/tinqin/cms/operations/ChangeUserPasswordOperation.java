package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import static com.tinqin.cms.operations.ChangeUserPasswordOperation.*;

public interface ChangeUserPasswordOperation extends OperationProcessor<ChangeUserPasswordResponse, ChangeUserPasswordRequest> {
    @Setter
    @Getter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    class ChangeUserPasswordRequest implements OperationInput {
        @NotEmpty
        private String oldPassword;

        @NotEmpty
        private String password;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    class ChangeUserPasswordResponse implements OperationOutput {
        private Boolean isSuccessful;
    }
}
