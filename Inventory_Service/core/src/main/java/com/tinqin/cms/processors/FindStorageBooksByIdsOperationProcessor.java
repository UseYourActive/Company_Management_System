package com.tinqin.cms.processors;

import com.tinqin.cms.converters.FindStorageAssetsByIdsResponseDTOConverter;
import com.tinqin.cms.entities.Storage;
import com.tinqin.cms.operations.FindStorageAssetsByIdsOperation;
import com.tinqin.cms.repositories.StorageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class FindStorageBooksByIdsOperationProcessor implements FindStorageAssetsByIdsOperation {
    private final StorageRepository storageBookRepository;
    private final FindStorageAssetsByIdsResponseDTOConverter converter;

    @Override
    public FindStorageAssetsByIdsResponse process(final FindStorageAssetsByIdsRequest request) {
        log.info("Processing request to find storage assets by IDs");

        List<String> ids = request.getIds();

        List<UUID> uuidList = ids.stream()
                .map(UUID::fromString)
                .toList();

        List<Storage> list = storageBookRepository.findAllById(uuidList);
        log.debug("Found {} storage assets by IDs", list.size());

        List<FindStorageBooksByIdsResponseDTO> storageBookResponseDTOList = list.stream()
                .map(converter::convert)
                .toList();

        FindStorageAssetsByIdsResponse response = FindStorageAssetsByIdsResponse.builder()
                .findStorageBooksByIdsResponseDTOS(storageBookResponseDTOList)
                .build();
        log.info("Found {} storage assets by IDs in response", storageBookResponseDTOList.size());

        return response;
    }
}
