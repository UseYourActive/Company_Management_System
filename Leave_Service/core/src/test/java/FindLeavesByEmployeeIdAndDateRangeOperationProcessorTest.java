import com.tinqin.cms.converters.FindLeavesByEmployeeIdAndDateRangeResponseDTOConverter;
import com.tinqin.cms.entities.Leave;
import com.tinqin.cms.operations.FindLeavesByEmployeeIdAndDateRangeOperation;
import com.tinqin.cms.processors.FindLeavesByEmployeeIdAndDateRangeOperationProcessor;
import com.tinqin.cms.repositories.LeaveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.tinqin.cms.operations.FindLeavesByEmployeeIdAndDateRangeOperation.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FindLeavesByEmployeeIdAndDateRangeOperationProcessorTest {

    @Mock
    private LeaveRepository leaveRepository;

    @Mock
    private FindLeavesByEmployeeIdAndDateRangeResponseDTOConverter converter;

    @InjectMocks
    private FindLeavesByEmployeeIdAndDateRangeOperationProcessor processor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcess_ValidRequest_ReturnsResponse() {
        FindLeavesByEmployeeIdAndDateRangeRequest request =
                FindLeavesByEmployeeIdAndDateRangeRequest.builder()
                        .employeeId(UUID.randomUUID().toString())
                        .startDate(LocalDate.now().minusDays(2).toString())
                        .endDate(LocalDate.now().plusDays(2).toString())
                        .build();

        List<Leave> leaves = Collections.singletonList(new Leave());
        List<FindLeavesByEmployeeIdAndDateRangeResponseDTO> dtos = Collections.singletonList(new FindLeavesByEmployeeIdAndDateRangeResponseDTO());
        when(leaveRepository.findLeavesByEmployeeIdAndDateRange(UUID.fromString(request.getEmployeeId()), LocalDate.parse(request.getStartDate()), LocalDate.parse(request.getEndDate()))).thenReturn(leaves);
        when(converter.convert(leaves.get(0))).thenReturn(dtos.get(0));

        FindLeavesByEmployeeIdAndDateRangeResponse response = processor.process(request);

        assertEquals(dtos, response.getFindLeavesByEmployeeIdAndDateRangeResponseDTOS());
    }

    @Test
    public void testProcess_NoLeavesFound_ReturnsEmptyResponse() {
        FindLeavesByEmployeeIdAndDateRangeRequest request =
                FindLeavesByEmployeeIdAndDateRangeRequest.builder()
                        .employeeId(UUID.randomUUID().toString())
                        .startDate(LocalDate.now().plusDays(2).toString())
                        .endDate(LocalDate.now().plusDays(3).toString())
                        .build();

        List<Leave> leaves = Collections.emptyList();
        when(leaveRepository.findLeavesByEmployeeIdAndDateRange(UUID.fromString(request.getEmployeeId()), LocalDate.parse(request.getStartDate()), LocalDate.parse(request.getEndDate()))).thenReturn(leaves);

        FindLeavesByEmployeeIdAndDateRangeResponse response = processor.process(request);

        assertEquals(Collections.emptyList(), response.getFindLeavesByEmployeeIdAndDateRangeResponseDTOS());
    }

}
