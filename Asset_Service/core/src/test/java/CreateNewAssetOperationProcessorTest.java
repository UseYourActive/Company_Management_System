import static com.tinqin.cms.operations.CreateNewAssetOperation.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.tinqin.cms.entities.Asset;
import com.tinqin.cms.enums.AssetStatus;
import com.tinqin.cms.enums.AssetType;
import com.tinqin.cms.operations.CreateNewAssetOperation;
import com.tinqin.cms.processors.CreateNewAssetOperationProcessor;
import com.tinqin.cms.repositories.AssetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class CreateNewAssetOperationProcessorTest {

    @Mock
    private AssetRepository assetRepository;

    @InjectMocks
    private CreateNewAssetOperationProcessor processor;

    @Test
    void testProcess_CreateNewAsset() {
        String name = "Test Asset";
        String description = "Test Description";
        String serialNumber = "12345";
        String assetType = "PHONE";

        CreateNewAssetRequest request = CreateNewAssetRequest.builder()
                .name(name)
                .description(description)
                .serialNumber(serialNumber)
                .assetType(assetType)
                .build();

        Asset asset = Asset.builder()
                .id(UUID.randomUUID())
                .name(name)
                .description(description)
                .serialNumber(serialNumber)
                .assetStatus(AssetStatus.AVAILABLE)
                .assetType(AssetType.PHONE)
                .build();

        when(assetRepository.save(any(Asset.class))).thenReturn(asset);

        CreateNewAssetResponse response = processor.process(request);

        verify(assetRepository).save(any(Asset.class));

        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals(name, response.getName());
        assertEquals(description, response.getDescription());
        assertEquals(serialNumber, response.getSerialNumber());
        assertEquals("AVAILABLE", response.getAssetStatus());
        assertEquals("PHONE", response.getAssetType());
    }

    @Test
    void testProcess_CreateNewAsset_Success() {
        String name = "Test Asset";
        String description = "Test Description";
        String serialNumber = "12345";
        String assetType = "PHONE";

        CreateNewAssetRequest request = CreateNewAssetRequest.builder()
                .name(name)
                .description(description)
                .serialNumber(serialNumber)
                .assetType(assetType)
                .build();

        Asset asset = Asset.builder()
                .id(UUID.randomUUID())
                .name(name)
                .description(description)
                .serialNumber(serialNumber)
                .assetStatus(AssetStatus.AVAILABLE)
                .assetType(AssetType.PHONE)
                .build();

        when(assetRepository.save(any(Asset.class))).thenReturn(asset);

        CreateNewAssetResponse response = processor.process(request);

        verify(assetRepository).save(any(Asset.class));

        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals(name, response.getName());
        assertEquals(description, response.getDescription());
        assertEquals(serialNumber, response.getSerialNumber());
        assertEquals("AVAILABLE", response.getAssetStatus());
        assertEquals("PHONE", response.getAssetType());
    }

    @Test
    void testProcess_CreateNewAsset_NullRequest() {
        assertThrows(NullPointerException.class, () -> processor.process(null));

        verifyNoInteractions(assetRepository);
    }

    @Test
    void testProcess_CreateNewAsset_SaveError() {
        String name = "Test Asset";
        String description = "Test Description";
        String serialNumber = "12345";
        String assetType = "PHONE";

        CreateNewAssetRequest request = CreateNewAssetRequest.builder()
                .name(name)
                .description(description)
                .serialNumber(serialNumber)
                .assetType(assetType)
                .build();

        when(assetRepository.save(any(Asset.class))).thenThrow(new RuntimeException("Failed to save asset"));

        assertThrows(RuntimeException.class, () -> processor.process(request));

        verify(assetRepository).save(any(Asset.class));
    }
}
