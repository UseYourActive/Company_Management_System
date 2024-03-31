package com.tinqin.cms.processors;

import com.tinqin.cms.converters.StorageBookToFindStorageBookByIdConverter;
import com.tinqin.cms.entities.StorageBook;
import com.tinqin.cms.operations.FindStorageBookByIdOperation;
import com.tinqin.cms.repositories.StorageBookRepository;
import com.tinqin.cms.utils.RepositoryUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class FindStorageBookByIdOperationProcessor implements FindStorageBookByIdOperation {
    private final StorageBookRepository storageBookRepository;
    private final RepositoryUtils repositoryUtils;
    private final StorageBookToFindStorageBookByIdConverter storageBookToFindStorageBookByIdConverter;

    @Override
    public FindStorageBookByIdResponse process(final FindStorageBookByIdRequest request) {
        String storageBookId = request.getStorageBookId();
        log.info("Processing request to find StorageBook by ID: {}", storageBookId);

        StorageBook storageBook = repositoryUtils.findByStorageBookIdOrThrow(storageBookRepository, UUID.fromString(storageBookId), StorageBook.class.getName());
        log.debug("Found StorageBook by ID: {}", storageBookId);

        FindStorageBookByIdResponse response = storageBookToFindStorageBookByIdConverter.convert(storageBook);
        log.info("Find StorageBook by ID operation completed successfully for ID: {}", storageBookId);

        return response;
    }
}
