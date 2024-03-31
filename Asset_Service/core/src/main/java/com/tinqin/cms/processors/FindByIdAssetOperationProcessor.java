package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Asset;
import com.tinqin.cms.exceptions.AssetNotFoundException;
import com.tinqin.cms.operations.FindByIdAssetOperation;
import com.tinqin.cms.repositories.AssetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindByIdAssetOperationProcessor implements FindByIdAssetOperation {
    private final AssetRepository assetRepository;

    @Override
    public FindByIdAssetResponse process(final FindByIdAssetRequest request) {
        log.info("Processing find by ID asset request: {}", request);

        String id = request.getId();

        log.debug("Fetching asset with ID: {}", id);

        Asset asset = assetRepository.findById(UUID.fromString(id))
                .orElseThrow(AssetNotFoundException::new);

        log.info("Found asset with ID {}: {}", id, asset);

        return FindByIdAssetResponse.builder()
                .id(String.valueOf(asset.getId()))
                .name(asset.getName())
                .serialNumber(asset.getSerialNumber())
                .description(asset.getDescription())
                .assetStatus(String.valueOf(asset.getAssetStatus()))
                .assetType(String.valueOf(asset.getAssetType()))
                .build();
    }
}
