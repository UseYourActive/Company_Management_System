import com.tinqin.cms.entities.Token;
import com.tinqin.cms.entities.User;
import com.tinqin.cms.exceptions.CurrentPasswordInvalidException;
import com.tinqin.cms.operations.ChangeUserPasswordOperation;
import com.tinqin.cms.processors.ChangeUserPasswordOperationProcessor;
import com.tinqin.cms.repositories.TokenRepository;
import com.tinqin.cms.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ChangeUserPasswordOperationProcessorTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    private ChangeUserPasswordOperationProcessor processor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
}
