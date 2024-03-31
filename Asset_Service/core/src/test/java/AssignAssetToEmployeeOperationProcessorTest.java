import static com.tinqin.cms.operations.AssignAssetToEmployeeOperation.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.tinqin.cms.entities.Asset;
import com.tinqin.cms.enums.AssetStatus;
import com.tinqin.cms.enums.AssetType;
import com.tinqin.cms.exceptions.AssetNotFoundException;
import com.tinqin.cms.operations.AssignAssetToEmployeeOperation;
import com.tinqin.cms.processors.AssignAssetToEmployeeOperationProcessor;
import com.tinqin.cms.repositories.AssetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class AssignAssetToEmployeeOperationProcessorTest {
    @Mock
    private AssetRepository assetRepository;

    @InjectMocks
    private AssignAssetToEmployeeOperationProcessor processor;

    @Test
    void testProcess_AssignAssetToEmployee() {
        UUID assetId = UUID.randomUUID();
        UUID employeeId = UUID.randomUUID();

        AssignAssetToEmployeeRequest request = AssignAssetToEmployeeRequest.builder()
                .employeeId(employeeId.toString())
                .assetId(assetId.toString())
                .build();

        Asset asset = Asset.builder()
                .id(assetId)
                .employeeId(null)
                .name("Test Asset")
                .description("Test Description")
                .serialNumber("12345")
                .assetType(AssetType.PHONE)
                .assetStatus(AssetStatus.AVAILABLE)
                .build();

        when(assetRepository.findById(assetId)).thenReturn(Optional.of(asset));
        when(assetRepository.save(any(Asset.class))).thenReturn(asset);

        AssignAssetToEmployeeResponse response = processor.process(request);

        verify(assetRepository).findById(assetId);
        verify(assetRepository).save(asset);

        assertNotNull(response);
        assertEquals(assetId.toString(), response.getId());
        assertEquals(employeeId.toString(), response.getEmployeeId());
        assertEquals("Test Asset", response.getName());
        assertEquals("Test Description", response.getDescription());
        assertEquals("12345", response.getSerialNumber());
        assertEquals("PHONE", response.getAssetType());
        assertEquals("ASSIGNED", response.getAssetStatus());
    }

    @Test
    void testProcess_AssignAssetToEmployee_NotFound() {
        UUID assetId = UUID.randomUUID();
        UUID employeeId = UUID.randomUUID();

        AssignAssetToEmployeeRequest request = AssignAssetToEmployeeRequest.builder()
                .employeeId(employeeId.toString())
                .assetId(assetId.toString())
                .build();

        when(assetRepository.findById(assetId)).thenReturn(Optional.empty());

        assertThrows(AssetNotFoundException.class, () -> processor.process(request));

        verify(assetRepository).findById(assetId);
        verifyNoMoreInteractions(assetRepository);
    }

    @Test
    void testProcess_AssignAssetToEmployee_NullRequest() {
        assertThrows(NullPointerException.class, () -> processor.process(null));

        verifyNoInteractions(assetRepository);
    }

    @Test
    void testProcess_AssignAssetToEmployee_SaveError() {
        UUID assetId = UUID.randomUUID();
        UUID employeeId = UUID.randomUUID();

        AssignAssetToEmployeeRequest request = AssignAssetToEmployeeRequest.builder()
                .employeeId(employeeId.toString())
                .assetId(assetId.toString())
                .build();

        Asset asset = Asset.builder()
                .id(assetId)
                .employeeId(null)
                .name("Test Asset")
                .description("Test Description")
                .serialNumber("12345")
                .assetType(AssetType.PHONE)
                .assetStatus(AssetStatus.AVAILABLE)
                .build();

        when(assetRepository.findById(assetId)).thenReturn(Optional.of(asset));
        when(assetRepository.save(any(Asset.class))).thenThrow(new RuntimeException("Failed to save asset"));

        assertThrows(RuntimeException.class, () -> processor.process(request));

        verify(assetRepository).findById(assetId);
        verify(assetRepository).save(asset);
    }
}
