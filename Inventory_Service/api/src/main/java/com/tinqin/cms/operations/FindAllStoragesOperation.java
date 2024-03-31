package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.List;

import static com.tinqin.cms.operations.FindAllStoragesOperation.*;

public interface FindAllStoragesOperation extends OperationProcessor<FindAllStoragesResponse, FindAllStoragesRequest> {
    @Schema(
            description = "Book Request DTO for finding all storage books."
    )
    @Getter
    @Builder
    @AllArgsConstructor
    class FindAllStoragesRequest implements OperationInput {
        @Schema(
                description = "Page number for Pagination"
        )
        @NotEmpty(message = "Page number should not be null or empty!")
        @PositiveOrZero
        private Integer pageNumber;

        @Schema(
                description = "Number of pages per page for Pagination"
        )
        @NotEmpty(message = "Number of books per page should not be null or empty!")
        @Positive
        private Integer numberOfBooksPerPage;
    }

    @Schema(
            description = "Book Response DTO for finding all storage books."
    )
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    class FindAllStoragesResponse implements OperationOutput {
        private List<FindAllStoragesResponseDTO> findAllStoragesResponseDTOS;
    }

    @Schema(
            description = "Storage Response DTO."
    )
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    class FindAllStoragesResponseDTO {
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
