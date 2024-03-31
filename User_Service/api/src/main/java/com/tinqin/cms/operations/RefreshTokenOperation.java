package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import static com.tinqin.cms.operations.RefreshTokenOperation.*;

public interface RefreshTokenOperation extends OperationProcessor<RefreshTokenResponse, RefreshTokenRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class RefreshTokenRequest implements OperationInput {
        @NotBlank(message = "Refresh token must not be blank")
        @Schema(description = "The refresh token to be used for refreshing the authentication token.")
        private String token;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class RefreshTokenResponse implements OperationOutput {

        @Schema(description = "The new authentication token.")
        private String newToken;

        @Schema(description = "The refresh token.")
        private String refreshedToken;
    }
}
