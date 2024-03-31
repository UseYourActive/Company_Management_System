package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

public interface ImportBookOperation extends OperationProcessor<ImportBookOperation.ImportBookResponse, ImportBookOperation.ImportBookRequest> {
    @Schema(
            description = "Book Request DTO for importing a book."
    )
    @Getter
    @Builder
    @AllArgsConstructor
    class ImportBookRequest implements OperationInput {
        @Schema(
                description = "Book id"
        )
        @UUID
        @NotEmpty(message = "Book id should not be null or empty!")
        public String bookId;

        @Schema(
                description = "Book quantity"
        )
        @NotEmpty(message = "Price should not be null or empty!")
        public String quantityToImport;
    }

    @Schema(
            description = "Book Response DTO for importing a book."
    )
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    class ImportBookResponse implements OperationOutput {
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
