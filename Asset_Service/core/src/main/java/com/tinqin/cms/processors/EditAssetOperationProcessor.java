package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Asset;
import com.tinqin.cms.enums.AssetStatus;
import com.tinqin.cms.enums.AssetType;
import com.tinqin.cms.exceptions.AssetNotFoundException;
import com.tinqin.cms.operations.EditAssetOperation;
import com.tinqin.cms.repositories.AssetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EditAssetOperationProcessor implements EditAssetOperation {
    private final AssetRepository assetRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public EditAssetResponse process(final EditAssetRequest request) {
        log.info("Processing edit asset request: {}", request);

        String id = request.getId();
        String name = request.getName();
        String description = request.getDescription();
        String serialNumber = request.getSerialNumber();
        String assetStatus = request.getAssetStatus();
        String assetType = request.getAssetType();

        Optional<Asset> optionalAsset = assetRepository.findById(UUID.fromString(id));
        Asset asset = optionalAsset.orElseThrow(AssetNotFoundException::new);

        optionalAsset.ifPresent(a -> {
            Optional.ofNullable(description).ifPresent(desc -> {
                log.debug("Updating description for asset with ID {}: {}", id, desc);
                a.setDescription(desc);
            });

            Optional.ofNullable(name).ifPresent(n -> {
                log.debug("Updating name for asset with ID {}: {}", id, n);
                a.setName(n);
            });

            Optional.ofNullable(serialNumber).ifPresent(sn -> {
                log.debug("Updating serial number for asset with ID {}: {}", id, sn);
                a.setSerialNumber(sn);
            });

            Optional.ofNullable(assetStatus).ifPresent(n -> {
                log.debug("Updating status for asset with ID {}: {}", id, n);
                a.setAssetStatus(AssetStatus.valueOf(n));
            });

            Optional.ofNullable(assetType).ifPresent(n -> {
                log.debug("Updating type for asset with ID {}: {}", id, n);
                a.setAssetType(AssetType.valueOf(n));
            });
        });

        Asset persistedAsset = assetRepository.save(asset);

        log.info("Asset updated successfully: {}", persistedAsset.getId());

        kafkaTemplate.send("ASSET-SERVICE", "Successfully edited an asset.");

        return EditAssetResponse.builder()
                .id(String.valueOf(persistedAsset.getId()))
                .name(persistedAsset.getName())
                .serialNumber(persistedAsset.getSerialNumber())
                .description(persistedAsset.getDescription())
                .assetStatus(String.valueOf(persistedAsset.getAssetStatus()))
                .assetType(String.valueOf(persistedAsset.getAssetType()))
                .build();
    }
}
