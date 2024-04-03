import com.tinqin.cms.entities.Attendance;
import com.tinqin.cms.exceptions.InvalidCheckInTimeException;
import com.tinqin.cms.operations.CreateNewAttendanceOperation.CreateNewAttendanceRequest;
import com.tinqin.cms.operations.CreateNewAttendanceOperation.CreateNewAttendanceResponse;
import com.tinqin.cms.processors.CreateNewAttendanceOperationProcessor;
import com.tinqin.cms.repositories.AttendanceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateNewAttendanceOperationProcessorTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @InjectMocks
    private CreateNewAttendanceOperationProcessor processor;

    @Test
    void testProcess_Successful() {
        CreateNewAttendanceRequest request = new CreateNewAttendanceRequest(
                "123e4567-e89b-12d3-a456-556642440000",
                "2024-03-09T09:00:00",
                "2024-03-09T17:00:00"
        );

        Attendance attendance = new Attendance();
        attendance.setId(UUID.randomUUID());
        attendance.setCheckInTime(LocalDateTime.parse(request.getCheckInTime()));
        attendance.setCheckOutTime(LocalDateTime.parse(request.getCheckOutTime()));
        attendance.setEmployeeId(UUID.fromString(request.getEmployeeId()));

        when(attendanceRepository.save(any(Attendance.class))).thenReturn(attendance);

        CreateNewAttendanceResponse response = processor.process(request);

        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals(request.getEmployeeId(), response.getEmployeeId());
        assertEquals(request.getCheckInTime(), response.getCheckInTime());
        assertEquals(request.getCheckOutTime(), response.getCheckOutTime());
    }

    @Test
    public void testProcess_CheckOutBeforeCheckIn_ThrowsException() {
        LocalDateTime checkInTime = LocalDateTime.of(2024, 3, 9, 9, 0);
        LocalDateTime checkOutTime = LocalDateTime.of(2024, 3, 9, 8, 0);
        String employeeId = "123e4567-e89b-12d3-a456-556642440000";

        CreateNewAttendanceRequest request = new CreateNewAttendanceRequest(
                employeeId,
                checkInTime.toString(),
                checkOutTime.toString()
        );

        Assertions.assertThrows(InvalidCheckInTimeException.class, () -> {
            processor.process(request);
        });
    }
}
