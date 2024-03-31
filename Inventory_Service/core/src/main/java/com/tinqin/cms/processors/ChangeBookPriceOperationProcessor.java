package com.tinqin.cms.processors;

import com.tinqin.cms.converters.StorageBookToChangeBookPriceResponseConverter;
import com.tinqin.cms.converters.StringToBigDecimalConverter;
import com.tinqin.cms.entities.StorageBook;
import com.tinqin.cms.operations.ChangeBookPriceOperation;
import com.tinqin.cms.repositories.StorageBookRepository;
import com.tinqin.cms.utils.RepositoryUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class ChangeBookPriceOperationProcessor implements ChangeBookPriceOperation {
    private final StorageBookRepository storageBookRepository;
    private final StorageBookToChangeBookPriceResponseConverter storageBookToChangeBookPriceResponseConverter;
    private final StringToBigDecimalConverter stringToBigDecimalConverter;
    private final RepositoryUtils repositoryUtils;

    @Override
    public ChangeBookPriceResponse process(final ChangeBookPriceRequest request) {
        String bookId = request.getBookId();
        String inputPrice = request.getNewPrice();
        log.info("Processing request to change price for book with ID: {}", bookId);

        StorageBook storageBook = repositoryUtils.findByStorageBookIdOrThrow(storageBookRepository, UUID.fromString(bookId), StorageBook.class.getName());

//        StorageBook storageBook = storageBookRepository.findById(UUID.fromString(bookId))
//                .orElseThrow(() -> {
//                    log.error("StorageBook not found for ID: {}", bookId);
//                    return new StorageBookNotFoundException();
//                });

        BigDecimal newPrice = stringToBigDecimalConverter.convert(inputPrice);
        storageBook.setPrice(newPrice);
        log.info("Updated price for book with ID {} to: {}", bookId, newPrice);

        StorageBook savedBook = storageBookRepository.save(storageBook);
        log.info("Book with ID {} saved successfully", bookId);

        ChangeBookPriceResponse response = storageBookToChangeBookPriceResponseConverter.convert(savedBook);
        log.info("Change price operation completed successfully for book with ID: {}", bookId);

        return response;
    }
}
