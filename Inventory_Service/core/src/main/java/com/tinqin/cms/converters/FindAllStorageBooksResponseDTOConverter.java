package com.tinqin.cms.converters;

import com.tinqin.cms.entities.StorageBook;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.tinqin.cms.operations.FindAllStorageBooksOperation.*;

@Component
public class FindAllStorageBooksResponseDTOConverter implements Converter<StorageBook, FindAllStorageBooksResponseDTO> {
    @Override
    public FindAllStorageBooksResponseDTO convert(StorageBook source) {
        return FindAllStorageBooksResponseDTO.builder()
                .storageItemId(String.valueOf(source.getId()))
                .targetBookId(String.valueOf(source.getBookId()))
                .price(String.valueOf(source.getPrice()))
                .quantity(String.valueOf(source.getQuantity()))
                .build();
    }
}
