package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

public interface SendSMSOperation
        extends OperationProcessor<SendSMSOperation.SendSMSOperationResponse, SendSMSOperation.SendSMSOperationRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class SendSMSOperationRequest implements OperationInput {
        private String toPhoneNumber;
        private String fromPhoneNumber;
        private String message;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class SendSMSOperationResponse implements OperationOutput {
        private Boolean isSentSuccessfully;
    }
}
