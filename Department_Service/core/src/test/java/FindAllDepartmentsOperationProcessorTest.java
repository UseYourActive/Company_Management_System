import com.tinqin.cms.converters.FindAllDepartmentsMapper;
import com.tinqin.cms.entities.Department;
import com.tinqin.cms.operations.FindAllDepartmentsOperation;
import com.tinqin.cms.processors.FindAllDepartmentsOperationProcessor;
import com.tinqin.cms.repositories.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.tinqin.cms.operations.FindAllDepartmentsOperation.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindAllDepartmentsOperationProcessorTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private FindAllDepartmentsMapper converter;

    @InjectMocks
    private FindAllDepartmentsOperationProcessor processor;

    @Test
    public void testProcess_FindAllDepartments_Success() {
        int pageNumber = 1;
        int numberOfDepartmentsPerPage = 10;
        FindAllDepartmentsRequest request = FindAllDepartmentsRequest.builder()
                .pageNumber(pageNumber)
                .numberOfBooksPerPage(numberOfDepartmentsPerPage)
                .build();

        // Mocking department data
        List<Department> departments = Arrays.asList(
                Department.builder()
                        .id(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"))
                        .name("Engineering")
                        .description("Responsible for software development")
                        .budget(new BigDecimal("10000.00"))
                        .location("New York")
                        .phoneNumber("123-456-7890")
                        .email("engineering@example.com")
                        .build(),
                Department.builder()
                        .id(UUID.fromString("123e4567-e89b-12d3-a456-426614174001"))
                        .name("HR")
                        .description("Responsible for human resources management")
                        .budget(new BigDecimal("5000.00"))
                        .location("California")
                        .phoneNumber("987-654-3210")
                        .email("hr@example.com")
                        .build()
        );

        Page<Department> page = new PageImpl<>(departments);
        when(departmentRepository.findAll(any(PageRequest.class))).thenReturn(page);

        FindAllDepartmentsResponseDTO dto1 = FindAllDepartmentsResponseDTO.builder()
                .id("123e4567-e89b-12d3-a456-426614174000")
                .name("Engineering")
                .description("Responsible for software development")
                .budget("10000.00")
                .location("New York")
                .phoneNumber("123-456-7890")
                .email("engineering@example.com")
                .build();
        FindAllDepartmentsResponseDTO dto2 = FindAllDepartmentsResponseDTO.builder()
                .id("123e4567-e89b-12d3-a456-426614174001")
                .name("HR")
                .description("Responsible for human resources management")
                .budget("5000.00")
                .location("California")
                .phoneNumber("987-654-3210")
                .email("hr@example.com")
                .build();
        when(converter.convert(departments.get(0))).thenReturn(dto1);
        when(converter.convert(departments.get(1))).thenReturn(dto2);

        FindAllDepartmentsResponse response = processor.process(request);

        assertNotNull(response);
        assertEquals(2, response.getFindAllDepartmentsResponseDTOS().size());
        assertEquals(dto1, response.getFindAllDepartmentsResponseDTOS().get(0));
        assertEquals(dto2, response.getFindAllDepartmentsResponseDTOS().get(1));
    }

    @Test
    public void testProcess_FindAllDepartments_EmptyList() {
        int pageNumber = 1;
        int numberOfDepartmentsPerPage = 10;
        FindAllDepartmentsRequest request = FindAllDepartmentsRequest.builder()
                .pageNumber(pageNumber)
                .numberOfBooksPerPage(numberOfDepartmentsPerPage)
                .build();

        Page<Department> emptyPage = new PageImpl<>(Collections.emptyList());
        when(departmentRepository.findAll(any(PageRequest.class))).thenReturn(emptyPage);

        FindAllDepartmentsResponse response = processor.process(request);

        assertNotNull(response);
        assertTrue(response.getFindAllDepartmentsResponseDTOS().isEmpty());
    }

    @Test
    public void testProcess_FindAllDepartments_NullRequest() {
        assertThrows(NullPointerException.class, () -> processor.process(null));
    }

    @Test
    public void testProcess_FindAllDepartments_NullRepositoryResponse() {
        int pageNumber = 1;
        int numberOfDepartmentsPerPage = 10;
        FindAllDepartmentsOperation.FindAllDepartmentsRequest request = FindAllDepartmentsOperation.FindAllDepartmentsRequest.builder()
                .pageNumber(pageNumber)
                .numberOfBooksPerPage(numberOfDepartmentsPerPage)
                .build();

        when(departmentRepository.findAll(any(PageRequest.class))).thenReturn(null);

        assertThrows(NullPointerException.class, () -> processor.process(request));
    }
}
