package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

import static com.tinqin.cms.operations.ImportAssetOperation.*;

public interface ImportAssetOperation extends OperationProcessor<ImportAssetResponse, ImportAssetRequest> {
    @Schema(
            description = "Request DTO for importing a book."
    )
    @Getter
    @Builder
    @AllArgsConstructor
    class ImportAssetRequest implements OperationInput {
        @Schema(
                description = "Id"
        )
        @UUID
        @NotEmpty(message = "Id should not be null or empty!")
        public String id;

        @Schema(
                description = "Quantity"
        )
        @NotEmpty(message = "Price should not be null or empty!")
        public String quantityToImport;
    }

    @Schema(
            description = "Response DTO for importing a book."
    )
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    class ImportAssetResponse implements OperationOutput {
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
