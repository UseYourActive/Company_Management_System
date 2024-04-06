import com.tinqin.cms.operations.DeleteLeaveOperation;
import com.tinqin.cms.processors.DeleteLeaveOperationProcessor;
import com.tinqin.cms.repositories.LeaveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteLeaveOperationProcessorTest {

    @Mock
    private LeaveRepository leaveRepository;

    @InjectMocks
    private DeleteLeaveOperationProcessor processor;

    private DeleteLeaveOperation.DeleteLeaveRequest validRequest;

    @BeforeEach
    public void setup() {
        validRequest = DeleteLeaveOperation.DeleteLeaveRequest.builder()
                .id(UUID.randomUUID().toString())
                .build();
    }

    @Test
    public void testProcess_DeleteLeave_ValidRequest() {
        doNothing().when(leaveRepository).deleteById(any(UUID.class));

        DeleteLeaveOperation.DeleteLeaveResponse response = processor.process(validRequest);

        assertNotNull(response);
        assertTrue(response.getIsSuccessful());
    }

    @Test
    public void testProcess_DeleteLeave_NullRequest() {
        assertThrows(NullPointerException.class, () -> processor.process(null));
    }

    @Test
    public void testProcess_DeleteLeave_NullId() {
        validRequest.setId(null);

        assertThrows(NullPointerException.class, () -> processor.process(validRequest));
    }
}
