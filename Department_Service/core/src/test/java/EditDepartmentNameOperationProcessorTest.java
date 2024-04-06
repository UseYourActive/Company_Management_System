import com.tinqin.cms.converters.EditDepartmentNameConverter;
import com.tinqin.cms.entities.Department;
import com.tinqin.cms.exceptions.DepartmentNotFoundException;
import com.tinqin.cms.operations.EditDepartmentNameOperation;
import com.tinqin.cms.processors.EditDepartmentNameOperationProcessor;
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
public class EditDepartmentNameOperationProcessorTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private EditDepartmentNameConverter converter;

    @InjectMocks
    private EditDepartmentNameOperationProcessor processor;

    @Test
    public void testProcess_EditDepartmentName_Success() {
        String id = UUID.randomUUID().toString();
        String newName = "New Department Name";
        EditDepartmentNameOperation.EditDepartmentNameRequest request = EditDepartmentNameOperation.EditDepartmentNameRequest.builder()
                .id(id)
                .name(newName)
                .build();

        Department department = new Department();
        department.setId(UUID.fromString(id));
        department.setName(newName);

        when(departmentRepository.findById(any(UUID.class))).thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        EditDepartmentNameOperation.EditDepartmentNameResponse response = EditDepartmentNameOperation.EditDepartmentNameResponse.builder()
                .id(id)
                .name(newName)
                .build();
        when(converter.convert(any(Department.class))).thenReturn(response);

        EditDepartmentNameOperation.EditDepartmentNameResponse result = processor.process(request);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(newName, result.getName());
        verify(departmentRepository, times(1)).findById(any(UUID.class));
        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    public void testProcess_EditDepartmentName_DepartmentNotFound() {
        String id = UUID.randomUUID().toString();
        String newName = "New Department Name";
        EditDepartmentNameOperation.EditDepartmentNameRequest request = EditDepartmentNameOperation.EditDepartmentNameRequest.builder()
                .id(id)
                .name(newName)
                .build();

        when(departmentRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(DepartmentNotFoundException.class, () -> processor.process(request));

        verify(departmentRepository, times(1)).findById(any(UUID.class));
        verify(departmentRepository, never()).save(any(Department.class));
    }

    @Test
    public void testProcess_EditDepartmentName_NullRequest() {
        assertThrows(NullPointerException.class, () -> processor.process(null));

        verify(departmentRepository, never()).findById(any(UUID.class));
        verify(departmentRepository, never()).save(any(Department.class));
    }
}
