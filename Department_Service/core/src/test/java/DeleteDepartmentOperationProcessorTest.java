import com.tinqin.cms.entities.Department;
import com.tinqin.cms.exceptions.DepartmentNotFoundException;
import com.tinqin.cms.operations.DeleteDepartmentOperation;
import com.tinqin.cms.processors.DeleteDepartmentOperationProcessor;
import com.tinqin.cms.repositories.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.tinqin.cms.operations.DeleteDepartmentOperation.*;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteDepartmentOperationProcessorTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DeleteDepartmentOperationProcessor processor;

    @Test
    public void testProcess_DeleteDepartment_Success() {
        String departmentId = "12345678-abcd-90ab-cdef-1234567890ab";
        DeleteDepartmentRequest request = DeleteDepartmentRequest.builder()
                .id(departmentId)
                .build();

        Department department = new Department();
        when(departmentRepository.findById(UUID.fromString(departmentId))).thenReturn(Optional.of(department));

        doNothing().when(departmentRepository).delete(department);

        DeleteDepartmentResponse response = processor.process(request);

        assertNotNull(response);
        assertTrue(response.getSuccessfullyDeletedDepartment());
    }

    @Test
    public void testProcess_DeleteDepartment_DepartmentNotFound() {
        String departmentId = "12345678-abcd-90ab-cdef-1234567890ab";
        DeleteDepartmentRequest request = DeleteDepartmentRequest.builder()
                .id(departmentId)
                .build();

        when(departmentRepository.findById(UUID.fromString(departmentId))).thenReturn(Optional.empty());

        assertThrows(DepartmentNotFoundException.class, () -> processor.process(request));
    }

    @Test
    public void testProcess_DeleteDepartment_NullId() {
        DeleteDepartmentRequest request = DeleteDepartmentRequest.builder()
                .id(null)
                .build();

        assertThrows(NullPointerException.class, () -> processor.process(request));
    }

    @Test
    public void testProcess_DeleteDepartment_EmptyId() {
        DeleteDepartmentRequest request = DeleteDepartmentRequest.builder()
                .id("")
                .build();

        assertThrows(IllegalArgumentException.class, () -> processor.process(request));
    }

    @Test
    public void testProcess_DeleteDepartment_ExceptionDuringDeletion() {
        String departmentId = "12345678-abcd-90ab-cdef-1234567890ab";
        DeleteDepartmentRequest request = DeleteDepartmentRequest.builder()
                .id(departmentId)
                .build();

        Department department = new Department();
        when(departmentRepository.findById(UUID.fromString(departmentId))).thenReturn(Optional.of(department));

        RuntimeException deletionException = new RuntimeException("Deletion failed");
        doThrow(deletionException).when(departmentRepository).delete(department);

        assertThrows(RuntimeException.class, () -> processor.process(request));
    }
}
