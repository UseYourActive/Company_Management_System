package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

public interface FindStorageBooksByIdsOperation extends OperationProcessor<FindStorageBooksByIdsOperation.FindStorageBooksByIdsResponse, FindStorageBooksByIdsOperation.FindStorageBooksByIdsRequest> {
    @Schema(
            description = "Book Request DTO for finding all storage books by ids."
    )
    @Getter
    @Builder
    @AllArgsConstructor
    class FindStorageBooksByIdsRequest implements OperationInput {
        @Schema(
                description = "Input ids"
        )
        private List<String> ids;
    }

    @Schema(
            description = "Book Response DTO for finding storage books by ids."
    )
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    class FindStorageBooksByIdsResponse implements OperationOutput {
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
