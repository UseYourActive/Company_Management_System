package com.tinqin.cms.operations;

import com.tinqin.cms.annotations.validators.ValidBigDecimalString;
import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

public interface RegisterNewBookOperation extends OperationProcessor<RegisterNewBookOperation.RegisterNewBookResponse, RegisterNewBookOperation.RegisterNewBookRequest> {
    @Schema(
            description = "Book Request DTO for registering a new book."
    )
    @Getter
    @Builder
    @AllArgsConstructor
    class RegisterNewBookRequest implements OperationInput {
        @Schema(
                description = "Book id"
        )
        @UUID
        @NotEmpty(message = "Book id should not be null or empty!")
        private String bookId;

        @Schema(
                description = "Book price"
        )
        @NotEmpty(message = "Price should not be null or empty!")
        @ValidBigDecimalString
        private String price;
    }

    @Schema(
            description = "Book Response DTO for registering a new book."
    )
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    class RegisterNewBookResponse implements OperationOutput {
        @Schema(
                description = "Storage item id"
        )
        private String storageItemId;

        @Schema(
                description = "Book id"
        )
        private String targetBookId;

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
