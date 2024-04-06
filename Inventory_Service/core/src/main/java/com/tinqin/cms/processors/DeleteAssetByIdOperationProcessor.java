package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Storage;
import com.tinqin.cms.operations.DeleteStorageByIdOperation;
import com.tinqin.cms.repositories.StorageRepository;
import com.tinqin.cms.utils.RepositoryUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class DeleteAssetByIdOperationProcessor implements DeleteStorageByIdOperation {
    private final StorageRepository storageRepository;
    private final RepositoryUtils repositoryUtils;

    @Override
    public DeleteStorageByIdResponse process(final DeleteStorageByIdRequest request) {
        String id = request.getId();

        Storage storageBook = repositoryUtils.findByAssetIdOrThrow(storageRepository, UUID.fromString(id), Storage.class.getName());
        log.info("Deleting storage with ID: {}", id);

        storageRepository.delete(storageBook);
        log.info("Storage with ID {} deleted successfully", id);

        DeleteStorageByIdResponse response = DeleteStorageByIdResponse.builder()
                .id(String.valueOf(storageBook.getId()))
                .targetAssetId(String.valueOf(storageBook.getItemId()))
                .price(String.valueOf(storageBook.getPrice()))
                .quantity(String.valueOf(storageBook.getQuantity()))
                .status("Deleted")
                .build();
        log.info("Delete operation completed successfully for storage with ID: {}", id);

        return response;
    }
}
