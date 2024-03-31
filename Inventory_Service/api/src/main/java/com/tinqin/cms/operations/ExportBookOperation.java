package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

public interface ExportBookOperation extends OperationProcessor<ExportBookOperation.ExportBookResponse, ExportBookOperation.ExportBookRequest> {
    @Schema(
            description = "Book Request DTO for exporting books / a book."
    )
    @Getter
    @Builder
    @AllArgsConstructor
    class ExportBookRequest implements OperationInput {
        @Schema(
                description = "Book id"
        )
        @UUID
        @NotEmpty(message = "Book id should not be null or empty!")
        public String bookId;

        @Schema(
                description = "Book quantity to export"
        )
        @NotEmpty(message = "Quantity to export should not be null or empty!")
        public String quantityToExport;
    }

    @Schema(
            description = "Book Response DTO for exporting a book."
    )
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    class ExportBookResponse implements OperationOutput {
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
