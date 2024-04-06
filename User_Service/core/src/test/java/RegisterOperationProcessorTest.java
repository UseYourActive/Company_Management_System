import com.tinqin.cms.auth.JwtService;
import com.tinqin.cms.entities.Token;
import com.tinqin.cms.entities.User;
import com.tinqin.cms.enums.Role;
import com.tinqin.cms.enums.TokenType;
import com.tinqin.cms.exceptions.NotMatchingPasswordsException;
import com.tinqin.cms.exceptions.UsernameAlreadyExistsException;
import com.tinqin.cms.operations.RegisterOperation;
import com.tinqin.cms.processors.RegisterOperationProcessor;
import com.tinqin.cms.repositories.TokenRepository;
import com.tinqin.cms.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RegisterOperationProcessorTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private RegisterOperationProcessor processor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcess_UsernameAlreadyExists() {
        RegisterOperation.RegisterRequest request = RegisterOperation.RegisterRequest.builder()
                .username("existingUser")
                .password("password123")
                .repeatedPassword("password123")
                .build();

        when(userRepository.findUserByUsername("existingUser"))
                .thenReturn(Optional.of(new User()));

        assertThrows(UsernameAlreadyExistsException.class, () -> processor.process(request));

        verify(userRepository, times(1)).findUserByUsername("existingUser");
        verify(passwordEncoder, never()).encode(anyString());
        verify(jwtService, never()).generateToken(any(User.class));
        verify(tokenRepository, never()).save(any());
    }

}
