import static com.tinqin.cms.operations.EditAssetOperation.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.tinqin.cms.entities.Asset;
import com.tinqin.cms.enums.AssetStatus;
import com.tinqin.cms.enums.AssetType;
import com.tinqin.cms.exceptions.AssetNotFoundException;
import com.tinqin.cms.operations.EditAssetOperation;
import com.tinqin.cms.processors.EditAssetOperationProcessor;
import com.tinqin.cms.repositories.AssetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class EditAssetOperationProcessorTest {

    @Mock
    private AssetRepository assetRepository;

    @InjectMocks
    private EditAssetOperationProcessor processor;

    @Test
    void testProcess_EditAsset() {
        String id = UUID.randomUUID().toString();
        String newName = "New Asset Name";
        String newDescription = "New Asset Description";
        String newSerialNumber = "New Serial Number";
        String newAssetType = "PHONE";
        String newAssetStatus = "AVAILABLE";

        EditAssetRequest request = EditAssetRequest.builder()
                .id(id)
                .name(newName)
                .description(newDescription)
                .serialNumber(newSerialNumber)
                .assetType(newAssetType)
                .assetStatus(newAssetStatus)
                .build();

        Asset existingAsset = Asset.builder()
                .id(UUID.fromString(id))
                .name("Old Asset Name")
                .description("Old Asset Description")
                .serialNumber("Old Serial Number")
                .assetType(AssetType.PHONE)
                .assetStatus(AssetStatus.AVAILABLE)
                .build();

        when(assetRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(existingAsset));
        when(assetRepository.save(any(Asset.class))).thenAnswer(invocation -> invocation.getArgument(0));

        EditAssetResponse response = processor.process(request);

        verify(assetRepository).findById(UUID.fromString(id));
        verify(assetRepository).save(any(Asset.class));

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals(newName, response.getName());
        assertEquals(newDescription, response.getDescription());
        assertEquals(newSerialNumber, response.getSerialNumber());
        assertEquals(newAssetType, response.getAssetType());
        assertEquals(newAssetStatus, response.getAssetStatus());
    }

    @Test
    void testProcess_EditAsset_NotFound() {
        String id = UUID.randomUUID().toString();
        EditAssetRequest request = EditAssetRequest.builder()
                .id(id)
                .build();

        when(assetRepository.findById(UUID.fromString(id))).thenReturn(Optional.empty());

        assertThrows(AssetNotFoundException.class, () -> processor.process(request));

        verify(assetRepository).findById(UUID.fromString(id));
        verifyNoMoreInteractions(assetRepository);
    }

    @Test
    void testProcess_EditAsset_NullRequest() {
        assertThrows(NullPointerException.class, () -> processor.process(null));

        verifyNoInteractions(assetRepository);
    }

    @Test
    void testProcess_EditAsset_UpdateDescription() {
        String id = UUID.randomUUID().toString();
        String newDescription = "New Asset Description";

        EditAssetRequest request = EditAssetRequest.builder()
                .id(id)
                .description(newDescription)
                .build();

        Asset existingAsset = Asset.builder()
                .id(UUID.fromString(id))
                .description("Old Asset Description")
                .build();

        when(assetRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(existingAsset));
        when(assetRepository.save(any(Asset.class))).thenAnswer(invocation -> invocation.getArgument(0));

        EditAssetResponse response = processor.process(request);

        verify(assetRepository).findById(UUID.fromString(id));
        verify(assetRepository).save(any(Asset.class));

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals(newDescription, response.getDescription());
    }

    @Test
    void testProcess_EditAsset_UpdateName() {
        String id = UUID.randomUUID().toString();
        String newName = "New Asset Name";

        EditAssetRequest request = EditAssetRequest.builder()
                .id(id)
                .name(newName)
                .build();

        Asset existingAsset = Asset.builder()
                .id(UUID.fromString(id))
                .name("Old Asset Name")
                .build();

        when(assetRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(existingAsset));
        when(assetRepository.save(any(Asset.class))).thenAnswer(invocation -> invocation.getArgument(0));

        EditAssetResponse response = processor.process(request);

        verify(assetRepository).findById(UUID.fromString(id));
        verify(assetRepository).save(any(Asset.class));

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals(newName, response.getName());
    }

    @Test
    void testProcess_EditAsset_UpdateSerialNumber() {
        String id = UUID.randomUUID().toString();
        String newSerialNumber = "New Serial Number";

        EditAssetRequest request = EditAssetRequest.builder()
                .id(id)
                .serialNumber(newSerialNumber)
                .build();

        Asset existingAsset = Asset.builder()
                .id(UUID.fromString(id))
                .serialNumber("Old Serial Number")
                .build();

        when(assetRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(existingAsset));
        when(assetRepository.save(any(Asset.class))).thenAnswer(invocation -> invocation.getArgument(0));

        EditAssetResponse response = processor.process(request);

        verify(assetRepository).findById(UUID.fromString(id));
        verify(assetRepository).save(any(Asset.class));

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals(newSerialNumber, response.getSerialNumber());
    }

    @Test
    void testProcess_EditAsset_UpdateAssetType() {
        String id = UUID.randomUUID().toString();
        String newAssetType = "COMPUTER";

        EditAssetRequest request = EditAssetRequest.builder()
                .id(id)
                .assetType(newAssetType)
                .build();

        Asset existingAsset = Asset.builder()
                .id(UUID.fromString(id))
                .assetType(AssetType.PHONE)
                .build();

        when(assetRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(existingAsset));
        when(assetRepository.save(any(Asset.class))).thenAnswer(invocation -> invocation.getArgument(0));

        EditAssetResponse response = processor.process(request);

        verify(assetRepository).findById(UUID.fromString(id));
        verify(assetRepository).save(any(Asset.class));

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals(newAssetType, response.getAssetType());
    }

    @Test
    void testProcess_EditAsset_UpdateAssetStatus() {
        String id = UUID.randomUUID().toString();
        String newAssetStatus = "MAINTENANCE";

        EditAssetRequest request = EditAssetRequest.builder()
                .id(id)
                .assetStatus(newAssetStatus)
                .build();

        Asset existingAsset = Asset.builder()
                .id(UUID.fromString(id))
                .assetStatus(AssetStatus.AVAILABLE)
                .build();

        when(assetRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(existingAsset));
        when(assetRepository.save(any(Asset.class))).thenAnswer(invocation -> invocation.getArgument(0));

        EditAssetResponse response = processor.process(request);

        verify(assetRepository).findById(UUID.fromString(id));
        verify(assetRepository).save(any(Asset.class));

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals(newAssetStatus, response.getAssetStatus());
    }

    @Test
    void testProcess_EditAsset_UpdateMultipleFields() {
        String id = UUID.randomUUID().toString();
        String newName = "New Asset Name";
        String newDescription = "New Asset Description";
        String newSerialNumber = "New Serial Number";
        String newAssetType = "COMPUTER";
        String newAssetStatus = "MAINTENANCE";

        EditAssetRequest request = EditAssetRequest.builder()
                .id(id)
                .name(newName)
                .description(newDescription)
                .serialNumber(newSerialNumber)
                .assetType(newAssetType)
                .assetStatus(newAssetStatus)
                .build();

        Asset existingAsset = Asset.builder()
                .id(UUID.fromString(id))
                .name("Old Asset Name")
                .description("Old Asset Description")
                .serialNumber("Old Serial Number")
                .assetType(AssetType.PHONE)
                .assetStatus(AssetStatus.AVAILABLE)
                .build();

        when(assetRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(existingAsset));
        when(assetRepository.save(any(Asset.class))).thenAnswer(invocation -> invocation.getArgument(0));

        EditAssetResponse response = processor.process(request);

        verify(assetRepository).findById(UUID.fromString(id));
        verify(assetRepository).save(any(Asset.class));

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals(newName, response.getName());
        assertEquals(newDescription, response.getDescription());
        assertEquals(newSerialNumber, response.getSerialNumber());
        assertEquals(newAssetType, response.getAssetType());
        assertEquals(newAssetStatus, response.getAssetStatus());
    }

    @Test
    void testProcess_EditAsset_NoUpdates() {
        String id = UUID.randomUUID().toString();

        EditAssetRequest request = EditAssetRequest.builder()
                .id(id)
                .build();

        Asset existingAsset = Asset.builder()
                .id(UUID.fromString(id))
                .name("Old Asset Name")
                .description("Old Asset Description")
                .serialNumber("Old Serial Number")
                .assetType(AssetType.PHONE)
                .assetStatus(AssetStatus.AVAILABLE)
                .build();

        when(assetRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(existingAsset));
        when(assetRepository.save(any(Asset.class))).thenAnswer(invocation -> invocation.getArgument(0));

        EditAssetResponse response = processor.process(request);

        verify(assetRepository).findById(UUID.fromString(id));
        verify(assetRepository).save(existingAsset);
        verifyNoMoreInteractions(assetRepository);

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals(existingAsset.getName(), response.getName());
        assertEquals(existingAsset.getDescription(), response.getDescription());
        assertEquals(existingAsset.getSerialNumber(), response.getSerialNumber());
        assertEquals(existingAsset.getAssetType().name(), response.getAssetType());
        assertEquals(existingAsset.getAssetStatus().name(), response.getAssetStatus());
    }

    @Test
    void testProcess_EditAsset_UpdateNameAndDescription() {
        String id = UUID.randomUUID().toString();
        String newName = "New Asset Name";
        String newDescription = "New Asset Description";

        EditAssetRequest request = EditAssetRequest.builder()
                .id(id)
                .name(newName)
                .description(newDescription)
                .build();

        Asset existingAsset = Asset.builder()
                .id(UUID.fromString(id))
                .name("Old Asset Name")
                .description("Old Asset Description")
                .assetType(AssetType.PHONE)
                .assetStatus(AssetStatus.AVAILABLE)
                .build();

        when(assetRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(existingAsset));
        when(assetRepository.save(any(Asset.class))).thenAnswer(invocation -> invocation.getArgument(0));

        EditAssetResponse response = processor.process(request);

        verify(assetRepository).findById(UUID.fromString(id));
        verify(assetRepository).save(any(Asset.class));

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals(newName, response.getName());
        assertEquals(newDescription, response.getDescription());
        assertEquals(existingAsset.getSerialNumber(), response.getSerialNumber());
        assertEquals(existingAsset.getAssetType().name(), response.getAssetType());
        assertEquals(existingAsset.getAssetStatus().name(), response.getAssetStatus());
    }


    @Test
    void testProcess_EditAsset_UpdateAssetTypeAndStatus() {
        String id = UUID.randomUUID().toString();
        String newAssetType = "COMPUTER";
        String newAssetStatus = "MAINTENANCE";

        EditAssetRequest request = EditAssetRequest.builder()
                .id(id)
                .assetType(newAssetType)
                .assetStatus(newAssetStatus)
                .build();

        Asset existingAsset = Asset.builder()
                .id(UUID.fromString(id))
                .assetType(AssetType.PHONE)
                .assetStatus(AssetStatus.AVAILABLE)
                .build();

        when(assetRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(existingAsset));
        when(assetRepository.save(any(Asset.class))).thenAnswer(invocation -> invocation.getArgument(0));

        EditAssetResponse response = processor.process(request);

        verify(assetRepository).findById(UUID.fromString(id));
        verify(assetRepository).save(any(Asset.class));

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals(existingAsset.getName(), response.getName());
        assertEquals(existingAsset.getDescription(), response.getDescription());
        assertEquals(existingAsset.getSerialNumber(), response.getSerialNumber());
        assertEquals(newAssetType, response.getAssetType());
        assertEquals(newAssetStatus, response.getAssetStatus());
    }

    @Test
    void testProcess_EditAsset_EmptyNameAndDescription() {
        // Mock data
        String id = UUID.randomUUID().toString();
        String emptyName = "";
        String emptyDescription = "";

        EditAssetRequest request = EditAssetRequest.builder()
                .id(id)
                .name(emptyName)
                .description(emptyDescription)
                .build();

        Asset existingAsset = Asset.builder()
                .id(UUID.fromString(id))
                .name("Old Asset Name")
                .description("Old Asset Description")
                .assetType(AssetType.PHONE)
                .assetStatus(AssetStatus.AVAILABLE)
                .build();

        when(assetRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(existingAsset));
        when(assetRepository.save(any(Asset.class))).thenAnswer(invocation -> invocation.getArgument(0));

        EditAssetResponse response = processor.process(request);

        verify(assetRepository).findById(UUID.fromString(id));
        verify(assetRepository).save(any(Asset.class));

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals(emptyName, response.getName());
        assertEquals(emptyDescription, response.getDescription());
        assertEquals(existingAsset.getSerialNumber(), response.getSerialNumber());
        assertEquals(existingAsset.getAssetType().name(), response.getAssetType());
        assertEquals(existingAsset.getAssetStatus().name(), response.getAssetStatus());
    }
}
