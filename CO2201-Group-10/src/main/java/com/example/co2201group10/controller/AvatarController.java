package com.example.co2201group10.controller;

import com.example.co2201group10.model.Avatar;
import com.example.co2201group10.model.Body;
import com.example.co2201group10.model.User;
import com.example.co2201group10.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AvatarController {

    @Autowired
    UserRepo userRepo;

    @RequestMapping("/avatar-creation")
    public String avatar(Model model, OAuth2AuthenticationToken token) {
        model.addAttribute("navitems", WebController.defaultNavItems);
        model.addAttribute("token", token);
        model.addAttribute("principal", token.getPrincipal());
        model.addAttribute("details", token.getPrincipal().getAttributes());
        User user = userRepo.findByUsername(token.getPrincipal().getAttribute("preferred_username"));
        if (user == null) return "redirect:/";
        model.addAttribute("user", user);

        Avatar avatar = user.getAvatar();

        model.addAttribute("headid", avatar.getHeadId());
        model.addAttribute("bodyid", avatar.getBodyId());
        model.addAttribute("legid", avatar.getLegId());

        model.addAttribute("heads", Body.Heads);
        model.addAttribute("bodies", Body.Bodies);
        model.addAttribute("legs", Body.Legs);

        return "app/avatar";
    }

    @RequestMapping("/avatar-updatehead/{direction}")
    public String headUpdate(Model model, @PathVariable String direction, OAuth2AuthenticationToken token) {
        model.addAttribute("navitems", WebController.defaultNavItems);
        model.addAttribute("token", token);
        model.addAttribute("principal", token.getPrincipal());
        model.addAttribute("details", token.getPrincipal().getAttributes());
        User user = userRepo.findByUsername(token.getPrincipal().getAttribute("preferred_username"));
        if (user == null) return "redirect:/";
        model.addAttribute("user", user);

        Avatar avatar = user.getAvatar();

        if (avatar.getHeadId() < Body.Heads.size()-1 && direction.equals("next")) {
            int newHead = avatar.getHeadId() + 1;
            avatar.setHeadId(newHead);
        } else if (avatar.getHeadId() >= Body.Heads.size()-1 && direction.equals("next")) {
            avatar.setHeadId(1); }

        if (avatar.getHeadId() > 1 && direction.equals("back")) {
            int newHead = avatar.getHeadId() - 1;
            avatar.setHeadId(newHead);
        } else if (avatar.getHeadId() <= 1 && direction.equals("back")) {
            avatar.setHeadId(Body.Heads.size()-1);
        }
        userRepo.save(user);
        return "redirect:/avatar-creation";
    }

    @RequestMapping("/avatar-updatebody/{direction}")
    public String bodyUpdate(Model model, @PathVariable String direction, OAuth2AuthenticationToken token) {
        model.addAttribute("navitems", WebController.defaultNavItems);
        model.addAttribute("token", token);
        model.addAttribute("principal", token.getPrincipal());
        model.addAttribute("details", token.getPrincipal().getAttributes());
        User user = userRepo.findByUsername(token.getPrincipal().getAttribute("preferred_username"));
        if (user == null) return "redirect:/";
        model.addAttribute("user", user);

        Avatar avatar = user.getAvatar();

        if (avatar.getBodyId() < Body.Bodies.size()-1 && direction.equals("next")) {
            int newBody = avatar.getBodyId() + 1;
            avatar.setBodyId(newBody);
        } else if (avatar.getBodyId() >= Body.Bodies.size()-1 && direction.equals("next")) {
            avatar.setBodyId(0); }

        if (avatar.getBodyId() > 0 && direction.equals("back")) {
            int newBody = avatar.getBodyId() - 1;
            avatar.setBodyId(newBody);
        } else if (avatar.getBodyId() <= 0 && direction.equals("back")) {
            avatar.setBodyId(Body.Bodies.size()-1);
        }
        userRepo.save(user);

        return "redirect:/avatar-creation";
    }

    @RequestMapping("/avatar-updatelegs/{direction}")
    public String legsUpdate(Model model, @PathVariable String direction, OAuth2AuthenticationToken token) {
        model.addAttribute("navitems", WebController.defaultNavItems);
        model.addAttribute("token", token);
        model.addAttribute("principal", token.getPrincipal());
        model.addAttribute("details", token.getPrincipal().getAttributes());
        User user = userRepo.findByUsername(token.getPrincipal().getAttribute("preferred_username"));
        if (user == null) return "redirect:/";
        model.addAttribute("user", user);

        Avatar avatar = user.getAvatar();

        if (avatar.getLegId() < Body.Legs.size()-1 && direction.equals("next")) {
            int newLegs = avatar.getLegId() + 1;
            avatar.setLegId(newLegs);
        } else if (avatar.getLegId() >= Body.Legs.size()-1 && direction.equals("next")) {
            avatar.setLegId(0); }

        if (avatar.getLegId() > 0 && direction.equals("back")) {
            int newLegs = avatar.getLegId() - 1;
            avatar.setLegId(newLegs);
        } else if (avatar.getLegId() <= 0 && direction.equals("back")) {
            avatar.setLegId(Body.Legs.size()-1);
        }
        userRepo.save(user);
        return "redirect:/avatar-creation";
    }

}
