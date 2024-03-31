package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Training;
import com.tinqin.cms.exceptions.TrainingNotFoundException;
import com.tinqin.cms.operations.DeleteTrainingOperation;
import com.tinqin.cms.repositories.TrainingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteTrainingOperationProcessor implements DeleteTrainingOperation {
    private final TrainingRepository trainingRepository;

    @Override
    public DeleteTrainingResponse process(final DeleteTrainingRequest request) {
        String id = request.getId();

        log.info("Processing request to delete training with ID: {}", id);

        Training training = trainingRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> {
                    log.error("Training with ID {} not found for deletion", id);
                    return new TrainingNotFoundException();
                });

        trainingRepository.delete(training);

        log.info("Training with ID {} successfully deleted", id);

        return DeleteTrainingResponse.builder()
                .isSuccessfullyDeleted(Boolean.TRUE)
                .build();
    }
}
