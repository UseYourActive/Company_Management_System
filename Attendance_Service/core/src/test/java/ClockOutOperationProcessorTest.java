import com.tinqin.cms.entities.Attendance;
import com.tinqin.cms.exceptions.AttendanceNotFoundException;
import com.tinqin.cms.exceptions.InvalidCheckoutTimeException;
import com.tinqin.cms.operations.ClockOutOperation;
import com.tinqin.cms.processors.ClockOutOperationProcessor;
import com.tinqin.cms.repositories.AttendanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.tinqin.cms.operations.ClockOutOperation.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClockOutOperationProcessorTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @InjectMocks
    private ClockOutOperationProcessor processor;

    @Test
    public void testProcess_ClockOut_AttendanceNotFound() {
        String validEmployeeId = "c7d4a220-7d7e-11ec-8d3d-0242ac130003";
        ClockOutRequest request = new ClockOutRequest(validEmployeeId);
        when(attendanceRepository.findLastAttendanceByEmployeeId(UUID.fromString(validEmployeeId)))
                .thenReturn(Optional.empty());

        assertThrows(AttendanceNotFoundException.class, () -> processor.process(request));
    }

    @Test
    public void testProcess_ClockOut_ExceptionHandling() {
        String validEmployeeId = "c7d4a220-7d7e-11ec-8d3d-0242ac130003";
        ClockOutRequest request = new ClockOutRequest(validEmployeeId);

        when(attendanceRepository.findLastAttendanceByEmployeeId(UUID.fromString(validEmployeeId)))
                .thenThrow(new RuntimeException("Database connection failed"));

        assertThrows(RuntimeException.class, () -> processor.process(request));
    }
}
