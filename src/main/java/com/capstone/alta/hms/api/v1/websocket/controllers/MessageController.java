package com.capstone.alta.hms.api.v1.websocket.controllers;

import com.capstone.alta.hms.api.v1.websocket.dto.MessageDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
  @MessageMapping("/notification/sendMessage")
  @SendTo("/topic/messages")
  public MessageDTO sendMessage(@Payload MessageDTO message) {
    return message;
  }
}
