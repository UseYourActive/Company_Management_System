import com.tinqin.cms.entities.Attendance;
import com.tinqin.cms.exceptions.AttendanceNotFoundException;
import com.tinqin.cms.operations.EditAttendanceOperation;
import com.tinqin.cms.processors.EditAttendanceOperationProcessor;
import com.tinqin.cms.repositories.AttendanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.tinqin.cms.operations.EditAttendanceOperation.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EditAttendanceOperationProcessorTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @InjectMocks
    private EditAttendanceOperationProcessor processor;

    @Test
    public void testProcess_EditAttendance_AttendanceNotFound() {
        String attendanceId = "123e4567-e89b-12d3-a456-556642440000";
        String employeeId = "123e4567-e89b-12d3-a456-556642440001";
        String checkInTime = "2024-03-30T09:00:00";
        String checkOutTime = "2024-03-30T18:00:00";

        EditAttendanceRequest request = new EditAttendanceRequest(attendanceId, employeeId, checkInTime, checkOutTime);

        when(attendanceRepository.findById(UUID.fromString(attendanceId))).thenReturn(Optional.empty());

        assertThrows(AttendanceNotFoundException.class, () -> processor.process(request));
    }
}
