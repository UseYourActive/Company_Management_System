package com.tinqin.cms.converters;

import com.tinqin.cms.entities.Storage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.tinqin.cms.operations.ChangePriceOperation.*;

@Component
@Slf4j
@Qualifier("StorageBookToChangeBookPriceResponseConverter")
public class StorageToChangePriceResponseConverter implements Converter<Storage, ChangePriceResponse> {
    @Override
    public ChangePriceResponse convert(Storage source) {
        log.debug("Converting StorageBook to ChangeBookPriceResponse: {}", source);

        ChangePriceResponse response = ChangePriceResponse.builder()
                .id(String.valueOf(source.getId()))
                .targetAssetId(String.valueOf(source.getItemId()))
                .price(String.valueOf(source.getPrice()))
                .quantity(String.valueOf(source.getQuantity()))
                .build();
        log.debug("Conversion result: {}", response);

        return response;
    }
}
