package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import static com.tinqin.cms.operations.FindStorageByIdOperation.*;

public interface FindStorageByIdOperation extends OperationProcessor<FindStorageByIdResponse, FindStorageByIdRequest> {
    @Schema(
            description = "Request DTO for finding a storage book by id."
    )
    @Getter
    @Builder
    @AllArgsConstructor
    class FindStorageByIdRequest implements OperationInput {
        @Schema(
                description = "Id."
        )
        private String id;
    }

    @Schema(
            description = "Response DTO for finding storage book by id."
    )
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    class FindStorageByIdResponse implements OperationOutput {
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
