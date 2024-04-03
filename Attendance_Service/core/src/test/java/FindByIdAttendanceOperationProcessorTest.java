import com.tinqin.cms.entities.Attendance;
import com.tinqin.cms.exceptions.AttendanceNotFoundException;
import com.tinqin.cms.operations.FindByIdAttendanceOperation;
import com.tinqin.cms.processors.FindByIdAttendanceOperationProcessor;
import com.tinqin.cms.repositories.AttendanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.tinqin.cms.operations.FindByIdAttendanceOperation.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindByIdAttendanceOperationProcessorTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @InjectMocks
    private FindByIdAttendanceOperationProcessor processor;

    @Test
    public void testProcess_FindByIdAttendance_Success() {
        String attendanceId = "123e4567-e89b-12d3-a456-556642440000";
        Attendance attendance = new Attendance();
        attendance.setId(UUID.fromString(attendanceId));
        attendance.setEmployeeId(UUID.randomUUID());
        attendance.setCheckInTime(LocalDateTime.now());
        attendance.setCheckOutTime(LocalDateTime.now().plusHours(8));

        when(attendanceRepository.findById(UUID.fromString(attendanceId)))
                .thenReturn(Optional.of(attendance));

        FindByIdAttendanceRequest request = new FindByIdAttendanceRequest(attendanceId);

        FindByIdAttendanceResponse response = processor.process(request);

        assertNotNull(response);
        assertEquals(attendanceId, response.getId());
        assertEquals(attendance.getEmployeeId().toString(), response.getEmployeeId());
        assertEquals(attendance.getCheckInTime().toString(), response.getCheckInTime());
        assertEquals(attendance.getCheckOutTime().toString(), response.getCheckOutTime());
    }

    @Test
    public void testProcess_FindByIdAttendance_NotFound() {
        String attendanceId = "123e4567-e89b-12d3-a456-556642440000";

        when(attendanceRepository.findById(UUID.fromString(attendanceId)))
                .thenReturn(Optional.empty());

        FindByIdAttendanceRequest request = new FindByIdAttendanceRequest(attendanceId);

        assertThrows(AttendanceNotFoundException.class, () -> processor.process(request));
    }

    @Test
    public void testProcess_FindByIdAttendance_NullId() {
        String attendanceId = null;

        FindByIdAttendanceRequest request = new FindByIdAttendanceRequest(attendanceId);

        assertThrows(NullPointerException.class, () -> processor.process(request));
    }
}
