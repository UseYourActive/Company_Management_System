package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Training;
import com.tinqin.cms.exceptions.TrainingNotFoundException;
import com.tinqin.cms.operations.FindByIdTrainingOperation;
import com.tinqin.cms.repositories.TrainingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindByIdTrainingOperationProcessor implements FindByIdTrainingOperation {
    private final TrainingRepository trainingRepository;

    @Override
    public FindByIdTrainingResponse process(final FindByIdTrainingRequest request) {
        String id = request.getId();

        log.info("Processing request to find training by ID: {}", id);

        Training training = trainingRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> {
                    log.error("Training not found for ID: {}", id);
                    return new TrainingNotFoundException();
                });

        log.info("Found training by ID: {}", id);

        return FindByIdTrainingResponse.builder()
                .id(String.valueOf(training.getId()))
                .title(training.getTitle())
                .description(training.getDescription())
                .startDate(String.valueOf(training.getStartDate()))
                .endDate(String.valueOf(training.getEndDate()))
                .build();
    }
}
