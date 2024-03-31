package com.tinqin.cms;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.tinqin.cms.operations.CreateNewTrainingOperation.*;
import static com.tinqin.cms.operations.DeleteTrainingOperation.*;
import static com.tinqin.cms.operations.EditTrainingOperation.*;
import static com.tinqin.cms.operations.FindAllTrainingsOperation.*;
import static com.tinqin.cms.operations.FindByIdTrainingOperation.*;


@FeignClient(name = "TRAINING-AND-DEVELOPMENT-SERVICE")
public interface TrainingAndDevelopmentFeignClient {
    @GetMapping(path = "/training/{id}")
    ResponseEntity<FindByIdTrainingResponse> findTrainingById(@PathVariable(value = "id") String id);

    @GetMapping(path = "/training/find-all")
    ResponseEntity<FindAllTrainingsResponse> findAllTrainings(@RequestParam(defaultValue = "1") Integer pageNumber,
                                                              @RequestParam(defaultValue = "2") Integer numberOfBooksPerPage);

    @PostMapping(path = "/training/create")
    ResponseEntity<CreateNewTrainingResponse> createNewTraining(@RequestBody CreateNewTrainingRequest request);

    @PatchMapping(path = "/training/edit")
    ResponseEntity<EditTrainingResponse> editTraining(@RequestBody EditTrainingRequest request);

    @DeleteMapping(path = "/training/{id}")
    ResponseEntity<DeleteTrainingResponse> deleteTraining(@PathVariable("id") String id);
}
