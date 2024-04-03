import com.tinqin.cms.entities.Attendance;
import com.tinqin.cms.operations.FindAllAttendancesOperation;
import com.tinqin.cms.processors.FindAllAttendancesOperationProcessor;
import com.tinqin.cms.repositories.AttendanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.tinqin.cms.operations.FindAllAttendancesOperation.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindAllAttendancesOperationProcessorTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @InjectMocks
    private FindAllAttendancesOperationProcessor processor;

}
