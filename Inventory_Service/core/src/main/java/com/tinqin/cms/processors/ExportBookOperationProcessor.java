package com.tinqin.cms.processors;

import com.tinqin.cms.converters.StorageBookToExportBookResponseConverter;
import com.tinqin.cms.entities.StorageBook;
import com.tinqin.cms.operations.ExportBookOperation;
import com.tinqin.cms.repositories.StorageBookRepository;
import com.tinqin.cms.utils.RepositoryUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class ExportBookOperationProcessor implements ExportBookOperation {
    private final StorageBookRepository storageBookRepository;
    private final RepositoryUtils repositoryUtils;
    private final StorageBookToExportBookResponseConverter storageBookToExportBookResponseConverter;

    @Override
    public ExportBookResponse process(final ExportBookRequest request) {
        String bookId = request.getBookId();
        String quantityToExport = request.getQuantityToExport();
        log.info("Processing request to export {} units of book with ID: {}", quantityToExport, bookId);

        StorageBook storageBook = repositoryUtils.findByStorageBookIdOrThrow(storageBookRepository, UUID.fromString(bookId), StorageBook.class.getName());

//        StorageBook storageBook = storageBookRepository.findById(UUID.fromString(bookId))
//                .orElseThrow(() -> {
//                    log.error("StorageBook not found for ID: {}", bookId);
//                    return new StorageBookNotFoundException();
//                });

        storageBook.setQuantity(storageBook.getQuantity() - Integer.parseInt(quantityToExport));
        log.info("Updated quantity for book with ID {}: new quantity = {}", bookId, storageBook.getQuantity());

        StorageBook savedBook = storageBookRepository.save(storageBook);
        log.info("Book with ID {} saved successfully", bookId);

        ExportBookResponse response = storageBookToExportBookResponseConverter.convert(savedBook);
        log.info("Export operation completed successfully for book with ID: {}", bookId);

        return response;
    }
}
