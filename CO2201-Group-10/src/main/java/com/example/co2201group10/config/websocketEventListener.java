package com.example.co2201group10.config;

import com.example.co2201group10.model.ChatMessage;
import com.example.co2201group10.model.MessageType;
import com.example.co2201group10.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class websocketEventListener {
    @Autowired
    private UserRepo userRepo;
    private final SimpMessageSendingOperations messageTemplate;

    public websocketEventListener(SimpMessageSendingOperations messageTemplateIN) {
        this.messageTemplate=messageTemplateIN;
    }

    @EventListener
    public void handleWebsocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        // get the username of leaving chatroom member
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if (username != null) {
            // compile to local ChatMessage object
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(MessageType.LEAVE);
            chatMessage.setSender(username);

            // alert chatroom that the user has left
            messageTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
