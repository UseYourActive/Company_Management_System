import com.tinqin.cms.entities.Leave;
import com.tinqin.cms.enums.LeaveStatus;
import com.tinqin.cms.exceptions.LeaveNotFoundException;
import com.tinqin.cms.operations.RejectLeaveOperation;
import com.tinqin.cms.processors.RejectLeaveOperationProcessor;
import com.tinqin.cms.repositories.LeaveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static com.tinqin.cms.operations.RejectLeaveOperation.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class RejectLeaveOperationProcessorTest {

    @Mock
    private LeaveRepository leaveRepository;

    @InjectMocks
    private RejectLeaveOperationProcessor processor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcess_LeaveExists_RejectionSuccessful() {
        RejectLeaveRequest request =
                RejectLeaveRequest.builder()
                        .id(UUID.randomUUID().toString())
                        .build();

        Leave leave = new Leave();
        leave.setStatus(LeaveStatus.PENDING);
        when(leaveRepository.findById(any(UUID.class))).thenReturn(Optional.of(leave));

        RejectLeaveResponse response = processor.process(request);

        assertTrue(response.getIsSuccessfullyRejected());
        assertEquals(LeaveStatus.REJECTED, leave.getStatus());
    }

    @Test
    public void testProcess_LeaveDoesNotExist_ThrowsLeaveNotFoundException() {
        RejectLeaveRequest request =
                RejectLeaveRequest.builder()
                        .id(UUID.randomUUID().toString())
                        .build();

        when(leaveRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(LeaveNotFoundException.class, () -> processor.process(request));
    }

}
