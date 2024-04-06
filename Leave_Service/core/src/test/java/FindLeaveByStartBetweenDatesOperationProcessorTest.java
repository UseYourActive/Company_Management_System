import com.tinqin.cms.converters.FindByStartBetweenDatesResponseDTOConverter;
import com.tinqin.cms.entities.Leave;
import com.tinqin.cms.operations.FindByStartBetweenDatesOperation;
import com.tinqin.cms.processors.FindLeaveByStartBetweenDatesOperationProcessor;
import com.tinqin.cms.repositories.LeaveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static com.tinqin.cms.operations.FindByStartBetweenDatesOperation.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FindLeaveByStartBetweenDatesOperationProcessorTest {

    @Mock
    private LeaveRepository leaveRepository;

    @Mock
    private FindByStartBetweenDatesResponseDTOConverter converter;

    @InjectMocks
    private FindLeaveByStartBetweenDatesOperationProcessor processor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcess_ValidRequest_ReturnsResponse() {
        FindByStartBetweenDatesRequest request = FindByStartBetweenDatesRequest.builder()
                .startDate(LocalDate.now().minusDays(2).toString())
                .endDate(LocalDate.now().plusDays(2).toString())
                .build();

        List<Leave> leaves = Collections.singletonList(new Leave());
        List<FindByStartBetweenDatesResponseDTO> dtos = Collections.singletonList(new FindByStartBetweenDatesResponseDTO());
        when(leaveRepository.findByStartDateBetween(LocalDate.parse(request.getStartDate()), LocalDate.parse(request.getEndDate()))).thenReturn(leaves);
        when(converter.convert(leaves.get(0))).thenReturn(dtos.get(0));

        FindByStartBetweenDatesResponse response = processor.process(request);

        assertEquals(dtos, response.getFindByStartBetweenDatesResponseDTOS());
    }

    @Test
    public void testProcess_NoLeavesFound_ReturnsEmptyResponse() {
        FindByStartBetweenDatesRequest request = FindByStartBetweenDatesRequest.builder()
                .startDate(LocalDate.now().plusDays(2).toString())
                .endDate(LocalDate.now().plusDays(3).toString())
                .build();

        List<Leave> leaves = Collections.emptyList();
        when(leaveRepository.findByStartDateBetween(LocalDate.parse(request.getStartDate()), LocalDate.parse(request.getEndDate()))).thenReturn(leaves);

        FindByStartBetweenDatesResponse response = processor.process(request);

        assertEquals(Collections.emptyList(), response.getFindByStartBetweenDatesResponseDTOS());
    }
}
