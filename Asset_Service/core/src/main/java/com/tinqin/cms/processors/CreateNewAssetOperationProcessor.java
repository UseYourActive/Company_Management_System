package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Asset;
import com.tinqin.cms.enums.AssetStatus;
import com.tinqin.cms.enums.AssetType;
import com.tinqin.cms.operations.CreateNewAssetOperation;
import com.tinqin.cms.repositories.AssetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateNewAssetOperationProcessor implements CreateNewAssetOperation {
    private final AssetRepository assetRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public CreateNewAssetResponse process(final CreateNewAssetRequest request) {
        log.info("Processing create new asset request: {}", request);

        String name = request.getName();
        String description = request.getDescription();
        String serialNumber = request.getSerialNumber();
        String assetType = request.getAssetType();

        log.debug("Creating asset with name: {}, description: {}, serial number: {}", name, description, serialNumber);

        Asset asset = Asset.builder()
                .description(description)
                .serialNumber(serialNumber)
                .name(name)
                .assetStatus(AssetStatus.AVAILABLE)
                .assetType(AssetType.valueOf(assetType))
                .build();

        Asset persistedAsset = assetRepository.save(asset);

        log.info("Asset created successfully with ID: {}", persistedAsset.getId());

        kafkaTemplate.send("ASSET-SERVICE", "Successfully assigned an asset to employee.");

        return CreateNewAssetResponse.builder()
                .id(String.valueOf(persistedAsset.getId()))
                .name(persistedAsset.getName())
                .serialNumber(persistedAsset.getSerialNumber())
                .description(persistedAsset.getDescription())
                .assetStatus(String.valueOf(persistedAsset.getAssetStatus()))
                .assetType(String.valueOf(persistedAsset.getAssetType()))
                .build();
    }
}
