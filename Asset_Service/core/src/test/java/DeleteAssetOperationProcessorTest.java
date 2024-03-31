import static com.tinqin.cms.operations.DeleteAssetOperation.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.tinqin.cms.entities.Asset;
import com.tinqin.cms.exceptions.AssetNotFoundException;
import com.tinqin.cms.operations.DeleteAssetOperation;
import com.tinqin.cms.processors.DeleteAssetOperationProcessor;
import com.tinqin.cms.repositories.AssetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class DeleteAssetOperationProcessorTest {

    @Mock
    private AssetRepository assetRepository;

    @InjectMocks
    private DeleteAssetOperationProcessor processor;

    @Test
    void testProcess_DeleteAsset() {
        String id = UUID.randomUUID().toString();

        DeleteAssetRequest request = DeleteAssetRequest.builder()
                .id(id)
                .build();

        Asset asset = Asset.builder()
                .id(UUID.fromString(id))
                .build();

        when(assetRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(asset));

        DeleteAssetResponse response = processor.process(request);

        verify(assetRepository).findById(UUID.fromString(id));
        verify(assetRepository).delete(asset);

        assertNotNull(response);
        assertTrue(response.getIsSuccessfullyDeleted());
    }

    @Test
    void testProcess_DeleteAsset_NotFound() {
        String id = UUID.randomUUID().toString();

        DeleteAssetRequest request = DeleteAssetRequest.builder()
                .id(id)
                .build();

        when(assetRepository.findById(UUID.fromString(id))).thenReturn(Optional.empty());

        assertThrows(AssetNotFoundException.class, () -> processor.process(request));

        verify(assetRepository).findById(UUID.fromString(id));
        verifyNoMoreInteractions(assetRepository);
    }

    @Test
    void testProcess_DeleteAsset_InvalidId() {
        String invalidId = "invalid_id";

        DeleteAssetRequest request = DeleteAssetRequest.builder()
                .id(invalidId)
                .build();

        assertThrows(IllegalArgumentException.class, () -> processor.process(request));

        verifyNoInteractions(assetRepository);
    }

    @Test
    void testProcess_DeleteAsset_NullRequest() {
        assertThrows(NullPointerException.class, () -> processor.process(null));

        verifyNoInteractions(assetRepository);
    }

    @Test
    void testProcess_DeleteAsset_DeleteError() {
        String id = UUID.randomUUID().toString();

        DeleteAssetRequest request = DeleteAssetRequest.builder()
                .id(id)
                .build();

        Asset asset = Asset.builder()
                .id(UUID.fromString(id))
                .build();

        when(assetRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(asset));
        doThrow(new RuntimeException("Failed to delete asset")).when(assetRepository).delete(asset);

        assertThrows(RuntimeException.class, () -> processor.process(request));

        verify(assetRepository).findById(UUID.fromString(id));
        verify(assetRepository).delete(asset);
    }
}
