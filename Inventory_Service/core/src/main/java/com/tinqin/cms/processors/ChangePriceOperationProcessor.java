package com.tinqin.cms.processors;

import com.tinqin.cms.converters.StorageToChangePriceResponseConverter;
import com.tinqin.cms.converters.StringToBigDecimalConverter;
import com.tinqin.cms.entities.Storage;
import com.tinqin.cms.operations.ChangePriceOperation;
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
public class ChangePriceOperationProcessor implements ChangePriceOperation {
    private final StorageRepository storageRepository;
    private final StorageToChangePriceResponseConverter converter;
    private final StringToBigDecimalConverter stringToBigDecimalConverter;
    private final RepositoryUtils repositoryUtils;

    @Override
    public ChangePriceResponse process(final ChangePriceRequest request) {
        String id = request.getId();
        String inputPrice = request.getNewPrice();
        log.info("Processing request to change price for asset with ID: {}", id);

        Storage storage = repositoryUtils.findByAssetIdOrThrow(storageRepository, UUID.fromString(id), Storage.class.getName());

        BigDecimal newPrice = stringToBigDecimalConverter.convert(inputPrice);
        storage.setPrice(newPrice);
        log.info("Updated price for book with ID {} to: {}", id, newPrice);

        Storage saved = storageRepository.save(storage);
        log.info("Asset with ID {} saved successfully", id);

        ChangePriceResponse response = converter.convert(saved);
        log.info("Change price operation completed successfully for asset with ID: {}", id);

        return response;
    }
}
