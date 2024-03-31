package com.tinqin.cms.converters;

import com.tinqin.cms.entities.Storage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.tinqin.cms.operations.FindStorageAssetsByIdsOperation.*;

@Component
public class FindStorageAssetsByIdsResponseDTOConverter implements Converter<Storage, FindStorageBooksByIdsResponseDTO> {
    @Override
    public FindStorageBooksByIdsResponseDTO convert(Storage source) {
        return FindStorageBooksByIdsResponseDTO.builder()
                .id(String.valueOf(source.getId()))
                .targetAssetId(String.valueOf(source.getItemId()))
                .price(String.valueOf(source.getPrice()))
                .quantity(String.valueOf(source.getQuantity()))
                .build();
    }
}
