import com.tinqin.cms.entities.Training;
import com.tinqin.cms.operations.FindAllTrainingsOperation;
import com.tinqin.cms.processors.FindAllTrainingsOperationProcessor;
import com.tinqin.cms.repositories.TrainingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FindAllTrainingsOperationProcessorTest {

    @Mock
    private TrainingRepository trainingRepository;

    @InjectMocks
    private FindAllTrainingsOperationProcessor processor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

}
