import com.tinqin.cms.converters.EditDepartmentEmailConverter;
import com.tinqin.cms.entities.Department;
import com.tinqin.cms.exceptions.DepartmentNotFoundException;
import com.tinqin.cms.operations.EditDepartmentEmailOperation;
import com.tinqin.cms.processors.EditDepartmentEmailOperationProcessor;
import com.tinqin.cms.repositories.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EditDepartmentEmailOperationProcessorTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private EditDepartmentEmailConverter converter;

    @InjectMocks
    private EditDepartmentEmailOperationProcessor processor;

    @Test
    public void testProcess_EditDepartmentEmail_Success() {
        String id = UUID.randomUUID().toString();
        String newEmail = "new_email@example.com";
        EditDepartmentEmailOperation.EditDepartmentEmailRequest request = EditDepartmentEmailOperation.EditDepartmentEmailRequest.builder()
                .id(id)
                .email(newEmail)
                .build();

        Department department = new Department();
        department.setId(UUID.fromString(id));
        department.setEmail(newEmail);

        when(departmentRepository.findById(any(UUID.class))).thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        EditDepartmentEmailOperation.EditDepartmentEmailResponse response = EditDepartmentEmailOperation.EditDepartmentEmailResponse.builder()
                .id(id)
                .email(newEmail)
                .build();
        when(converter.convert(any(Department.class))).thenReturn(response);

        EditDepartmentEmailOperation.EditDepartmentEmailResponse result = processor.process(request);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(newEmail, result.getEmail());
        verify(departmentRepository, times(1)).findById(any(UUID.class));
        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    public void testProcess_EditDepartmentEmail_DepartmentNotFound() {
        String id = UUID.randomUUID().toString();
        String newEmail = "new_email@example.com";
        EditDepartmentEmailOperation.EditDepartmentEmailRequest request = EditDepartmentEmailOperation.EditDepartmentEmailRequest.builder()
                .id(id)
                .email(newEmail)
                .build();

        when(departmentRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(DepartmentNotFoundException.class, () -> processor.process(request));

        verify(departmentRepository, times(1)).findById(any(UUID.class));
        verify(departmentRepository, never()).save(any(Department.class));
    }

    @Test
    public void testProcess_EditDepartmentEmail_NullRequest() {
        assertThrows(NullPointerException.class, () -> processor.process(null));

        verify(departmentRepository, never()).findById(any(UUID.class));
        verify(departmentRepository, never()).save(any(Department.class));
    }
}
