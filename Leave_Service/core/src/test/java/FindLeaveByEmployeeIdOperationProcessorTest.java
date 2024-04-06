import com.tinqin.cms.converters.FindByEmployeeIdResponseDTOConverter;
import com.tinqin.cms.entities.Leave;
import com.tinqin.cms.operations.FindByEmployeeIdOperation;
import com.tinqin.cms.processors.FindLeaveByEmployeeIdOperationProcessor;
import com.tinqin.cms.repositories.LeaveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.tinqin.cms.operations.FindByEmployeeIdOperation.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FindLeaveByEmployeeIdOperationProcessorTest {

    @Mock
    private LeaveRepository leaveRepository;

    @Mock
    private FindByEmployeeIdResponseDTOConverter converter;

    @InjectMocks
    private FindLeaveByEmployeeIdOperationProcessor processor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcess_ValidRequest_ReturnsResponse() {
        FindByEmployeeIdRequest request = FindByEmployeeIdRequest.builder()
                .employeeId(UUID.randomUUID().toString())
                .build();

        List<Leave> leaves = Collections.singletonList(new Leave());
        List<FindByEmployeeIdResponseDTO> dtos = List.of(new FindByEmployeeIdResponseDTO());
        when(leaveRepository.findByEmployeeId(UUID.fromString(request.getEmployeeId()))).thenReturn(leaves);
        when(converter.convert(leaves.get(0))).thenReturn(dtos.get(0));

        FindByEmployeeIdResponse response = processor.process(request);

        assertEquals(dtos, response.getFindByEmployeeIdResponseDTOS());
    }

    @Test
    public void testProcess_NoLeavesFound_ReturnsEmptyResponse() {
        FindByEmployeeIdRequest request = FindByEmployeeIdRequest.builder()
                .employeeId(UUID.randomUUID().toString())
                .build();

        List<Leave> leaves = Collections.emptyList();
        when(leaveRepository.findByEmployeeId(UUID.fromString(request.getEmployeeId()))).thenReturn(leaves);

        FindByEmployeeIdResponse response = processor.process(request);

        assertEquals(Collections.emptyList(), response.getFindByEmployeeIdResponseDTOS());
    }

}
