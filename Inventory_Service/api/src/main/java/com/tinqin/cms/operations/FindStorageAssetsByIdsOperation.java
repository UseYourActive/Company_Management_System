package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

import static com.tinqin.cms.operations.FindStorageAssetsByIdsOperation.*;

public interface FindStorageAssetsByIdsOperation extends OperationProcessor<FindStorageAssetsByIdsResponse, FindStorageAssetsByIdsRequest> {
    @Schema(
            description = "Request DTO for finding all storage books by ids."
    )
    @Getter
    @Builder
    @AllArgsConstructor
    class FindStorageAssetsByIdsRequest implements OperationInput {
        @Schema(
                description = "Input ids"
        )
        private List<String> ids;
    }

    @Schema(
            description = "Response DTO for finding storage books by ids."
    )
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    class FindStorageAssetsByIdsResponse implements OperationOutput {
        private List<FindStorageBooksByIdsResponseDTO> findStorageBooksByIdsResponseDTOS;
    }

    @Schema(
            description = "Storage Book Response DTO."
    )
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    class FindStorageBooksByIdsResponseDTO {
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
