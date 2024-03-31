package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

import static com.tinqin.cms.operations.DeleteStoragesByIdsOperation.*;

public interface DeleteStoragesByIdsOperation extends OperationProcessor<DeleteStoragesByIdsResponse, DeleteStoragesByIdsRequest> {
    @Schema(
            description = "Request DTO for deleting storage books by ids."
    )
    @Getter
    @Builder
    @AllArgsConstructor
    class DeleteStoragesByIdsRequest implements OperationInput {
        private List<String> ids;
    }

    @Schema(
            description = "Response DTO for deleting storage books by ids."
    )
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    class DeleteStoragesByIdsResponse implements OperationOutput {
        @Schema(
                description = "Status."
        )
        private String status;
    }
}
