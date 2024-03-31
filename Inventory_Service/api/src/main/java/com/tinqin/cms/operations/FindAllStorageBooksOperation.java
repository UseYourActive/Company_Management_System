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

public interface FindAllStorageBooksOperation extends OperationProcessor<FindAllStorageBooksOperation.FindAllStorageBooksResponse, FindAllStorageBooksOperation.FindAllStorageBooksRequest> {
    @Schema(
            description = "Book Request DTO for finding all storage books."
    )
    @Getter
    @Builder
    @AllArgsConstructor
    class FindAllStorageBooksRequest implements OperationInput {
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
    class FindAllStorageBooksResponse implements OperationOutput {
        private List<FindAllStorageBooksResponseDTO> findAllStorageBooksResponseDTOS;
    }

    @Schema(
            description = "Storage Book Response DTO."
    )
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    class FindAllStorageBooksResponseDTO {
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
