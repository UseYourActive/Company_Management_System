package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Training;
import com.tinqin.cms.exceptions.TrainingNotFoundException;
import com.tinqin.cms.operations.EditTrainingOperation;
import com.tinqin.cms.repositories.TrainingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EditTrainingOperationProcessor implements EditTrainingOperation {
    private final TrainingRepository trainingRepository;

    @Override
    public EditTrainingResponse process(final EditTrainingRequest request) {
        String description = request.getDescription();
        String id = request.getId();
        String title = request.getTitle();
        String endDate = request.getEndDate();
        String startDate = request.getStartDate();

        log.info("Processing request to edit training with ID: {}", id);

        Training training = trainingRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new TrainingNotFoundException("Training not found"));

        Optional.ofNullable(title)
                .ifPresent(t -> {
                    log.info("Setting title: {}", t);
                    training.setTitle(t);
                });

        Optional.ofNullable(description)
                .ifPresent(desc -> {
                    log.info("Setting description: {}", desc);
                    training.setDescription(desc);
                });

        Optional.ofNullable(endDate)
                .ifPresent(date -> {
                    log.info("Setting end date: {}", date);
                    training.setEndDate(LocalDate.parse(date));
                });

        Optional.ofNullable(startDate)
                .ifPresent(date -> {
                    log.info("Setting start date: {}", date);
                    training.setStartDate(LocalDate.parse(date));
                });

        Training persistedTraining = trainingRepository.save(training);

        return EditTrainingResponse.builder()
                .id(String.valueOf(persistedTraining.getId()))
                .description(persistedTraining.getDescription())
                .startDate(String.valueOf(persistedTraining.getStartDate()))
                .endDate(String.valueOf(persistedTraining.getEndDate()))
                .title(persistedTraining.getTitle())
                .build();
    }
}
