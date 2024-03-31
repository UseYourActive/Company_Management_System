package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

import static com.tinqin.cms.operations.ExportAssetOperation.*;

public interface ExportAssetOperation extends OperationProcessor<ExportAssetResponse, ExportAssetRequest> {
    @Schema(
            description = "Request DTO for exporting assets."
    )
    @Getter
    @Builder
    @AllArgsConstructor
    class ExportAssetRequest implements OperationInput {
        @Schema(
                description = "Id"
        )
        @UUID
        @NotEmpty(message = "Id should not be null or empty!")
        public String id;

        @Schema(
                description = "Quantity to export"
        )
        @NotEmpty(message = "Quantity to export should not be null or empty!")
        public String quantityToExport;
    }

    @Schema(
            description = "Response DTO for exporting an asset."
    )
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    class ExportAssetResponse implements OperationOutput {
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
