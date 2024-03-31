package com.tinqin.cms.processors;

import com.tinqin.cms.converters.FindAllLeavesResponseDTOConverter;
import com.tinqin.cms.entities.Leave;
import com.tinqin.cms.operations.FindAllLeavesOperation;
import com.tinqin.cms.repositories.LeaveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindAllLeavesOperationProcessor implements FindAllLeavesOperation {
    private final LeaveRepository leaveRepository;
    private final FindAllLeavesResponseDTOConverter converter;

    @Override
    public FindAllLeavesResponse process(final FindAllLeavesRequest request) {
        PageRequest pageRequest = PageRequest.of(
                request.getPageNumber(),
                request.getNumberOfBooksPerPage());

        Page<Leave> allLeaves = leaveRepository.findAll(pageRequest);

        List<FindAllLeavesResponseDTO> list = allLeaves.stream().map(converter::convert).toList();

        return FindAllLeavesResponse.builder()
                .findAllLeavesResponseDTOS(list)
                .build();
    }
}
