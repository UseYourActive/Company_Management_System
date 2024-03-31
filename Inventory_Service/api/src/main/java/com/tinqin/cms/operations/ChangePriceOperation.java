package com.tinqin.cms.operations;

import com.tinqin.cms.annotations.validators.ValidBigDecimalString;
import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

import static com.tinqin.cms.operations.ChangePriceOperation.*;

public interface ChangePriceOperation extends OperationProcessor<ChangePriceResponse, ChangePriceRequest> {
    @Schema(
            description = "Request DTO for changing price."
    )
    @Getter
    @Setter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    class ChangePriceRequest implements OperationInput {
        @Schema(
                description = "id"
        )
        @UUID
        @NotEmpty(message = "Id should not be null or empty!")
        private String id;

        @Schema(
                description = "New price"
        )
        @NotEmpty(message = "New price should not be null or empty!")
        @ValidBigDecimalString
        private String newPrice;
    }

    @Schema(
            description = "Response DTO for changing price."
    )
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    class ChangePriceResponse implements OperationOutput {
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
