package com.tinqin.cms.processors;

import com.tinqin.cms.converters.StorageBookToImportBookResponseConverter;
import com.tinqin.cms.entities.StorageBook;
import com.tinqin.cms.operations.ImportBookOperation;
import com.tinqin.cms.repositories.StorageBookRepository;
import com.tinqin.cms.utils.RepositoryUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class ImportBookOperationProcessor implements ImportBookOperation {
    private final StorageBookRepository storageBookRepository;
    private final StorageBookToImportBookResponseConverter storageBookToImportBookResponseConverter;
    private final RepositoryUtils repositoryUtils;

    @Override
    public ImportBookResponse process(final ImportBookRequest request) {
        String bookId = request.getBookId();
        String quantityToImport = request.getQuantityToImport();
        log.info("Processing request to import {} units of book with ID: {}", quantityToImport, bookId);

        StorageBook storageBook = repositoryUtils.findByStorageBookIdOrThrow(storageBookRepository, UUID.fromString(bookId), StorageBook.class.getName());

//        StorageBook storageBook = storageBookRepository.findById(UUID.fromString(bookId))
//                .orElseThrow(() -> {
//                    log.error("StorageBook not found for ID: {}", bookId);
//                    return new StorageBookNotFoundException();
//                });

        storageBook.setQuantity(storageBook.getQuantity() + Integer.parseInt(quantityToImport));
        log.info("Updated quantity for book with ID {}: new quantity = {}", bookId, storageBook.getQuantity());

        StorageBook savedBook = storageBookRepository.save(storageBook);
        log.info("Book with ID {} saved successfully", bookId);

        ImportBookResponse response = storageBookToImportBookResponseConverter.convert(savedBook);
        log.info("Import operation completed successfully for book with ID: {}", bookId);

        return response;
    }
}
