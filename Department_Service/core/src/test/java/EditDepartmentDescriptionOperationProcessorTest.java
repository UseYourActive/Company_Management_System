import com.tinqin.cms.converters.EditDepartmentDescriptionConverter;
import com.tinqin.cms.entities.Department;
import com.tinqin.cms.exceptions.DepartmentNotFoundException;
import com.tinqin.cms.operations.EditDepartmentDescriptionOperation;
import com.tinqin.cms.processors.EditDepartmentDescriptionOperationProcessor;
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
public class EditDepartmentDescriptionOperationProcessorTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private EditDepartmentDescriptionConverter converter;

    @InjectMocks
    private EditDepartmentDescriptionOperationProcessor processor;

    @Test
    public void testProcess_EditDepartmentDescription_Success() {
        String id = UUID.randomUUID().toString();
        String newDescription = "New Engineering Department Description";
        EditDepartmentDescriptionOperation.EditDepartmentDescriptionRequest request = EditDepartmentDescriptionOperation.EditDepartmentDescriptionRequest.builder()
                .id(id)
                .description(newDescription)
                .build();

        Department department = new Department();
        department.setId(UUID.fromString(id));
        department.setDescription(newDescription);

        when(departmentRepository.findById(any(UUID.class))).thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        EditDepartmentDescriptionOperation.EditDepartmentDescriptionResponse response = EditDepartmentDescriptionOperation.EditDepartmentDescriptionResponse.builder()
                .id(id)
                .description(newDescription)
                .build();
        when(converter.convert(any(Department.class))).thenReturn(response);

        EditDepartmentDescriptionOperation.EditDepartmentDescriptionResponse result = processor.process(request);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(newDescription, result.getDescription());
        verify(departmentRepository, times(1)).findById(any(UUID.class));
        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    public void testProcess_EditDepartmentDescription_DepartmentNotFound() {
        String id = UUID.randomUUID().toString();
        String newDescription = "New Engineering Department Description";
        EditDepartmentDescriptionOperation.EditDepartmentDescriptionRequest request = EditDepartmentDescriptionOperation.EditDepartmentDescriptionRequest.builder()
                .id(id)
                .description(newDescription)
                .build();

        when(departmentRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(DepartmentNotFoundException.class, () -> processor.process(request));

        verify(departmentRepository, times(1)).findById(any(UUID.class));
        verify(departmentRepository, never()).save(any(Department.class));
    }

    @Test
    public void testProcess_EditDepartmentDescription_NullRequest() {
        assertThrows(NullPointerException.class, () -> processor.process(null));

        verify(departmentRepository, never()).findById(any(UUID.class));
        verify(departmentRepository, never()).save(any(Department.class));
    }
}
