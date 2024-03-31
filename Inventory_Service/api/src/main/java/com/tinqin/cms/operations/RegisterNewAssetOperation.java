package com.tinqin.cms.operations;

import com.tinqin.cms.annotations.validators.ValidBigDecimalString;
import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

import static com.tinqin.cms.operations.RegisterNewAssetOperation.*;

public interface RegisterNewAssetOperation extends OperationProcessor<RegisterNewAssetResponse, RegisterNewAssetRequest> {
    @Schema(
            description = "Request DTO for registering a new book."
    )
    @Getter
    @Builder
    @AllArgsConstructor
    class RegisterNewAssetRequest implements OperationInput {
        @Schema(
                description = "Id"
        )
        @UUID
        @NotEmpty(message = "Id should not be null or empty!")
        private String id;

        @Schema(
                description = "Price"
        )
        @NotEmpty(message = "Price should not be null or empty!")
        @ValidBigDecimalString
        private String price;
    }

    @Schema(
            description = "Response DTO for registering a new book."
    )
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    class RegisterNewAssetResponse implements OperationOutput {
        @Schema(
                description = "Id"
        )
        private String id;

        @Schema(
                description = "Asset id"
        )
        private String targetAssetId;

        @Schema(
                description = "Price"
        )
        private String price;

        @Schema(
                description = "Quantity"
        )
        private String quantity;
    }
}
