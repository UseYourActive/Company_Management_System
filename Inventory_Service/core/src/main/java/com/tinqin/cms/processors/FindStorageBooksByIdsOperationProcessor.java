package com.tinqin.cms.processors;

import com.tinqin.cms.converters.FindStorageBooksByIdsResponseDTOConverter;
import com.tinqin.cms.entities.StorageBook;
import com.tinqin.cms.operations.FindStorageBooksByIdsOperation;
import com.tinqin.cms.repositories.StorageBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class FindStorageBooksByIdsOperationProcessor implements FindStorageBooksByIdsOperation {
    private final StorageBookRepository storageBookRepository;
    private final FindStorageBooksByIdsResponseDTOConverter converter;

    @Override
    public FindStorageBooksByIdsResponse process(final FindStorageBooksByIdsRequest request) {
        log.info("Processing request to find storage books by IDs");

        List<String> ids = request.getIds();

        List<UUID> uuidList = ids.stream()
                .map(UUID::fromString)
                .toList();

        List<StorageBook> storageBookList = storageBookRepository.findAllById(uuidList);
        log.debug("Found {} storage books by IDs", storageBookList.size());

        List<FindStorageBooksByIdsResponseDTO> storageBookResponseDTOList = storageBookList.stream()
                .map(converter::convert)
                .toList();

        FindStorageBooksByIdsResponse response = FindStorageBooksByIdsResponse.builder()
                .findStorageBooksByIdsResponseDTOS(storageBookResponseDTOList)
                .build();
        log.info("Found {} storage books by IDs in response", storageBookResponseDTOList.size());

        return response;
    }
}
