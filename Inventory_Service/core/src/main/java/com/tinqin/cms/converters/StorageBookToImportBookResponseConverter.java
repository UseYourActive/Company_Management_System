package com.tinqin.cms.converters;

import com.tinqin.cms.entities.Storage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.tinqin.cms.operations.ImportAssetOperation.*;

@Component
@Slf4j
@Qualifier("StorageBookToImportBookResponseConverter")
public class StorageBookToImportBookResponseConverter implements Converter<Storage, ImportAssetResponse> {
    @Override
    public ImportAssetResponse convert(Storage source) {
        log.debug("Converting StorageBook to ImportBookResponse: {}", source);

        ImportAssetResponse response = ImportAssetResponse.builder()
                .id(String.valueOf(source.getId()))
                .targetAssetId(String.valueOf(source.getItemId()))
                .price(String.valueOf(source.getPrice()))
                .quantity(String.valueOf(source.getQuantity()))
                .build();
        log.debug("Conversion result: {}", response);

        return response;
    }
}
