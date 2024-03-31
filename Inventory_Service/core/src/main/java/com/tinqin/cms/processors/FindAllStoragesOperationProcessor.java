package com.tinqin.cms.processors;

import com.tinqin.cms.converters.FindAllStoragesResponseDTOConverter;
import com.tinqin.cms.entities.Storage;
import com.tinqin.cms.operations.FindAllStoragesOperation;
import com.tinqin.cms.repositories.StorageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class FindAllStoragesOperationProcessor implements FindAllStoragesOperation {
    private final StorageRepository storageBookRepository;
    private final FindAllStoragesResponseDTOConverter converter;

    @Override
    public FindAllStoragesResponse process(final FindAllStoragesRequest request) {
        log.info("Processing request to find all storage assets");

        Integer pageNumber = request.getPageNumber();
        Integer numberOfBooksPerPage = request.getNumberOfBooksPerPage();

        PageRequest pageRequest = PageRequest.of(pageNumber, numberOfBooksPerPage);

        Page<Storage> all = storageBookRepository.findAll(pageRequest);
        log.debug("Found {} storage assets", all.getTotalElements());

        List<FindAllStoragesResponseDTO> storagesResponseDTOS = all.stream()
                .map(converter::convert)
                .toList();

        FindAllStoragesResponse response = FindAllStoragesResponse.builder()
                .findAllStoragesResponseDTOS(storagesResponseDTOS)
                .build();
        log.info("Found {} storage assets in response", storagesResponseDTOS.size());

        return response;
    }
}
