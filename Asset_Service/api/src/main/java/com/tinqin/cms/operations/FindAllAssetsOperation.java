package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.List;

public interface FindAllAssetsOperation extends OperationProcessor<FindAllAssetsOperation.FindAllAssetsResponse, FindAllAssetsOperation.FindAllAssetsRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindAllAssetsResponse implements OperationOutput {
        @Schema(description = "List of assets found")
        private List<FindAllAssetsResponseDTO> findAllAssetsResponseDTOS;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindAllAssetsRequest implements OperationInput {
        @Schema(description = "Page number to retrieve", required = true)
        @NotNull(message = "Page number must not be null")
        @PositiveOrZero(message = "Page number must be positive or zero")
        private Integer pageNumber;

        @Schema(description = "Number of assets per page", required = true)
        @NotNull(message = "Number of assets per page must not be null")
        @Positive(message = "Number of assets per page must be positive")
        private Integer numberOfAssetsPerPage;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindAllAssetsResponseDTO {
        @Schema(description = "ID of the asset")
        private String id;

        @Schema(description = "Name of the asset")
        private String name;

        @Schema(description = "Description of the asset")
        private String description;

        @Schema(description = "Serial number of the asset")
        private String serialNumber;

        @Schema(description = "Type of the asset")
        private String assetType;

        @Schema(description = "Status of the asset")
        private String assetStatus;
    }
}
