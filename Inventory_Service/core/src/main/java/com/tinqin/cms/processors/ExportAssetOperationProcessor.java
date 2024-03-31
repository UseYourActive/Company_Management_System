package com.tinqin.cms.processors;

import com.tinqin.cms.converters.StorageToExportResponseConverter;
import com.tinqin.cms.entities.Storage;
import com.tinqin.cms.operations.ExportAssetOperation;
import com.tinqin.cms.repositories.StorageRepository;
import com.tinqin.cms.utils.RepositoryUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class ExportAssetOperationProcessor implements ExportAssetOperation {
    private final StorageRepository storageBookRepository;
    private final RepositoryUtils repositoryUtils;
    private final StorageToExportResponseConverter converter;

    @Override
    public ExportAssetResponse process(final ExportAssetRequest request) {
        String id = request.getId();
        String quantityToExport = request.getQuantityToExport();
        log.info("Processing request to export {} units with ID: {}", quantityToExport, id);

        Storage storage = repositoryUtils.findByStorageBookIdOrThrow(storageBookRepository, UUID.fromString(id), Storage.class.getName());

        storage.setQuantity(storage.getQuantity() - Integer.parseInt(quantityToExport));
        log.info("Updated quantity for asset with ID {}: new quantity = {}", id, storage.getQuantity());

        Storage saved = storageBookRepository.save(storage);
        log.info("Asset with ID {} saved successfully", id);

        ExportAssetResponse response = converter.convert(saved);
        log.info("Export operation completed successfully for asset with ID: {}", id);

        return response;
    }
}
