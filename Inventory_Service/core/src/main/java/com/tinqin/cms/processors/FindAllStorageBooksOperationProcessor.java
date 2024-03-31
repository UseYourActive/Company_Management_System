package com.tinqin.cms.processors;

import com.tinqin.cms.converters.FindAllStorageBooksResponseDTOConverter;
import com.tinqin.cms.entities.StorageBook;
import com.tinqin.cms.operations.FindAllStorageBooksOperation;
import com.tinqin.cms.repositories.StorageBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class FindAllStorageBooksOperationProcessor implements FindAllStorageBooksOperation {
    private final StorageBookRepository storageBookRepository;
    private final FindAllStorageBooksResponseDTOConverter converter;

    @Override
    public FindAllStorageBooksResponse process(final FindAllStorageBooksRequest request) {
        log.info("Processing request to find all storage books");

        Integer pageNumber = request.getPageNumber();
        Integer numberOfBooksPerPage = request.getNumberOfBooksPerPage();

        PageRequest pageRequest = PageRequest.of(pageNumber, numberOfBooksPerPage);

        Page<StorageBook> allStorageBooks = storageBookRepository.findAll(pageRequest);
        log.debug("Found {} storage books", allStorageBooks.getTotalElements());

        List<FindAllStorageBooksResponseDTO> storageBookResponseDTOList = allStorageBooks.stream()
                .map(converter::convert)
                .toList();

        FindAllStorageBooksResponse response = FindAllStorageBooksResponse.builder()
                .findAllStorageBooksResponseDTOS(storageBookResponseDTOList)
                .build();
        log.info("Found {} storage books in response", storageBookResponseDTOList.size());

        return response;
    }
}
