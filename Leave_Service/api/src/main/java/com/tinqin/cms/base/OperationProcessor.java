package com.tinqin.cms.base;

public interface OperationProcessor<Response extends OperationOutput, Request extends OperationInput> {
    Response process(Request request);
}
