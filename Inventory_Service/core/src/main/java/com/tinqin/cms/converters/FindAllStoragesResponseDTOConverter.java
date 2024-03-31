package com.tinqin.cms.converters;

import com.tinqin.cms.entities.Storage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.tinqin.cms.operations.FindAllStoragesOperation.*;

@Component
public class FindAllStoragesResponseDTOConverter implements Converter<Storage, FindAllStoragesResponseDTO> {
    @Override
    public FindAllStoragesResponseDTO convert(Storage source) {
        return FindAllStoragesResponseDTO.builder()
                .id(String.valueOf(source.getId()))
                .targetAssetId(String.valueOf(source.getItemId()))
                .price(String.valueOf(source.getPrice()))
                .quantity(String.valueOf(source.getQuantity()))
                .build();
    }
}
