import com.tinqin.cms.entities.Training;
import com.tinqin.cms.operations.CreateNewTrainingOperation;
import com.tinqin.cms.processors.CreateNewTrainingOperationProcessor;
import com.tinqin.cms.repositories.TrainingRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.UUID;

import static com.tinqin.cms.operations.CreateNewTrainingOperation.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreateNewTrainingOperationProcessorTest {

    @Mock
    private TrainingRepository trainingRepository;

    @InjectMocks
    private CreateNewTrainingOperationProcessor processor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcess_TrainingCreatedSuccessfully() {
        CreateNewTrainingRequest request =
                CreateNewTrainingRequest.builder()
                        .title("Java Programming")
                        .description("Learn Java programming basics.")
                        .startDate("2024-03-10")
                        .endDate("2024-03-15")
                        .build();

        Training training = Training.builder()
                .id(UUID.randomUUID())
                .title(request.getTitle())
                .description(request.getDescription())
                .startDate(LocalDate.parse(request.getStartDate()))
                .endDate(LocalDate.parse(request.getEndDate()))
                .build();
        when(trainingRepository.save(any(Training.class))).thenReturn(training);

        CreateNewTrainingResponse response = processor.process(request);

        verify(trainingRepository).save(any(Training.class));

        assertEquals(request.getTitle(), response.getTitle());
        assertEquals(request.getDescription(), response.getDescription());
        assertEquals(request.getStartDate(), response.getStartDate());
        assertEquals(request.getEndDate(), response.getEndDate());
    }

    @Test
    public void testProcess_InvalidStartDate() {
        CreateNewTrainingRequest request =
                CreateNewTrainingRequest.builder()
                        .title("Java Programming")
                        .description("Learn Java programming basics.")
                        .startDate("invalid_date")
                        .endDate("2024-03-15")
                        .build();

        assertThrows(
                DateTimeParseException.class,
                () -> processor.process(request),
                "Should throw DateTimeParseException for invalid start date"
        );
    }

}
