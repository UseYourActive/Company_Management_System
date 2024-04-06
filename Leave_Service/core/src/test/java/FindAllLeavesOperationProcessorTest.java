import com.tinqin.cms.converters.FindAllLeavesResponseDTOConverter;
import com.tinqin.cms.entities.Leave;
import com.tinqin.cms.operations.FindAllLeavesOperation;
import com.tinqin.cms.processors.FindAllLeavesOperationProcessor;
import com.tinqin.cms.repositories.LeaveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindAllLeavesOperationProcessorTest {

    @Mock
    private LeaveRepository leaveRepository;

    @Mock
    private FindAllLeavesResponseDTOConverter converter;

    @InjectMocks
    private FindAllLeavesOperationProcessor processor;

    private FindAllLeavesOperation.FindAllLeavesRequest validRequest;

    @BeforeEach
    public void setup() {
        validRequest = FindAllLeavesOperation.FindAllLeavesRequest.builder()
                .pageNumber(0)
                .numberOfBooksPerPage(10)
                .build();
    }

    @Test
    public void testProcess_FindAllLeaves_ValidRequest() {
        Page<Leave> mockPage = TestDataUtil.createMockLeavePage();
        List<FindAllLeavesOperation.FindAllLeavesResponseDTO> mockDTOList = TestDataUtil.createMockDTOList();

        when(leaveRepository.findAll(PageRequest.of(validRequest.getPageNumber(), validRequest.getNumberOfBooksPerPage()))).thenReturn(mockPage);

        when(converter.convert(mockPage.getContent().get(0))).thenReturn(mockDTOList.get(0));

        FindAllLeavesOperation.FindAllLeavesResponse response = processor.process(validRequest);

        assertEquals(mockDTOList, response.getFindAllLeavesResponseDTOS());
    }
}
