import com.tinqin.cms.operations.SendEmailOperation;
import com.tinqin.cms.processors.SendEmailOperationProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static com.tinqin.cms.operations.SendEmailOperation.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class SendEmailOperationProcessorTest {

    @Mock
    private JavaMailSender emailSender;

    @InjectMocks
    private SendEmailOperationProcessor processor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcess_EmailSentSuccessfully() {
        SendEmailOperationRequest request =
                SendEmailOperationRequest.builder()
                        .to("recipient@example.com")
                        .subject("Test Subject")
                        .text("Test Body")
                        .build();

        SendEmailOperationResponse response = processor.process(request);

        verify(emailSender).send(any(SimpleMailMessage.class));

        assertTrue(response.getIsSentSuccessfully());
    }

}
