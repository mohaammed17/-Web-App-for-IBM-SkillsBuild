package com.example.co2201group10.controller;

import com.example.co2201group10.model.Avatar;
import com.example.co2201group10.model.Body;
import com.example.co2201group10.model.ChatMessage;
import com.example.co2201group10.model.User;
import com.example.co2201group10.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatRoomController {

    @Autowired
    private UserRepo userRepo;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/MainRoom")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/MainRoom")
    public ChatMessage addUser (@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // add username to the session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());

        return chatMessage;
    }

    @MessageMapping("/chat.removeUser")
    @SendTo("/topic/MainRoom")
    public ChatMessage removeUser (@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // add username to the session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());

        return chatMessage;
    }

    @RequestMapping("/chatroom")
    public String chatRoom(Model model, OAuth2AuthenticationToken token) {
        model.addAttribute("token", token);
        model.addAttribute("principal", token.getPrincipal());
        model.addAttribute("details", token.getPrincipal().getAttributes());
        model.addAttribute("navitems", WebController.defaultNavItems);

        User user = userRepo.findByUsername(token.getPrincipal().getAttribute("preferred_username"));;
        if (user == null) return "redirect:/";
        model.addAttribute("user", user);

        Avatar avatar = user.getAvatar();
        model.addAttribute("heads", Body.Heads);
        model.addAttribute("headid", avatar.getHeadId());

        model.addAttribute("Uname", token.getPrincipal().getAttributes().get("name").toString().trim());
        return "app/chatroom";
    }

}
