import com.tinqin.cms.converters.EditDepartmentBudgetConverter;
import com.tinqin.cms.entities.Department;
import com.tinqin.cms.exceptions.DepartmentNotFoundException;
import com.tinqin.cms.operations.EditDepartmentBudgetOperation;
import com.tinqin.cms.processors.EditDepartmentBudgetOperationProcessor;
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
public class EditDepartmentBudgetOperationProcessorTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private EditDepartmentBudgetConverter converter;

    @InjectMocks
    private EditDepartmentBudgetOperationProcessor processor;

    @Test
    public void testProcess_EditDepartmentBudget_Success() {
        String id = UUID.randomUUID().toString();
        String newBudget = "15000.00";
        EditDepartmentBudgetOperation.EditDepartmentBudgetRequest request = EditDepartmentBudgetOperation.EditDepartmentBudgetRequest.builder()
                .id(id)
                .budget(newBudget)
                .build();

        Department department = new Department();
        department.setId(UUID.fromString(id));
        department.setBudget(new BigDecimal(newBudget));

        when(departmentRepository.findById(any(UUID.class))).thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        EditDepartmentBudgetOperation.EditDepartmentBudgetResponse response = EditDepartmentBudgetOperation.EditDepartmentBudgetResponse.builder()
                .id(id)
                .budget(newBudget)
                .build();
        when(converter.convert(any(Department.class))).thenReturn(response);

        EditDepartmentBudgetOperation.EditDepartmentBudgetResponse result = processor.process(request);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(newBudget, result.getBudget());
        verify(departmentRepository, times(1)).findById(any(UUID.class));
        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    public void testProcess_EditDepartmentBudget_DepartmentNotFound() {
        String id = UUID.randomUUID().toString();
        String newBudget = "15000.00";
        EditDepartmentBudgetOperation.EditDepartmentBudgetRequest request = EditDepartmentBudgetOperation.EditDepartmentBudgetRequest.builder()
                .id(id)
                .budget(newBudget)
                .build();

        when(departmentRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(DepartmentNotFoundException.class, () -> processor.process(request));

        verify(departmentRepository, times(1)).findById(any(UUID.class));
        verify(departmentRepository, never()).save(any(Department.class));
    }

    @Test
    public void testProcess_EditDepartmentBudget_NullRequest() {
        assertThrows(NullPointerException.class, () -> processor.process(null));

        verify(departmentRepository, never()).findById(any(UUID.class));
        verify(departmentRepository, never()).save(any(Department.class));
    }
}
