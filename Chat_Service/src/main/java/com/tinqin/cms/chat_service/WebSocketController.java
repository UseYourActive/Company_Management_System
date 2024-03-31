package com.tinqin.cms.chat_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class WebSocketController {
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message send(Message message) {
        log.info(message.toString());
        return message;
    }
}
