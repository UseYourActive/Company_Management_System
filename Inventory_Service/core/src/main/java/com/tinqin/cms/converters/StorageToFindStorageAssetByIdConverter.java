package com.tinqin.cms.converters;

import com.tinqin.cms.entities.Storage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.tinqin.cms.operations.FindStorageByIdOperation.*;

@Component
@Slf4j
public class StorageToFindStorageAssetByIdConverter implements Converter<Storage, FindStorageByIdResponse> {
    @Override
    public FindStorageByIdResponse convert(Storage source) {
        log.debug("Converting StorageBook to FindStorageBookByIdResponse: {}", source);

        FindStorageByIdResponse response = FindStorageByIdResponse.builder()
                .id(String.valueOf(source.getId()))
                .targetAssetId(String.valueOf(source.getItemId()))
                .price(String.valueOf(source.getPrice()))
                .quantity(String.valueOf(source.getQuantity()))
                .build();
        log.debug("Conversion result: {}", response);

        return response;
    }
}
