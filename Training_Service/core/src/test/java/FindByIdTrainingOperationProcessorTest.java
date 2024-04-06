import com.tinqin.cms.entities.Training;
import com.tinqin.cms.exceptions.TrainingNotFoundException;
import com.tinqin.cms.operations.FindByIdTrainingOperation;
import com.tinqin.cms.processors.FindByIdTrainingOperationProcessor;
import com.tinqin.cms.repositories.TrainingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FindByIdTrainingOperationProcessorTest {

    @Mock
    private TrainingRepository trainingRepository;

    @InjectMocks
    private FindByIdTrainingOperationProcessor processor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcess_TrainingFound() {
        UUID uuid = UUID.randomUUID();
        Training training = new Training(uuid, "Title", "Description", LocalDate.now(), LocalDate.now().plusDays(7));

        when(trainingRepository.findById(uuid)).thenReturn(Optional.of(training));

        FindByIdTrainingOperation.FindByIdTrainingRequest request = FindByIdTrainingOperation.FindByIdTrainingRequest.builder()
                .id(uuid.toString())
                .build();

        FindByIdTrainingOperation.FindByIdTrainingResponse response = processor.process(request);

        assertNotNull(response);
        assertEquals(uuid.toString(), response.getId());
        assertEquals("Title", response.getTitle());
        assertEquals("Description", response.getDescription());
        assertEquals(LocalDate.now().toString(), response.getStartDate());
        assertEquals(LocalDate.now().plusDays(7).toString(), response.getEndDate());

        verify(trainingRepository, times(1)).findById(uuid);
    }

    @Test
    void testProcess_TrainingNotFound() {
        UUID uuid = UUID.randomUUID();

        when(trainingRepository.findById(uuid)).thenReturn(Optional.empty());

        FindByIdTrainingOperation.FindByIdTrainingRequest request = FindByIdTrainingOperation.FindByIdTrainingRequest.builder()
                .id(uuid.toString())
                .build();

        assertThrows(TrainingNotFoundException.class, () -> processor.process(request));

        verify(trainingRepository, times(1)).findById(uuid);
    }
}
