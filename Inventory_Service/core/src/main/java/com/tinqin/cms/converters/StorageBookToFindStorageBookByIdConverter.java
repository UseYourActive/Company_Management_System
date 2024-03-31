package com.tinqin.cms.converters;

import com.tinqin.cms.entities.StorageBook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.tinqin.cms.operations.FindStorageBookByIdOperation.*;

@Component
@Slf4j
public class StorageBookToFindStorageBookByIdConverter implements Converter<StorageBook, FindStorageBookByIdResponse> {
    @Override
    public FindStorageBookByIdResponse convert(StorageBook source) {
        log.debug("Converting StorageBook to FindStorageBookByIdResponse: {}", source);

        FindStorageBookByIdResponse response = FindStorageBookByIdResponse.builder()
                .storageItemId(String.valueOf(source.getId()))
                .targetBookId(String.valueOf(source.getBookId()))
                .price(String.valueOf(source.getPrice()))
                .quantity(String.valueOf(source.getQuantity()))
                .build();
        log.debug("Conversion result: {}", response);

        return response;
    }
}
