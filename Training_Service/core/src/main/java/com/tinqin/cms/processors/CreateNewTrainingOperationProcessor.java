package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Training;
import com.tinqin.cms.operations.CreateNewTrainingOperation;
import com.tinqin.cms.repositories.TrainingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateNewTrainingOperationProcessor implements CreateNewTrainingOperation {
    private final TrainingRepository trainingRepository;

    @Override
    public CreateNewTrainingResponse process(final CreateNewTrainingRequest request) {
        String description = request.getDescription();
        String title = request.getTitle();
        String endDate = request.getEndDate();
        String startDate = request.getStartDate();

        log.info("Creating new training with title: {}, start date: {}, end date: {}, and description: {}",
                title, startDate, endDate, description);

        Training training = Training.builder()
                .description(description)
                .title(title)
                .endDate(LocalDate.parse(endDate))
                .startDate(LocalDate.parse(startDate))
                .build();

        Training persistedTraining = trainingRepository.save(training);

        log.info("New training created with ID: {}", persistedTraining.getId());

        return CreateNewTrainingResponse.builder()
                .id(String.valueOf(persistedTraining.getId()))
                .description(persistedTraining.getDescription())
                .title(persistedTraining.getTitle())
                .startDate(String.valueOf(persistedTraining.getStartDate()))
                .endDate(String.valueOf(persistedTraining.getEndDate()))
                .build();
    }
}
