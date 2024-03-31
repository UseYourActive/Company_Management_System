package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Training;
import com.tinqin.cms.operations.FindAllTrainingsOperation;
import com.tinqin.cms.repositories.TrainingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindAllTrainingsOperationProcessor implements FindAllTrainingsOperation {
    private final TrainingRepository trainingRepository;

    @Override
    public FindAllTrainingsResponse process(final FindAllTrainingsRequest request) {
        log.info("Processing request to find all trainings");

        PageRequest pageRequest = PageRequest.of(
                request.getPageNumber(),
                request.getNumberOfBooksPerPage());

        List<FindAllTrainingsResponseDTO> list = trainingRepository.findAll(pageRequest)
                .stream()
                .map(this::map)
                .toList();

        log.info("Found {} trainings", list.size());

        return FindAllTrainingsResponse.builder()
                .findAllTrainingsResponseDTOS(list)
                .build();
    }

    private FindAllTrainingsResponseDTO map(Training training) {
        return FindAllTrainingsResponseDTO.builder()
                .id(String.valueOf(training.getId()))
                .description(training.getDescription())
                .title(training.getTitle())
                .startDate(String.valueOf(training.getStartDate()))
                .endDate(String.valueOf(training.getEndDate()))
                .build();
    }
}
