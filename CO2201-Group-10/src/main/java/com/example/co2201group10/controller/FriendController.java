package com.example.co2201group10.controller;

import com.example.co2201group10.model.Avatar;
import com.example.co2201group10.model.Body;
import com.example.co2201group10.model.User;
import com.example.co2201group10.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FriendController {

    @Autowired
    private UserRepo userRepo;

    /* This method is used to display the friends page. It retrieves the user's friends and friend requests from the user repository and displays them.
     * @param model - the model to be used to display the friends page
     * @param token - the token used to retrieve the user's details
     * @return - the friends page jsp
     * @Author Jakob (jkep1)
     */
    @GetMapping("/friends")
    public String friends(Model model, OAuth2AuthenticationToken token) {
        model.addAttribute("token", token);
        model.addAttribute("principal", token.getPrincipal());
        model.addAttribute("details", token.getPrincipal().getAttributes());
        model.addAttribute("navitems", WebController.defaultNavItems);

        User user = userRepo.findByUsername(token.getPrincipal().getAttribute("preferred_username"));

        if (user == null) return "redirect:/";

        Avatar avatar = user.getAvatar();
        model.addAttribute("heads", Body.Heads);
        model.addAttribute("headid", avatar.getHeadId());

        model.addAttribute("user", user);
        model.addAttribute("friends", user.getFriends());
        model.addAttribute("friendRequestsIncoming", user.getFriendRequestsIncoming());
        model.addAttribute("friendRequestsOutgoing", user.getFriendRequestsOutgoing());

        model.addAttribute("error", null);

        return "app/friends";
    }

    /*  This method is used to accept a friend request. It retrieves the user and friend from the user repository and adds the friend to the user's friends list and removes the friend from the user's friend requests list. It then saves the user and friend to the user repository.
     * @param id - the id of the friend to be accepted
     * @param model - the model to be used to display the friends page
     * @param token - the token used to retrieve the user's details
     * @return - the friends page jsp
     * @Author - Jakob (jkep1)
     */
    @RequestMapping("/acceptFriend")
    public String addFriend(@RequestParam String id, Model model, OAuth2AuthenticationToken token) {
        User user = userRepo.findByUsername(token.getPrincipal().getAttribute("preferred_username"));
        if (user == null) return "redirect:/";
        model.addAttribute("user", user);

        Avatar avatar = user.getAvatar();
        model.addAttribute("heads", Body.Heads);
        model.addAttribute("headid", avatar.getHeadId());

        User friend = userRepo.findById(Long.parseLong(id)).orElse(null);
        if (friend == null) return "redirect:/friends";
        System.out.println("Accepter: " + user.getId() + " Sender: " + friend.getId());

        if (!user.hasFriendRequest(friend)) return "redirect:/friends";
        user.acceptFriendRequest(friend);
        friend.acceptFriendRequest(user);
        userRepo.save(user);
        userRepo.save(friend);

        return "redirect:/friends";
    }

    /* This method is used to send a friend request. It retrieves the user and friend from the user repository and adds the friend to the user's friend requests list and adds the user to the friend's friend requests list. It then saves the user and friend to the user repository.
     * @param id - the id of the friend to be requested
     * @param model - the model to be used to display the friends page
     * @param token - the token used to retrieve the user's details
     * @return - the friends page jsp
     * @Author - Jakob (jkep1)
     */
    @RequestMapping("/requestFriend")
    public String requestFriend(@RequestParam String id, Model model, OAuth2AuthenticationToken token) {
        User user = userRepo.findByUsername(token.getPrincipal().getAttribute("preferred_username"));
        if (user == null) return "redirect:/";
        model.addAttribute("user", user);

        Avatar avatar = user.getAvatar();
        model.addAttribute("heads", Body.Heads);
        model.addAttribute("headid", avatar.getHeadId());

        User friend = userRepo.findById(Long.parseLong(id)).orElse(null);
        if (friend == null) return "redirect:/friends";

        if (!user.hasFriendRequest(friend) && !user.hasFriend(friend)) {
            user.addFriendRequestOutgoing(friend);
            friend.addFriendRequestIncoming(user);
            userRepo.save(user);
            userRepo.save(friend);
        }

        return "redirect:/friends";
    }

    /* This method is used to remove a friend. It retrieves the user and friend from the user repository and removes the friend from the user's friends list and removes the user from the friend's friends list. It then saves the user and friend to the user repository.
     * @param id - the id of the friend to be removed
     * @param model - the model to be used to display the friends page
     * @param token - the token used to retrieve the user's details
     * @return - the friends page jsp
     * @Author - Jakob (jkep1)
     */
    @RequestMapping("/removeFriend")
    public String removeFriend(@RequestParam String id, Model model, OAuth2AuthenticationToken token) {
        User user = userRepo.findByUsername(token.getPrincipal().getAttribute("preferred_username"));
        if (user == null) return "redirect:/";
        model.addAttribute("user", user);

        Avatar avatar = user.getAvatar();
        model.addAttribute("heads", Body.Heads);
        model.addAttribute("headid", avatar.getHeadId());

        User friend = userRepo.findById(Long.parseLong(id)).orElse(null);
        if (friend == null) return "redirect:/friends";

        user.removeFriend(friend);
        friend.removeFriend(user);
        userRepo.save(user);
        userRepo.save(friend);

        return "redirect:/friends";
    }

    /* This method is used to find a friend. It retrieves the user from the user repository and finds all the users that match the search query. It then displays the search results.
     * @param name - the name of the friend to be found
     * @param model - the model to be used to display the find friends page
     * @param token - the token used to retrieve the user's details
     * @return - the find friends page jsp
     * @Author - Jakob (jkep1)
     */
    @RequestMapping("/findFriend")
    public String findFriend(@RequestParam String name, Model model, OAuth2AuthenticationToken token) {
        model.addAttribute("token", token);
        model.addAttribute("principal", token.getPrincipal());
        model.addAttribute("details", token.getPrincipal().getAttributes());
        model.addAttribute("navitems", WebController.defaultNavItems);

        User user = userRepo.findByUsername(token.getPrincipal().getAttribute("preferred_username"));
        if (user == null) return "redirect:/";
        model.addAttribute("user", user);

        Avatar avatar = user.getAvatar();
        model.addAttribute("heads", Body.Heads);
        model.addAttribute("headid", avatar.getHeadId());

        List<User> users = (ArrayList<User>) userRepo.findAll();
        List<User> options = new ArrayList<>();

        String lowerName = name.toLowerCase();
        for (User u : users) {
            if (
                (
                    u.getUsername().toLowerCase().contains(lowerName) || // Search by username
                    u.getName().toLowerCase().contains(lowerName) // Search by name
                ) &&
                !u.equals(user) // Don't show the user themselves
            ) {
                options.add(u); // Add the found user to the search results
            }
        }

        if (options.isEmpty()) { // Return to the friends page if no users are found with error message
            model.addAttribute("error", "No users found");
            model.addAttribute("searched", name);

            model.addAttribute("friends", user.getFriends());
            model.addAttribute("friendRequestsIncoming", user.getFriendRequestsIncoming());
            model.addAttribute("friendRequestsOutgoing", user.getFriendRequestsOutgoing());

            return "app/friends";
        }

        model.addAttribute("options", options);

        return "app/findFriends";
    }

    /* This method is used to display the profile page. It retrieves the user's details from the token and displays the user's details.
     * @param model - the model to be used to display the profile page
     * @param token - the token used to retrieve the user's details
     * @return - the profile page jsp
     * @Author - Jakob (jkep1)
     */
    @RequestMapping("/profile/{id}")
    public String profile(@PathVariable String id, Model model, OAuth2AuthenticationToken token) {
        model.addAttribute("token", token);
        model.addAttribute("principal", token.getPrincipal());
        model.addAttribute("details", token.getPrincipal().getAttributes());
        model.addAttribute("navitems", WebController.defaultNavItems);

        User user = userRepo.findById(Long.parseLong(id)).orElse(null);
        if (user == null) return "redirect:/";
        model.addAttribute("user", user);

        Avatar avatar = user.getAvatar();
        model.addAttribute("heads", Body.Heads);
        model.addAttribute("headid", avatar.getHeadId());

        User current_user = userRepo.findByUsername(token.getPrincipal().getAttribute("preferred_username"));
        if (current_user == null) return "redirect:/";
        model.addAttribute("current_user", current_user);

        if (!current_user.hasFriend(user)) {
            return "redirect:/friends";
        }

        // calc current progress percentage for user level
        double levelPercentage = (
                (double) user.getProgressionHandler().getCurrentPoints() /
                        (double) user.getProgressionHandler().getNextLevelRequirement()
        ) * 100;

        //round down so we keep whole numbers and (99.78 == 99 not 100)
        model.addAttribute("progress", (int) Math.floor(levelPercentage));
        model.addAttribute("unlockedBadges", user.getRewardHandler().getBadgesUnlocked());
        model.addAttribute("lockedBadges", user.getRewardHandler().getBadgesLocked());
        model.addAttribute("currentCourses", user.getCoursesInProgress());
        return "app/friendProfile";
    }
}
