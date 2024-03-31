package com.tinqin.cms.processors;

import com.tinqin.cms.converters.StorageBookToImportBookResponseConverter;
import com.tinqin.cms.entities.Storage;
import com.tinqin.cms.operations.ImportAssetOperation;
import com.tinqin.cms.repositories.StorageRepository;
import com.tinqin.cms.utils.RepositoryUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class ImportAssetOperationProcessor implements ImportAssetOperation {
    private final StorageRepository storageBookRepository;
    private final StorageBookToImportBookResponseConverter storageBookToImportBookResponseConverter;
    private final RepositoryUtils repositoryUtils;

    @Override
    public ImportAssetResponse process(final ImportAssetRequest request) {
        String id = request.getId();
        String quantityToImport = request.getQuantityToImport();
        log.info("Processing request to import {} units of assets with ID: {}", quantityToImport, id);

        Storage asset = repositoryUtils.findByStorageBookIdOrThrow(storageBookRepository, UUID.fromString(id), Storage.class.getName());

        asset.setQuantity(asset.getQuantity() + Integer.parseInt(quantityToImport));
        log.info("Updated quantity for asset with ID {}: new quantity = {}", id, asset.getQuantity());

        Storage savedBook = storageBookRepository.save(asset);
        log.info("Asset with ID {} saved successfully", id);

        ImportAssetResponse response = storageBookToImportBookResponseConverter.convert(savedBook);
        log.info("Import operation completed successfully for asset with ID: {}", id);

        return response;
    }
}
