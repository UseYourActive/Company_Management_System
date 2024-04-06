import com.tinqin.cms.converters.EditDepartmentPhoneNumberConverter;
import com.tinqin.cms.entities.Department;
import com.tinqin.cms.exceptions.DepartmentNotFoundException;
import com.tinqin.cms.operations.EditDepartmentPhoneNumberOperation;
import com.tinqin.cms.processors.EditDepartmentPhoneNumberOperationProcessor;
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
public class EditDepartmentPhoneNumberOperationProcessorTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private EditDepartmentPhoneNumberConverter converter;

    @InjectMocks
    private EditDepartmentPhoneNumberOperationProcessor processor;

    @Test
    public void testProcess_EditDepartmentPhoneNumber_Success() {
        String id = UUID.randomUUID().toString();
        String newPhoneNumber = "+1234567890";
        EditDepartmentPhoneNumberOperation.EditDepartmentPhoneNumberRequest request = EditDepartmentPhoneNumberOperation.EditDepartmentPhoneNumberRequest.builder()
                .id(id)
                .phoneNumber(newPhoneNumber)
                .build();

        Department department = new Department();
        department.setId(UUID.fromString(id));
        department.setPhoneNumber(newPhoneNumber);

        when(departmentRepository.findById(any(UUID.class))).thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        EditDepartmentPhoneNumberOperation.EditDepartmentPhoneNumberResponse response = EditDepartmentPhoneNumberOperation.EditDepartmentPhoneNumberResponse.builder()
                .id(id)
                .phoneNumber(newPhoneNumber)
                .build();
        when(converter.convert(any(Department.class))).thenReturn(response);

        EditDepartmentPhoneNumberOperation.EditDepartmentPhoneNumberResponse result = processor.process(request);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(newPhoneNumber, result.getPhoneNumber());
        verify(departmentRepository, times(1)).findById(any(UUID.class));
        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    public void testProcess_EditDepartmentPhoneNumber_DepartmentNotFound() {
        String id = UUID.randomUUID().toString();
        String newPhoneNumber = "+1234567890";
        EditDepartmentPhoneNumberOperation.EditDepartmentPhoneNumberRequest request = EditDepartmentPhoneNumberOperation.EditDepartmentPhoneNumberRequest.builder()
                .id(id)
                .phoneNumber(newPhoneNumber)
                .build();

        when(departmentRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(DepartmentNotFoundException.class, () -> processor.process(request));

        verify(departmentRepository, times(1)).findById(any(UUID.class));
        verify(departmentRepository, never()).save(any(Department.class));
    }

    @Test
    public void testProcess_EditDepartmentPhoneNumber_NullRequest() {
        assertThrows(NullPointerException.class, () -> processor.process(null));

        verify(departmentRepository, never()).findById(any(UUID.class));
        verify(departmentRepository, never()).save(any(Department.class));
    }
}
