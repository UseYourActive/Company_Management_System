package com.tinqin.cms.processors;

import com.tinqin.cms.operations.SendEmailOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendEmailOperationProcessor implements SendEmailOperation {
    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Override
    public SendEmailOperationResponse process(final SendEmailOperationRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromMail);
        message.setTo(request.getTo());
        message.setSubject(request.getSubject());
        message.setText(request.getText());
        emailSender.send(message);

        return SendEmailOperationResponse.builder()
                .isSentSuccessfully(Boolean.TRUE)
                .build();
    }
}
