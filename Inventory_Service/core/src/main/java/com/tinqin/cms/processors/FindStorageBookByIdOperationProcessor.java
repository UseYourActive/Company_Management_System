package com.tinqin.cms.processors;

import com.tinqin.cms.converters.StorageToFindStorageAssetByIdConverter;
import com.tinqin.cms.entities.Storage;
import com.tinqin.cms.operations.FindStorageByIdOperation;
import com.tinqin.cms.repositories.StorageRepository;
import com.tinqin.cms.utils.RepositoryUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class FindStorageBookByIdOperationProcessor implements FindStorageByIdOperation {
    private final StorageRepository storageBookRepository;
    private final RepositoryUtils repositoryUtils;
    private final StorageToFindStorageAssetByIdConverter converter;

    @Override
    public FindStorageByIdResponse process(final FindStorageByIdRequest request) {
        String id = request.getId();
        log.info("Processing request to find StorageBook by ID: {}", id);

        Storage storage = repositoryUtils.findByStorageBookIdOrThrow(storageBookRepository, UUID.fromString(id), Storage.class.getName());
        log.debug("Found StorageBook by ID: {}", id);

        FindStorageByIdResponse response = converter.convert(storage);
        log.info("Find StorageBook by ID operation completed successfully for ID: {}", id);

        return response;
    }
}
