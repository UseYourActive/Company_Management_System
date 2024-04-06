package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Training;
import com.tinqin.cms.exceptions.TrainingNotFoundException;
import com.tinqin.cms.operations.EditTrainingOperation;
import com.tinqin.cms.repositories.TrainingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static com.tinqin.cms.operations.EditTrainingOperation.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EditTrainingOperationProcessorTest {

    @Mock
    private TrainingRepository trainingRepository;

    @InjectMocks
    private EditTrainingOperationProcessor processor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcess_ValidRequest() {
        EditTrainingRequest request = EditTrainingRequest.builder()
                .id(UUID.randomUUID().toString())
                .title("New Title")
                .description("New Description")
                .startDate("2024-04-01")
                .endDate("2024-04-05")
                .build();

        Training training = new Training();
        training.setId(UUID.fromString(request.getId()));
        training.setTitle("Old Title");
        training.setDescription("Old Description");
        training.setStartDate(LocalDate.parse("2024-03-01"));
        training.setEndDate(LocalDate.parse("2024-03-05"));

        when(trainingRepository.findById(UUID.fromString(request.getId()))).thenReturn(Optional.of(training));
        when(trainingRepository.save(any(Training.class))).thenAnswer(invocation -> invocation.getArgument(0));

        EditTrainingResponse response = processor.process(request);

        assertEquals(request.getId(), response.getId());
        assertEquals(request.getTitle(), response.getTitle());
        assertEquals(request.getDescription(), response.getDescription());
        assertEquals(request.getStartDate(), response.getStartDate());
        assertEquals(request.getEndDate(), response.getEndDate());

        verify(trainingRepository, times(1)).findById(UUID.fromString(request.getId()));
        verify(trainingRepository, times(1)).save(any(Training.class));
    }

    @Test
    void testProcess_TrainingNotFound() {
        EditTrainingRequest request = EditTrainingRequest.builder()
                .id(UUID.randomUUID().toString())
                .build();

        when(trainingRepository.findById(UUID.fromString(request.getId()))).thenReturn(Optional.empty());

        TrainingNotFoundException exception = assertThrows(TrainingNotFoundException.class, () -> {
            processor.process(request);
        });

        assertEquals("Training not found", exception.getMessage());

        verify(trainingRepository, times(1)).findById(UUID.fromString(request.getId()));
        verify(trainingRepository, never()).save(any());
    }

}
