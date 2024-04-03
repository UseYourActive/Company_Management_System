import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import com.tinqin.cms.converters.CreateNewDepartmentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tinqin.cms.operations.CreateNewDepartmentOperation.CreateNewDepartmentRequest;
import com.tinqin.cms.operations.CreateNewDepartmentOperation.CreateNewDepartmentResponse;
import com.tinqin.cms.processors.CreateNewDepartmentOperationProcessor;
import com.tinqin.cms.repositories.DepartmentRepository;
import org.springframework.context.annotation.Import;

@ExtendWith(MockitoExtension.class)
public class CreateNewDepartmentOperationProcessorTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private CreateNewDepartmentMapper converter;

    @InjectMocks
    private CreateNewDepartmentOperationProcessor processor;

}
