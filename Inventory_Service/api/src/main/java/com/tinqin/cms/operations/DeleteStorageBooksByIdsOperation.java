package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

public interface DeleteStorageBooksByIdsOperation extends OperationProcessor<DeleteStorageBooksByIdsOperation.DeleteStorageBooksByIdsResponse, DeleteStorageBooksByIdsOperation.DeleteStorageBooksByIdsRequest> {
    @Schema(
            description = "Book Request DTO for deleting storage books by ids."
    )
    @Getter
    @Builder
    @AllArgsConstructor
    public class DeleteStorageBooksByIdsRequest implements OperationInput {
        private List<String> ids;
    }

    @Schema(
            description = "Book Response DTO for deleting storage books by ids."
    )
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    public class DeleteStorageBooksByIdsResponse implements OperationOutput {
        @Schema(
                description = "Status."
        )
        private String status;
    }
}
