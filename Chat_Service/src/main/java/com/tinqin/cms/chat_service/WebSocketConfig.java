package com.tinqin.cms.chat_service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;
import org.java_websocket.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

@Slf4j
@Configuration
public class WebSocketConfig {
    @Value("${websocket.server.port}")
    private int websocketPort;

    private WebSocketServer webSocketServer;

    @PostConstruct
    public void startWebSocketServer() {
        webSocketServer = new WebSocketServer(new InetSocketAddress(websocketPort)) {
            @Override
            public void onOpen(org.java_websocket.WebSocket conn, org.java_websocket.handshake.ClientHandshake handshake) {
                // New client connection
                log.info("New client connected: " + conn.getRemoteSocketAddress());
            }

            @Override
            public void onClose(org.java_websocket.WebSocket conn, int code, String reason, boolean remote) {
                // Client disconnected
                log.info("Client disconnected: " + conn.getRemoteSocketAddress());
            }

            @Override
            public void onMessage(org.java_websocket.WebSocket conn, String message) {
                // Received message from client
                log.info("Received message from client " + conn.getRemoteSocketAddress() + ": " + message);
                broadcast(message);
            }

            @Override
            public void onError(org.java_websocket.WebSocket conn, Exception ex) {
                // Error handling
                log.error("An error occurred on connection " + conn.getRemoteSocketAddress() + ": " + ex);
            }

            @Override
            public void onStart() {
                // WebSocket server started
                log.info("WebSocket server started on port " + websocketPort);
            }
        };
        webSocketServer.start();
    }

    @PreDestroy
    public void stopWebSocketServer() throws InterruptedException {
        if (webSocketServer != null) {
            webSocketServer.stop();
            log.warn("WebSocket server stopped");
        }
    }

    private void broadcast(String message) {
        // Broadcast message to all connected clients
        for (WebSocket conn : webSocketServer.getConnections()) {
            log.info(message);
            conn.send(message);
        }
    }
}
