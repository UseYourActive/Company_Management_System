package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public interface FindStorageBookByIdOperation extends OperationProcessor<FindStorageBookByIdOperation.FindStorageBookByIdResponse, FindStorageBookByIdOperation.FindStorageBookByIdRequest> {
    @Schema(
            description = "Book Request DTO for finding a storage book by id."
    )
    @Getter
    @Builder
    @AllArgsConstructor
    class FindStorageBookByIdRequest implements OperationInput {
        @Schema(
                description = "Storage book id."
        )
        private String storageBookId;
    }

    @Schema(
            description = "Book Response DTO for finding storage book by id."
    )
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    class FindStorageBookByIdResponse implements OperationOutput {
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
