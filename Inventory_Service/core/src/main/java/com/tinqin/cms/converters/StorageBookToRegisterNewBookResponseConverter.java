package com.tinqin.cms.converters;

import com.tinqin.cms.entities.StorageBook;
import com.tinqin.cms.operations.RegisterNewBookOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.tinqin.cms.operations.RegisterNewBookOperation.*;

@Component
@Slf4j
@Qualifier("StorageBookToRegisterNewBookResponseConverter")
public class StorageBookToRegisterNewBookResponseConverter implements Converter<StorageBook, RegisterNewBookResponse> {
    @Override
    public RegisterNewBookResponse convert(StorageBook source) {
        log.debug("Converting StorageBook to RegisterNewBookResponse: {}", source);

        RegisterNewBookResponse response = RegisterNewBookResponse.builder()
                .storageItemId(String.valueOf(source.getId()))
                .targetBookId(String.valueOf(source.getBookId()))
                .price(String.valueOf(source.getPrice()))
                .quantity(String.valueOf(source.getQuantity()))
                .build();
        log.debug("Conversion result: {}", response);

        return response;
    }
}
