package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import static com.tinqin.cms.operations.DeleteStorageByIdOperation.*;

public interface DeleteStorageByIdOperation extends OperationProcessor<DeleteStorageByIdResponse, DeleteStorageByIdRequest> {
    @Schema(
            description = "Request DTO for deleting a storage book by id."
    )
    @Getter
    @Builder
    @AllArgsConstructor
    class DeleteStorageByIdRequest implements OperationInput {
        @Schema(
                description = "Storage book id."
        )
        private String id;
    }

    @Schema(
            description = "Response DTO for deleting a storage book by id."
    )
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    class DeleteStorageByIdResponse implements OperationOutput {
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

        @Schema(
                description = "Status"
        )
        private String status;
    }
}
