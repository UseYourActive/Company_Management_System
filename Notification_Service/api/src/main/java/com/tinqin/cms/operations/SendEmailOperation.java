package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import lombok.*;

public interface SendEmailOperation
        extends OperationProcessor<SendEmailOperation.SendEmailOperationResponse, SendEmailOperation.SendEmailOperationRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class SendEmailOperationRequest implements OperationInput {
        private String to;
        private String subject;
        private String text;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class SendEmailOperationResponse implements OperationOutput {
        private Boolean isSentSuccessfully;
    }
}
