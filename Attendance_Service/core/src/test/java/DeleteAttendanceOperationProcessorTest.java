import com.tinqin.cms.entities.Attendance;
import com.tinqin.cms.exceptions.AttendanceNotFoundException;
import com.tinqin.cms.operations.DeleteAttendanceOperation;
import com.tinqin.cms.processors.DeleteAttendanceOperationProcessor;
import com.tinqin.cms.repositories.AttendanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.tinqin.cms.operations.DeleteAttendanceOperation.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteAttendanceOperationProcessorTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @InjectMocks
    private DeleteAttendanceOperationProcessor processor;

    @Test
    public void testProcess_DeleteAttendance_Success() {
        String validAttendanceId = "123e4567-e89b-12d3-a456-556642440000";
        DeleteAttendanceRequest request = new DeleteAttendanceRequest(validAttendanceId);

        Attendance attendance = new Attendance();
        attendance.setId(UUID.fromString(validAttendanceId));

        when(attendanceRepository.findById(UUID.fromString(validAttendanceId))).thenReturn(Optional.of(attendance));

        DeleteAttendanceResponse response = processor.process(request);

        assertTrue(response.getIsSuccessfullyDeleted());
        verify(attendanceRepository, times(1)).delete(attendance);
    }

    @Test
    public void testProcess_DeleteAttendance_NullId() {
        DeleteAttendanceOperation.DeleteAttendanceRequest request = new DeleteAttendanceOperation.DeleteAttendanceRequest(null);

        assertThrows(NullPointerException.class, () -> processor.process(request));
        verify(attendanceRepository, never()).findById(any(UUID.class));
    }

    @Test
    public void testProcess_DeleteAttendance_InvalidIdFormat() {
        String invalidAttendanceId = "invalid id format";
        DeleteAttendanceOperation.DeleteAttendanceRequest request = new DeleteAttendanceOperation.DeleteAttendanceRequest(invalidAttendanceId);

        assertThrows(IllegalArgumentException.class, () -> processor.process(request));
        verify(attendanceRepository, never()).findById(any(UUID.class));
    }

    @Test
    public void testProcess_DeleteAttendance_ExceptionThrownByRepository() {
        String validAttendanceId = "123e4567-e89b-12d3-a456-556642440000";
        DeleteAttendanceOperation.DeleteAttendanceRequest request = new DeleteAttendanceOperation.DeleteAttendanceRequest(validAttendanceId);

        when(attendanceRepository.findById(UUID.fromString(validAttendanceId))).thenThrow(new RuntimeException("Database connection failed"));

        assertThrows(RuntimeException.class, () -> processor.process(request));
        verify(attendanceRepository, never()).delete(any());
    }
}
