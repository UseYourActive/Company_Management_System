import com.tinqin.cms.entities.Leave;
import com.tinqin.cms.enums.LeaveStatus;
import com.tinqin.cms.enums.LeaveType;
import com.tinqin.cms.operations.CreateLeaveOperation;
import com.tinqin.cms.processors.CreateLeaveOperationProcessor;
import com.tinqin.cms.repositories.LeaveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static com.tinqin.cms.operations.CreateLeaveOperation.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateLeaveOperationProcessorTest {

    @Mock
    private LeaveRepository leaveRepository;

    @InjectMocks
    private CreateLeaveOperationProcessor processor;

    private CreateLeaveRequest validRequest;

    @BeforeEach
    public void setup() {
        validRequest = CreateLeaveRequest.builder()
                .employeeId(UUID.randomUUID().toString())
                .leaveType(LeaveType.ANNUAL.toString())
                .startDate(LocalDate.now().toString())
                .endDate(LocalDate.now().plusDays(1).toString())
                .status(LeaveStatus.PENDING.toString())
                .build();
    }

    @Test
    public void testProcess_CreateLeave_ValidRequest() {
        Leave leave = Leave.builder()
                .id(UUID.randomUUID())
                .status(LeaveStatus.PENDING)
                .employeeId(UUID.randomUUID())
                .leaveType(LeaveType.ANNUAL)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .build();

        when(leaveRepository.save(any(Leave.class))).thenReturn(leave);

        CreateLeaveRequest request = CreateLeaveRequest.builder()
                .employeeId(UUID.randomUUID().toString())
                .leaveType(LeaveType.ANNUAL.toString())
                .startDate(LocalDate.now().toString())
                .endDate(LocalDate.now().plusDays(1).toString())
                .status(LeaveStatus.PENDING.toString())
                .build();

        CreateLeaveResponse response = processor.process(request);

        assertEquals(leave.getId().toString(), response.getId());
        assertEquals(leave.getEmployeeId().toString(), response.getEmployeeId());
        assertEquals(leave.getLeaveType().toString(), response.getLeaveType());
        assertEquals(leave.getStatus().toString(), response.getStatus());
        assertEquals(leave.getStartDate().toString(), response.getStartDate());
        assertEquals(leave.getEndDate().toString(), response.getEndDate());
    }

    @Test
    public void testProcess_CreateLeave_NullRequest() {
        assertThrows(NullPointerException.class, () -> processor.process(null));
    }

    @Test
    public void testProcess_CreateLeave_NullEmployeeId() {
        validRequest.setEmployeeId(null);

        assertThrows(NullPointerException.class, () -> processor.process(validRequest));
    }

    @Test
    public void testProcess_CreateLeave_NullStartDate() {
        validRequest.setStartDate(null);

        assertThrows(NullPointerException.class, () -> processor.process(validRequest));
    }

    @Test
    public void testProcess_CreateLeave_InvalidLeaveType() {
        validRequest.setLeaveType("INVALID_LEAVE_TYPE");

        assertThrows(IllegalArgumentException.class, () -> processor.process(validRequest));
    }
}
