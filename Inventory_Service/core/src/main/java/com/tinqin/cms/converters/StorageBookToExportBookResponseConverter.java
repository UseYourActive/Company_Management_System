package com.tinqin.cms.converters;

import com.tinqin.cms.entities.StorageBook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.tinqin.cms.operations.ExportBookOperation.*;

@Component
@Slf4j
@Qualifier("StorageBookToExportBookResponseConverter")
public class StorageBookToExportBookResponseConverter implements Converter<StorageBook, ExportBookResponse> {
    @Override
    public ExportBookResponse convert(StorageBook source) {
        log.debug("Converting StorageBook to ExportBookResponse: {}", source);

        ExportBookResponse response = ExportBookResponse.builder()
                .storageItemId(String.valueOf(source.getId()))
                .targetBookId(String.valueOf(source.getBookId()))
                .price(String.valueOf(source.getPrice()))
                .quantity(String.valueOf(source.getQuantity()))
                .build();
        log.debug("Conversion result: {}", response);

        return response;
    }
}
