package com.example.co2201group10.controller;

import com.example.co2201group10.model.Avatar;
import com.example.co2201group10.model.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.co2201group10.model.User;
import com.example.co2201group10.repository.UserRepo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Controller
public class LeaderboardController {

    @Autowired
    private UserRepo userRepo;

    @RequestMapping("/leaderboard")
    public String leaderboard(Model model, OAuth2AuthenticationToken token) {
        model.addAttribute("token", token);
        model.addAttribute("principal", token.getPrincipal());
        model.addAttribute("details", token.getPrincipal().getAttributes());
        model.addAttribute("navitems", WebController.defaultNavItems);

        model.addAttribute("details", token.getPrincipal().getAttributes());
        model.addAttribute("principal", token.getPrincipal());
        User user = userRepo.findByUsername(token.getPrincipal().getAttribute("preferred_username"));
        if (user == null) return "redirect:/";

        Avatar avatar = user.getAvatar();
        model.addAttribute("heads", Body.Heads);
        model.addAttribute("headid", avatar.getHeadId());

        model.addAttribute("user", user);
        model.addAttribute("users", userRepo.findAll(Sort.by(Sort.Direction.DESC, "progressionHandler_currentExp")));
        model.addAttribute("isFriends", false);

        return "app/leaderboard";
    }

    @RequestMapping("/leaderboard/friends")
    public String friendsLeaderboard(Model model, OAuth2AuthenticationToken token) {
        model.addAttribute("token", token);
        model.addAttribute("principal", token.getPrincipal());
        model.addAttribute("details", token.getPrincipal().getAttributes());
        model.addAttribute("navitems", WebController.defaultNavItems);

        User user = userRepo.findByUsername(token.getPrincipal().getAttribute("preferred_username"));
        if (user == null) return "redirect:/";

        Avatar avatar = user.getAvatar();
        model.addAttribute("heads", Body.Heads);
        model.addAttribute("headid", avatar.getHeadId());

        List<User> friends = user.getFriends();
        friends.add(user);
        friends.sort((a, b) -> b.getProgressionHandler().getCurrentLevel() - a.getProgressionHandler().getCurrentLevel());

        model.addAttribute("details", token.getPrincipal().getAttributes());
        model.addAttribute("principal", token.getPrincipal());
        model.addAttribute("user", user);
        model.addAttribute("users", friends);
        model.addAttribute("isFriends", true);

        return "app/leaderboard";
    }
}