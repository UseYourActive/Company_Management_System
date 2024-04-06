import com.tinqin.cms.entities.Department;
import com.tinqin.cms.exceptions.DepartmentNotFoundException;
import com.tinqin.cms.operations.EditFullDepartmentOperation;
import com.tinqin.cms.processors.EditFullDepartmentOperationProcessor;
import com.tinqin.cms.repositories.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EditFullDepartmentOperationProcessorTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private EditFullDepartmentOperationProcessor processor;

    @Test
    public void testProcess_EditFullDepartment_Success() {
        String id = UUID.randomUUID().toString();
        EditFullDepartmentOperation.EditFullDepartmentRequest request = EditFullDepartmentOperation.EditFullDepartmentRequest.builder()
                .id(id)
                .name("Engineering")
                .description("Responsible for engineering tasks")
                .budget("100000.00")
                .location("Building A, Floor 3")
                .phoneNumber("+1234567890")
                .email("engineering@example.com")
                .build();

        Department department = new Department();
        department.setId(UUID.fromString(id));
        department.setName(request.getName());
        department.setDescription(request.getDescription());
        department.setBudget(new BigDecimal(request.getBudget()));
        department.setLocation(request.getLocation());
        department.setPhoneNumber(request.getPhoneNumber());
        department.setEmail(request.getEmail());

        when(departmentRepository.findById(any(UUID.class))).thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        EditFullDepartmentOperation.EditFullDepartmentResponse result = processor.process(request);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(request.getName(), result.getName());
        assertEquals(request.getDescription(), result.getDescription());
        assertEquals(request.getBudget(), result.getBudget());
        assertEquals(request.getLocation(), result.getLocation());
        assertEquals(request.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(request.getEmail(), result.getEmail());
        verify(departmentRepository, times(1)).findById(any(UUID.class));
        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    public void testProcess_EditFullDepartment_DepartmentNotFound() {
        String id = UUID.randomUUID().toString();
        EditFullDepartmentOperation.EditFullDepartmentRequest request = EditFullDepartmentOperation.EditFullDepartmentRequest.builder()
                .id(id)
                .name("Engineering")
                .description("Responsible for engineering tasks")
                .budget("100000.00")
                .location("Building A, Floor 3")
                .phoneNumber("+1234567890")
                .email("engineering@example.com")
                .build();

        when(departmentRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(DepartmentNotFoundException.class, () -> processor.process(request));

        verify(departmentRepository, times(1)).findById(any(UUID.class));
        verify(departmentRepository, never()).save(any(Department.class));
    }

    @Test
    public void testProcess_EditFullDepartment_NullRequest() {
        assertThrows(NullPointerException.class, () -> processor.process(null));

        verify(departmentRepository, never()).findById(any(UUID.class));
        verify(departmentRepository, never()).save(any(Department.class));
    }
}
