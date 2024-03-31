package com.tinqin.cms.processors;

import com.tinqin.cms.entities.StorageBook;
import com.tinqin.cms.operations.DeleteStorageBookByIdOperation;
import com.tinqin.cms.repositories.StorageBookRepository;
import com.tinqin.cms.utils.RepositoryUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class DeleteStorageBookByIdOperationProcessor implements DeleteStorageBookByIdOperation {
    private final StorageBookRepository storageBookRepository;
    private final RepositoryUtils repositoryUtils;

    @Override
    public DeleteStorageBookByIdResponse process(final DeleteStorageBookByIdRequest request) {
        String storageBookId = request.getStorageBookId();

        StorageBook storageBook = repositoryUtils.findByStorageBookIdOrThrow(storageBookRepository, UUID.fromString(storageBookId), StorageBook.class.getName());
        log.info("Deleting storage book with ID: {}", storageBookId);

        storageBookRepository.delete(storageBook);
        log.info("Storage book with ID {} deleted successfully", storageBookId);

        DeleteStorageBookByIdResponse response = DeleteStorageBookByIdResponse.builder()
                .storageItemId(String.valueOf(storageBook.getId()))
                .targetBookId(String.valueOf(storageBook.getBookId()))
                .price(String.valueOf(storageBook.getPrice()))
                .quantity(String.valueOf(storageBook.getQuantity()))
                .status("Deleted")
                .build();
        log.info("Delete operation completed successfully for storage book with ID: {}", storageBookId);

        return response;
    }
}
