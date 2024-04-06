import com.tinqin.cms.converters.FindByIdDepartmentMapper;
import com.tinqin.cms.entities.Department;
import com.tinqin.cms.exceptions.DepartmentNotFoundException;
import com.tinqin.cms.operations.FindByIdDepartmentOperation;
import com.tinqin.cms.processors.FindByIdDepartmentOperationProcessor;
import com.tinqin.cms.repositories.DepartmentRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static com.tinqin.cms.operations.FindByIdDepartmentOperation.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class FindByIdDepartmentOperationProcessorTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private FindByIdDepartmentMapper converter;

    @InjectMocks
    private FindByIdDepartmentOperationProcessor processor;

    @Test
    public void testProcess_FindByIdDepartment_Success() {
        String departmentId = "123e4567-e89b-12d3-a456-426614174000";
        FindByIdDepartmentRequest request = FindByIdDepartmentRequest.builder()
                .id(departmentId)
                .build();

        Department department = new Department();
        department.setId(UUID.fromString(departmentId));
        department.setName("Engineering");
        department.setDescription("Responsible for software development");
        department.setBudget(new BigDecimal("10000.00"));
        department.setLocation("New York");
        department.setPhoneNumber("123-456-7890");
        department.setEmail("engineering@example.com");

        when(departmentRepository.findById(UUID.fromString(departmentId))).thenReturn(Optional.of(department));

        FindByIdDepartmentResponse convertedResponse = FindByIdDepartmentResponse.builder()
                .id(departmentId)
                .name("Engineering")
                .description("Responsible for software development")
                .budget("10000.00")
                .location("New York")
                .phoneNumber("123-456-7890")
                .email("engineering@example.com")
                .build();
        when(converter.convert(department)).thenReturn(convertedResponse);

        FindByIdDepartmentResponse response = processor.process(request);

        assertNotNull(response);
        assertEquals(departmentId, response.getId());
        assertEquals("Engineering", response.getName());
        assertEquals("Responsible for software development", response.getDescription());
        assertEquals("10000.00", response.getBudget());
        assertEquals("New York", response.getLocation());
        assertEquals("123-456-7890", response.getPhoneNumber());
        assertEquals("engineering@example.com", response.getEmail());
    }

    @Test
    public void testProcess_FindByIdDepartment_DoesNotExist() {
        String departmentId = "123e4567-e89b-12d3-a456-426614174000";
        FindByIdDepartmentRequest request = FindByIdDepartmentRequest.builder()
                .id(departmentId)
                .build();

        when(departmentRepository.findById(UUID.fromString(departmentId))).thenReturn(Optional.empty());

        assertThrows(DepartmentNotFoundException.class, () -> processor.process(request));
    }

    @Test
    public void testProcess_FindByIdDepartment_DepartmentNotFound() {
        String departmentId = "123e4567-e89b-12d3-a456-426614174000";
        FindByIdDepartmentRequest request = FindByIdDepartmentRequest.builder()
                .id(departmentId)
                .build();

        when(departmentRepository.findById(UUID.fromString(departmentId))).thenReturn(Optional.empty());

        DepartmentNotFoundException exception = assertThrows(DepartmentNotFoundException.class, () -> processor.process(request));
        assertEquals("Department with given id has not been found", exception.getMessage());
    }
}

