package com.tinqin.cms.processors;

import com.tinqin.cms.operations.DeleteStorageBooksByIdsOperation;
import com.tinqin.cms.repositories.StorageBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class DeleteStorageBooksByIdsOperationProcessor implements DeleteStorageBooksByIdsOperation {
    private final StorageBookRepository storageBookRepository;

    @Override
    public DeleteStorageBooksByIdsResponse process(final DeleteStorageBooksByIdsRequest request) {
        List<String> ids = request.getIds();

        List<UUID> uuidList = ids.stream()
                .map(UUID::fromString)
                .toList();
        log.info("Deleting storage books with IDs: {}", uuidList);

        storageBookRepository.deleteAllById(uuidList);
        log.info("Deleted storage books with IDs: {}", uuidList);

        return DeleteStorageBooksByIdsResponse.builder()
                .status("Deleting operation complete")
                .build();
    }
}
