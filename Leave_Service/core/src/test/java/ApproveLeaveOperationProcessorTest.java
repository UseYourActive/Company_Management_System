import com.tinqin.cms.entities.Leave;
import com.tinqin.cms.enums.LeaveStatus;
import com.tinqin.cms.exceptions.LeaveNotFoundException;
import com.tinqin.cms.operations.ApproveLeaveOperation;
import com.tinqin.cms.processors.ApproveLeaveOperationProcessor;
import com.tinqin.cms.repositories.LeaveRepository;
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
public class ApproveLeaveOperationProcessorTest {

    @Mock
    private LeaveRepository leaveRepository;

    @InjectMocks
    private ApproveLeaveOperationProcessor processor;

    @Test
    public void testProcess_ApproveLeave_Success() {
        String id = UUID.randomUUID().toString();
        ApproveLeaveOperation.ApproveLeaveRequest request = ApproveLeaveOperation.ApproveLeaveRequest.builder()
                .id(id)
                .build();

        Leave leave = new Leave();
        leave.setId(UUID.fromString(id));
        leave.setStatus(LeaveStatus.PENDING);

        when(leaveRepository.findById(any(UUID.class))).thenReturn(Optional.of(leave));
        when(leaveRepository.save(any(Leave.class))).thenReturn(leave);

        ApproveLeaveOperation.ApproveLeaveResponse result = processor.process(request);

        assertNotNull(result);
        assertTrue(result.getSuccessfullyApprovedLeave());
        assertEquals(LeaveStatus.APPROVED, leave.getStatus());
        verify(leaveRepository, times(1)).findById(any(UUID.class));
        verify(leaveRepository, times(1)).save(any(Leave.class));
    }

    @Test
    public void testProcess_ApproveLeave_LeaveNotFound() {
        String id = UUID.randomUUID().toString();
        ApproveLeaveOperation.ApproveLeaveRequest request = ApproveLeaveOperation.ApproveLeaveRequest.builder()
                .id(id)
                .build();

        when(leaveRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(LeaveNotFoundException.class, () -> processor.process(request));

        verify(leaveRepository, times(1)).findById(any(UUID.class));
        verify(leaveRepository, never()).save(any(Leave.class));
    }

    @Test
    public void testProcess_ApproveLeave_NullRequest() {
        assertThrows(NullPointerException.class, () -> processor.process(null));

        verify(leaveRepository, never()).findById(any(UUID.class));
        verify(leaveRepository, never()).save(any(Leave.class));
    }
}
