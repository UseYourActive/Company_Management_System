package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Asset;
import com.tinqin.cms.enums.AssetStatus;
import com.tinqin.cms.exceptions.AssetNotFoundException;
import com.tinqin.cms.operations.AssignAssetToEmployeeOperation;
import com.tinqin.cms.repositories.AssetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssignAssetToEmployeeOperationProcessor implements AssignAssetToEmployeeOperation {
    private final AssetRepository assetRepository;

    @Override
    public AssignAssetToEmployeeResponse process(final AssignAssetToEmployeeRequest request) {
        String employeeId = request.getEmployeeId();
        String assetId = request.getAssetId();

        Asset asset = assetRepository.findById(UUID.fromString(assetId))
                .orElseThrow(AssetNotFoundException::new);

        asset.setEmployeeId(UUID.fromString(employeeId));
        asset.setAssetStatus(AssetStatus.ASSIGNED);

        Asset persistedAsset = assetRepository.save(asset);

        return AssignAssetToEmployeeResponse.builder()
                .id(String.valueOf(persistedAsset.getId()))
                .employeeId(String.valueOf(persistedAsset.getEmployeeId()))
                .description(persistedAsset.getDescription())
                .assetType(String.valueOf(persistedAsset.getAssetType()))
                .assetStatus(String.valueOf(persistedAsset.getAssetStatus()))
                .name(persistedAsset.getName())
                .serialNumber(persistedAsset.getSerialNumber())
                .build();
    }
}
