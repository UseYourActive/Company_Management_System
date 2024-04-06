import com.tinqin.cms.auth.JwtService;
import com.tinqin.cms.entities.User;
import com.tinqin.cms.exceptions.UserNotFoundException;
import com.tinqin.cms.operations.RefreshTokenOperation;
import com.tinqin.cms.processors.RefreshTokenOperationProcessor;
import com.tinqin.cms.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.tinqin.cms.operations.RefreshTokenOperation.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RefreshTokenOperationProcessorTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RefreshTokenOperationProcessor processor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcess_ValidToken() {
        RefreshTokenRequest request = RefreshTokenRequest.builder()
                .token("validToken")
                .build();

        User user = User.builder()
                .username("testUser")
                .build();

        when(jwtService.extractUsername("validToken")).thenReturn("testUser");
        when(userRepository.findUserByUsername("testUser")).thenReturn(Optional.of(user));
        when(jwtService.isValid("validToken", user)).thenReturn(true);
        when(jwtService.generateToken(user)).thenReturn("newToken");

        RefreshTokenResponse response = processor.process(request);

        assertNotNull(response);
        assertEquals("newToken", response.getNewToken());
        assertEquals("validToken", response.getRefreshedToken());
        verify(jwtService).extractUsername("validToken");
        verify(userRepository).findUserByUsername("testUser");
        verify(jwtService).isValid("validToken", user);
        verify(jwtService).generateToken(user);
    }

    @Test
    void testProcess_UserNotFound() {
        RefreshTokenRequest request = RefreshTokenRequest.builder()
                .token("tokenForNonExistingUser")
                .build();

        when(jwtService.extractUsername("tokenForNonExistingUser")).thenReturn("nonExistingUser");
        when(userRepository.findUserByUsername("nonExistingUser")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> processor.process(request));
        verify(jwtService).extractUsername("tokenForNonExistingUser");
        verify(userRepository).findUserByUsername("nonExistingUser");
        verifyNoMoreInteractions(jwtService);
    }
}
