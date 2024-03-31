package com.tinqin.cms.processors;

import com.tinqin.cms.operations.SendSMSOperation;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendSMSOperationProcessor implements SendSMSOperation {
    @Value("${twilio.ACCOUNT_SID}")
    private String ACCOUNT_SID;

    @Value("${twilio.AUTH_TOKEN}")
    private String AUTH_TOKEN;

    @Override
    public SendSMSOperationResponse process(final SendSMSOperationRequest request) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String fromNumber = request.getFromPhoneNumber();
        String toNumber = request.getToPhoneNumber();
        String msg = request.getMessage();

        Message.creator(new PhoneNumber(toNumber), new PhoneNumber(fromNumber),
                msg).create();

        return SendSMSOperationResponse.builder()
                .isSentSuccessfully(Boolean.TRUE)
                .build();
    }
}
