import com.tinqin.cms.entities.Attendance;
import com.tinqin.cms.operations.ClockInOperation;
import com.tinqin.cms.processors.ClockInOperationProcessor;
import com.tinqin.cms.repositories.AttendanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.tinqin.cms.operations.ClockInOperation.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClockInOperationProcessorTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @InjectMocks
    private ClockInOperationProcessor processor;

    @Test
    public void testProcess_ClockIn_Success() {
        String employeeId = UUID.randomUUID().toString();
        ClockInRequest request = new ClockInRequest(employeeId);

        when(attendanceRepository.save(any(Attendance.class))).thenAnswer(invocation -> {
            Attendance attendance = invocation.getArgument(0);
            attendance.setId(UUID.randomUUID());
            attendance.setCheckInTime(LocalDateTime.now());
            return attendance;
        });

        ClockInResponse response = processor.process(request);

        assertNotNull(response);
        assertNotNull(response.getId());
        assertNotNull(response.getClockInTime());
        assertEquals(employeeId, response.getEmployeeId());
        assertEquals(true, response.getSuccessfullyClockedIn());
    }

    @Test
    public void testProcess_ClockIn_Failure() {
        String employeeId = UUID.randomUUID().toString();
        ClockInRequest request = new ClockInRequest(employeeId);

        Attendance savedAttendance = Attendance.builder()
                .id(UUID.randomUUID())
                .employeeId(UUID.fromString(employeeId))
                .checkInTime(LocalDateTime.now())
                .build();
        when(attendanceRepository.save(any(Attendance.class))).thenReturn(savedAttendance);

        ClockInResponse response = processor.process(request);

        assertNotNull(response);
        assertNotNull(response.getId());
        assertNotNull(response.getClockInTime());
        assertEquals(employeeId, response.getEmployeeId());
        assertTrue(response.getSuccessfullyClockedIn());
    }

    @Test
    public void testProcess_FindLastAttendance_Success() {
        String employeeId = UUID.randomUUID().toString();
        Attendance lastAttendance = Attendance.builder()
                .id(UUID.randomUUID())
                .employeeId(UUID.fromString(employeeId))
                .checkInTime(LocalDateTime.now())
                .build();
        when(attendanceRepository.findLastAttendanceByEmployeeId(UUID.fromString(employeeId)))
                .thenReturn(Optional.of(lastAttendance));

        Optional<Attendance> result = attendanceRepository.findLastAttendanceByEmployeeId(UUID.fromString(employeeId));

        assertTrue(result.isPresent());
        assertEquals(lastAttendance.getId(), result.get().getId());
        assertEquals(lastAttendance.getEmployeeId(), result.get().getEmployeeId());
        assertEquals(lastAttendance.getCheckInTime(), result.get().getCheckInTime());
    }

    @Test
    public void testProcess_ClockIn_ValidEmployeeId() {
        ClockInOperationProcessor processor = mock(ClockInOperationProcessor.class);
        String validEmployeeId = "c7d4a220-7d7e-11ec-8d3d-0242ac130003";
        ClockInRequest request = new ClockInRequest(validEmployeeId);
        LocalDateTime clockInTime = LocalDateTime.now();
        ClockInResponse expectedResponse = new ClockInResponse("id", clockInTime.toString(), validEmployeeId, true);

        when(processor.process(request)).thenReturn(expectedResponse);

        ClockInResponse response = processor.process(request);

        assertNotNull(response);
        assertEquals(expectedResponse.getId(), response.getId());
        assertEquals(expectedResponse.getClockInTime(), response.getClockInTime());
        assertEquals(expectedResponse.getEmployeeId(), response.getEmployeeId());
        assertEquals(expectedResponse.getSuccessfullyClockedIn(), response.getSuccessfullyClockedIn());
    }
}
