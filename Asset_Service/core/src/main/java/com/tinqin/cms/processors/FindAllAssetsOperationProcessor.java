package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Asset;
import com.tinqin.cms.operations.FindAllAssetsOperation;
import com.tinqin.cms.repositories.AssetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class FindAllAssetsOperationProcessor implements FindAllAssetsOperation {
    private final AssetRepository assetRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public FindAllAssetsResponse process(final FindAllAssetsRequest request) {
        log.info("Processing find all assets request: {}", request);

        PageRequest pageRequest = PageRequest.of(
                request.getPageNumber(),
                request.getNumberOfAssetsPerPage());

        log.debug("Fetching assets from database with page request: {}", pageRequest);

        List<FindAllAssetsResponseDTO> list = assetRepository.findAll(pageRequest)
                .stream()
                .map(this::map)
                .toList();

        log.info("Found {} assets", list.size());

        kafkaTemplate.send("ASSET-SERVICE", "Successfully found all assets.");

        return FindAllAssetsResponse.builder()
                .findAllAssetsResponseDTOS(list)
                .build();
    }

    private FindAllAssetsResponseDTO map(Asset asset){
        return FindAllAssetsResponseDTO.builder()
                .id(String.valueOf(asset.getId()))
                .name(asset.getName())
                .serialNumber(asset.getSerialNumber())
                .description(asset.getDescription())
                .assetStatus(String.valueOf(asset.getAssetStatus()))
                .assetType(String.valueOf(asset.getAssetType()))
                .build();
    }
}
