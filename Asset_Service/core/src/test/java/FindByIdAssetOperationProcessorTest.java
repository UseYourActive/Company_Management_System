import com.tinqin.cms.entities.Asset;
import com.tinqin.cms.enums.AssetStatus;
import com.tinqin.cms.enums.AssetType;
import com.tinqin.cms.exceptions.AssetNotFoundException;
import com.tinqin.cms.operations.FindByIdAssetOperation;
import com.tinqin.cms.operations.FindByIdAssetOperation.FindByIdAssetRequest;
import com.tinqin.cms.operations.FindByIdAssetOperation.FindByIdAssetResponse;
import com.tinqin.cms.processors.FindByIdAssetOperationProcessor;
import com.tinqin.cms.repositories.AssetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FindByIdAssetOperationProcessorTest {

    @Mock
    private AssetRepository assetRepository;

    @InjectMocks
    private FindByIdAssetOperationProcessor processor;

    @Test
    public void testProcess_FindByIdAsset() {
        String assetId = UUID.randomUUID().toString();
        FindByIdAssetRequest request = new FindByIdAssetRequest(assetId);

        Asset asset = Asset.builder()
                .id(UUID.fromString(assetId))
                .name("Test Asset")
                .description("Test Description")
                .serialNumber("12345")
                .assetType(AssetType.PHONE)
                .assetStatus(AssetStatus.AVAILABLE)
                .build();

        when(assetRepository.findById(UUID.fromString(assetId)))
                .thenReturn(Optional.of(asset));

        FindByIdAssetResponse response = processor.process(request);

        assertEquals(assetId, response.getId());
        assertEquals("Test Asset", response.getName());
        assertEquals("Test Description", response.getDescription());
        assertEquals("12345", response.getSerialNumber());
        assertEquals("PHONE", response.getAssetType());
        assertEquals("AVAILABLE", response.getAssetStatus());

        verify(assetRepository, times(1)).findById(UUID.fromString(assetId));
        verifyNoMoreInteractions(assetRepository);
    }

    @Test
    public void testProcess_FindByIdAsset_AssetNotFound() {
        String assetId = UUID.randomUUID().toString();
        FindByIdAssetRequest request = new FindByIdAssetRequest(assetId);

        when(assetRepository.findById(UUID.fromString(assetId)))
                .thenReturn(Optional.empty());

        AssetNotFoundException exception = assertThrows(AssetNotFoundException.class, () -> processor.process(request));
        assertEquals("Asset not found", exception.getMessage());

        verify(assetRepository, times(1)).findById(UUID.fromString(assetId));
        verifyNoMoreInteractions(assetRepository);
    }

    @Test
    public void testProcess_FindByIdAsset_AssetFound() {
        String assetId = UUID.randomUUID().toString();
        FindByIdAssetRequest request = new FindByIdAssetRequest(assetId);
        Asset asset = createDummyAsset();

        when(assetRepository.findById(UUID.fromString(assetId))).thenReturn(Optional.of(asset));

        FindByIdAssetResponse response = processor.process(request);

        assertEquals(asset.getId().toString(), response.getId());
        assertEquals(asset.getName(), response.getName());
        assertEquals(asset.getSerialNumber(), response.getSerialNumber());
        assertEquals(asset.getDescription(), response.getDescription());
        assertEquals(asset.getAssetStatus().toString(), response.getAssetStatus());
        assertEquals(asset.getAssetType().toString(), response.getAssetType());

        verify(assetRepository, times(1)).findById(UUID.fromString(assetId));
        verifyNoMoreInteractions(assetRepository);
    }

    private Asset createDummyAsset() {
        Asset asset = new Asset();
        asset.setId(UUID.randomUUID());
        asset.setName("Dummy Asset");
        asset.setDescription("This is a dummy asset for testing");
        asset.setSerialNumber("123456789");
        asset.setAssetType(AssetType.PHONE);
        asset.setAssetStatus(AssetStatus.AVAILABLE);
        return asset;
    }
}
