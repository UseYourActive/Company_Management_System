package com.tinqin.cms.converters;

import com.tinqin.cms.entities.Storage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.tinqin.cms.operations.ExportAssetOperation.*;

@Component
@Slf4j
@Qualifier("StorageBookToExportBookResponseConverter")
public class StorageToExportResponseConverter implements Converter<Storage, ExportAssetResponse> {
    @Override
    public ExportAssetResponse convert(Storage source) {
        log.debug("Converting StorageBook to ExportBookResponse: {}", source);

        ExportAssetResponse response = ExportAssetResponse.builder()
                .id(String.valueOf(source.getId()))
                .targetAssetId(String.valueOf(source.getItemId()))
                .price(String.valueOf(source.getPrice()))
                .quantity(String.valueOf(source.getQuantity()))
                .build();
        log.debug("Conversion result: {}", response);

        return response;
    }
}
