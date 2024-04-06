package com.tinqin.cms.processors;

import com.tinqin.cms.converters.StorageAssetToRegisterNewAssetResponseConverter;
import com.tinqin.cms.converters.StringToBigDecimalConverter;
import com.tinqin.cms.entities.Storage;
import com.tinqin.cms.operations.RegisterNewAssetOperation;
import com.tinqin.cms.repositories.StorageRepository;
import com.tinqin.cms.utils.RepositoryUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class RegisterNewAssetOperationProcessor implements RegisterNewAssetOperation {
    private final StorageRepository storageRepository;
    private final StorageAssetToRegisterNewAssetResponseConverter converter;
    private final StringToBigDecimalConverter stringToBigDecimalConverter;
    private final RepositoryUtils repositoryUtils;

    @Override
    public RegisterNewAssetResponse process(final RegisterNewAssetRequest request) {
        String id = request.getId();
        String price = request.getPrice();
        log.info("Processing request to register new asset with ID: {}", id);

        Storage storage = repositoryUtils.findByAssetIdOrThrow(storageRepository, UUID.fromString(id), Storage.class.getName());

        BigDecimal newPrice = stringToBigDecimalConverter.convert(price);
        storage.setPrice(newPrice);
        log.info("Updated price for asset with ID {}: new price = {}", id, newPrice);

        Storage savedBook = storageRepository.save(storage);
        log.info("Asset with ID {} saved successfully", id);

        RegisterNewAssetResponse response = converter.convert(savedBook);
        log.info("Registration operation completed successfully for asset with ID: {}", id);

        return response;
    }
}
