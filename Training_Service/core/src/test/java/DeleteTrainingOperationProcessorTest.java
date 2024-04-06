import com.tinqin.cms.entities.Training;
import com.tinqin.cms.operations.DeleteTrainingOperation;
import com.tinqin.cms.processors.DeleteTrainingOperationProcessor;
import com.tinqin.cms.repositories.TrainingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static com.tinqin.cms.operations.DeleteTrainingOperation.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DeleteTrainingOperationProcessorTest {

    @Mock
    private TrainingRepository trainingRepository;

    @InjectMocks
    private DeleteTrainingOperationProcessor processor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcess_ValidId() {
        DeleteTrainingRequest request =
                DeleteTrainingRequest.builder()
                        .id(UUID.randomUUID().toString())
                        .build();

        Training training = Training.builder().build();
        when(trainingRepository.findById(any(UUID.class))).thenReturn(Optional.of(training));

        DeleteTrainingResponse response = processor.process(request);
        assertTrue(response.getIsSuccessfullyDeleted(), "Training should be successfully deleted");
    }

    @Test
    public void testProcess_InvalidId() {
        DeleteTrainingRequest request =
                DeleteTrainingRequest.builder()
                        .id("invalid_id")
                        .build();

        assertThrows(
                IllegalArgumentException.class,
                () -> processor.process(request),
                "Should throw IllegalArgumentException for invalid ID"
        );
    }


    @Test
    public void testProcess_NonexistentId() {
        DeleteTrainingRequest request =
                DeleteTrainingRequest.builder()
                        .id(UUID.randomUUID().toString())
                        .build();

        when(trainingRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(
                com.tinqin.cms.exceptions.TrainingNotFoundException.class,
                () -> processor.process(request),
                "Should throw TrainingNotFoundException for nonexistent ID"
        );
    }
}
