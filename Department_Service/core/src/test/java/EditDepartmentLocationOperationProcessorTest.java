import com.tinqin.cms.converters.EditDepartmentLocationConverter;
import com.tinqin.cms.entities.Department;
import com.tinqin.cms.exceptions.DepartmentNotFoundException;
import com.tinqin.cms.operations.EditDepartmentLocationOperation;
import com.tinqin.cms.processors.EditDepartmentLocationOperationProcessor;
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
public class EditDepartmentLocationOperationProcessorTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private EditDepartmentLocationConverter converter;

    @InjectMocks
    private EditDepartmentLocationOperationProcessor processor;

    @Test
    public void testProcess_EditDepartmentLocation_Success() {
        String id = UUID.randomUUID().toString();
        String newLocation = "New Location";
        EditDepartmentLocationOperation.EditDepartmentLocationRequest request = EditDepartmentLocationOperation.EditDepartmentLocationRequest.builder()
                .id(id)
                .location(newLocation)
                .build();

        Department department = new Department();
        department.setId(UUID.fromString(id));
        department.setLocation(newLocation);

        when(departmentRepository.findById(any(UUID.class))).thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        EditDepartmentLocationOperation.EditDepartmentLocationResponse response = EditDepartmentLocationOperation.EditDepartmentLocationResponse.builder()
                .id(id)
                .location(newLocation)
                .build();
        when(converter.convert(any(Department.class))).thenReturn(response);

        EditDepartmentLocationOperation.EditDepartmentLocationResponse result = processor.process(request);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(newLocation, result.getLocation());
        verify(departmentRepository, times(1)).findById(any(UUID.class));
        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    public void testProcess_EditDepartmentLocation_DepartmentNotFound() {
        String id = UUID.randomUUID().toString();
        String newLocation = "New Location";
        EditDepartmentLocationOperation.EditDepartmentLocationRequest request = EditDepartmentLocationOperation.EditDepartmentLocationRequest.builder()
                .id(id)
                .location(newLocation)
                .build();

        when(departmentRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(DepartmentNotFoundException.class, () -> processor.process(request));

        verify(departmentRepository, times(1)).findById(any(UUID.class));
        verify(departmentRepository, never()).save(any(Department.class));
    }

    @Test
    public void testProcess_EditDepartmentLocation_NullRequest() {
        assertThrows(NullPointerException.class, () -> processor.process(null));

        verify(departmentRepository, never()).findById(any(UUID.class));
        verify(departmentRepository, never()).save(any(Department.class));
    }
}
