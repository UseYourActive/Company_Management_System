package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.List;

public interface FindAllDepartmentsOperation extends OperationProcessor<FindAllDepartmentsOperation.FindAllDepartmentsResponse, FindAllDepartmentsOperation.FindAllDepartmentsRequest> {
    @Builder
    @Getter
    class FindAllDepartmentsRequest implements OperationInput {

        @Min(value = 1, message = "Page number must be greater than or equal to 1")
        @Schema(description = "Page number for pagination", minimum = "1", example = "1")
        private Integer pageNumber;

        @Min(value = 1, message = "Number of books per page must be greater than or equal to 1")
        @Schema(description = "Number of departments per page", minimum = "1", example = "10")
        private Integer numberOfBooksPerPage;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class FindAllDepartmentsResponse implements OperationOutput {
        private List<FindAllDepartmentsResponseDTO> findAllDepartmentsResponseDTOS;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class FindAllDepartmentsResponseDTO {
        @Schema(description = "ID of the department", example = "12345678-1234-1234-1234-123456789abc")
        private String id;

        @Schema(description = "Name of the department", example = "Engineering")
        private String name;

        @Schema(description = "Description of the department", example = "Responsible for developing new technologies")
        private String description;

        @Schema(description = "Budget of the department", example = "100000")
        private String budget;

        @Schema(description = "Location of the department", example = "New York")
        private String location;

        @Schema(description = "Phone number of the department", example = "+1234567890")
        private String phoneNumber;

        @Schema(description = "Email of the department", example = "engineering@example.com")
        private String email;
    }
}
