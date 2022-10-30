package com.capstone.alta.hms.api.v1.websocket.client;

import com.capstone.alta.hms.api.v1.websocket.listener.StompListener;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;

public class StompClient {
  public static void main(String[] args) {
    WebSocketClient client = new StandardWebSocketClient();
    WebSocketStompClient stompClient = new WebSocketStompClient(client);

    stompClient.setMessageConverter(new MappingJackson2MessageConverter());

    StompListener stompListener = new StompListener();
    String URL = "ws://localhost:8080/notification";

    stompClient.connect(URL, stompListener);

    new Scanner(System.in).nextLine();
  }
}
