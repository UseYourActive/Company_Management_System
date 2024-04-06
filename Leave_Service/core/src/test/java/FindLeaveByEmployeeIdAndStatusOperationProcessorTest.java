import com.tinqin.cms.converters.FindByEmployeeIdAndStatusResponseDTOConverter;
import com.tinqin.cms.entities.Leave;
import com.tinqin.cms.enums.LeaveStatus;
import com.tinqin.cms.operations.FindByEmployeeIdAndStatusOperation;
import com.tinqin.cms.processors.FindLeaveByEmployeeIdAndStatusOperationProcessor;
import com.tinqin.cms.repositories.LeaveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FindLeaveByEmployeeIdAndStatusOperationProcessorTest {

    @Mock
    private LeaveRepository leaveRepository;

    @Mock
    private FindByEmployeeIdAndStatusResponseDTOConverter converter;

    @InjectMocks
    private FindLeaveByEmployeeIdAndStatusOperationProcessor processor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcess_ValidRequest_ReturnsResponse() {
        FindByEmployeeIdAndStatusOperation.FindByEmployeeIdAndStatusRequest request = FindByEmployeeIdAndStatusOperation.FindByEmployeeIdAndStatusRequest.builder()
                .employeeId(UUID.randomUUID().toString())
                .status(LeaveStatus.APPROVED.name())
                .build();

        List<Leave> leaves = Collections.singletonList(new Leave());
        List<FindByEmployeeIdAndStatusOperation.FindByEmployeeIdAndStatusResponseDTO> dtos = Collections.singletonList(new FindByEmployeeIdAndStatusOperation.FindByEmployeeIdAndStatusResponseDTO());
        when(leaveRepository.findByEmployeeIdAndStatus(UUID.fromString(request.getEmployeeId()), LeaveStatus.valueOf(request.getStatus()))).thenReturn(leaves);
        when(converter.convert(leaves.get(0))).thenReturn(dtos.get(0));

        FindByEmployeeIdAndStatusOperation.FindByEmployeeIdAndStatusResponse response = processor.process(request);

        assertEquals(dtos, response.getFindByEmployeeIdAndStatusResponseDTOS());
    }

    @Test
    public void testProcess_NoLeavesFound_ReturnsEmptyResponse() {
        FindByEmployeeIdAndStatusOperation.FindByEmployeeIdAndStatusRequest request = FindByEmployeeIdAndStatusOperation.FindByEmployeeIdAndStatusRequest.builder()
                .employeeId(UUID.randomUUID().toString())
                .status(LeaveStatus.APPROVED.name())
                .build();

        List<Leave> leaves = Collections.emptyList();
        when(leaveRepository.findByEmployeeIdAndStatus(UUID.fromString(request.getEmployeeId()), LeaveStatus.valueOf(request.getStatus()))).thenReturn(leaves);

        FindByEmployeeIdAndStatusOperation.FindByEmployeeIdAndStatusResponse response = processor.process(request);

        assertEquals(Collections.emptyList(), response.getFindByEmployeeIdAndStatusResponseDTOS());
    }

}
