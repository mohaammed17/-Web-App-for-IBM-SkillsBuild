package com.example.co2201group10.controller;

import com.example.co2201group10.model.*;
import com.example.co2201group10.repository.BadgeRepo;
import com.example.co2201group10.repository.CourseRepo;
import com.example.co2201group10.repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.co2201group10.repository.UserRepo;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.lang.Long.parseLong;

@Controller
public class WebController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private BadgeRepo badgeRepo;

    @Autowired
    private ReviewRepo reviewRepo;


    public static String[] defaultNavItems = new String[] { "Dashboard", "Courses", "Rewards", "Friends", "Leaderboard", "Chatroom" };

    @RequestMapping("/")
    public String start(Model model, OAuth2AuthenticationToken token) {
        User user = userRepo.findByUsername(token.getPrincipal().getAttribute("preferred_username"));

        if (user == null) {
            user = new User();
            user.setUsername(token.getPrincipal().getAttributes().get("preferred_username").toString());
            user.setName(token.getPrincipal().getAttributes().get("name").toString());
            user.getRewardHandler().addBadges(badgeRepo);
            user = userRepo.save(user);
        }

        user.getRewardHandler().unlockBadges(user);
        userRepo.save(user);

        return "redirect:/dashboard";
    }

    /* This method is used to display the course page. It retrieves the course from the course repository and displays the course details.
    * @param courseId - the id of the course to be displayed
    * @param model - the model to be used to display the course page
    * @param token - the token used to retrieve the user's details
    * @return - the course page jsp or the dashboard page if the course is not found
    * @Author - Jakob (jkep1)
    */
    @RequestMapping("/course")
    public String course(@RequestParam String id, Model model, OAuth2AuthenticationToken token) {
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

        Optional<Course> course = courseRepo.findById(parseLong(id));
        if (course.isEmpty()) {
            return "/dashboard";
        } else {
            List<Review> reviewList = course.get().getCourseReviews();
            if (reviewList.size() > 0) {
                int sum = 0;
                for (int i = 0; i < reviewList.size(); i++) {
                    sum += reviewList.get(i).getRating();
                }
                double average = sum / reviewList.size();
                model.addAttribute("average", average);
            }
            model.addAttribute("course", course.get());
            model.addAttribute("reviews", course.get().getCourseReviews());
        }

        return "app/course";
    }

    /* This method is used to display the courses page. It retrieves all the courses from the course repository and displays the courses.
    * @param model - the model to be used to display the courses page
    * @param token - the token used to retrieve the user's details
    * @return - the courses page jsp
    * @Author - Jakob (jkep1)
    */
    @RequestMapping("/courses")
    public String courses(Model model, OAuth2AuthenticationToken token) {
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

        model.addAttribute("recommended", user.createCoursesRecommended((List<Course>) courseRepo.findAll()));
        model.addAttribute("courses", courseRepo.findAll());
        return "app/courses";
    }

    /* This method is used to display the dashboard page. It retrieves the user's details from the token and displays the user's courses in progress, finished courses and recommended courses.
    * @param model - the model to be used to display the dashboard page
    * @param token - the token used to retrieve the user's details
    * @return - the dashboard page jsp
    * @Author - Jakob (jkep1)
    */
    @RequestMapping("/dashboard")
    public String dashboard(Model model, OAuth2AuthenticationToken token) {
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

        model.addAttribute("coursesInProgress", user.getCoursesInProgress());
        model.addAttribute("coursesFinished", user.getCoursesFinished());
        model.addAttribute("coursesRecommended", user.createCoursesRecommended((List<Course>) courseRepo.findAll()));
        model.addAttribute("top3Courses",Course.CalculatePopularity((ArrayList<Course>) courseRepo.findAll()));
        return "app/dashboard";
    }

    /* This method is used to display the profile page. It retrieves the user's details from the token and displays the user's details.
    * @param model - the model to be used to display the profile page
    * @param token - the token used to retrieve the user's details
    * @return - the profile page jsp
    * @Author - Jakob (jkep1)
    */
    @RequestMapping("/profile")
    public String profile(Model model, OAuth2AuthenticationToken token) {
        model.addAttribute("token", token);
        model.addAttribute("principal", token.getPrincipal());
        model.addAttribute("details", token.getPrincipal().getAttributes());
        model.addAttribute("navitems", WebController.defaultNavItems);

        User user = userRepo.findByUsername(token.getPrincipal().getAttribute("preferred_username"));;
        if (user == null) return "redirect:/";
        model.addAttribute("user", user);

        Avatar avatar = user.getAvatar();

        model.addAttribute("headid", avatar.getHeadId());
        model.addAttribute("bodyid", avatar.getBodyId());
        model.addAttribute("legid", avatar.getLegId());

        model.addAttribute("heads", Body.Heads);
        model.addAttribute("bodies", Body.Bodies);
        model.addAttribute("legs", Body.Legs);

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
        return "app/profile";
    }

    @RequestMapping("/start")
    public String startCourse(@RequestParam String id, Model model, OAuth2AuthenticationToken token) {
        User user = userRepo.findByUsername(token.getPrincipal().getAttribute("preferred_username"));;
        if (user == null) return "redirect:/";

        Avatar avatar = user.getAvatar();
        model.addAttribute("heads", Body.Heads);
        model.addAttribute("headid", avatar.getHeadId());

        Optional<Course> course = courseRepo.findById(parseLong(id));
        if (course.isEmpty()) {
            return "redirect:/dashboard";
        } else if (user.hasCourse(course.get())) {
            return "redirect:/dashboard";
        }

        user.addCourseInProgress(course.get());
        user.getRewardHandler().unlockBadges(user);
        course.get().incrementViews();

        CourseTime currentTime = new CourseTime();
        currentTime.setCourseId(parseLong(id));
        currentTime.setStartDate(LocalDateTime.now());
        user.addCourseTime(currentTime);

        userRepo.save(user);
        return "redirect:/dashboard";
    }

    @RequestMapping("/finish")
    public String completeCourse (@RequestParam String id, Model model, OAuth2AuthenticationToken token) {
        User user = userRepo.findByUsername(token.getPrincipal().getAttribute("preferred_username"));;
        if (user == null) return "redirect:/";

        Avatar avatar = user.getAvatar();
        model.addAttribute("heads", Body.Heads);
        model.addAttribute("headid", avatar.getHeadId());

        Optional<Course> course = courseRepo.findById(parseLong(id));
        if (course.isEmpty()) {
            return "redirect:/dashboard";
        } else if (!user.hasCourseInProgress(course.get())) {
            return "redirect:/dashboard";
        }

        user.finishCourse(course.get());
        user.getCourseTimes().forEach((item) -> {
            if(item.getCourseId()==(parseLong(id))){
                item.setEndDate(LocalDateTime.now());
            }
        });
        user.getProgressionHandler().earnPoints(course.get().getPoints());
        user.getRewardHandler().unlockBadges(user);
        userRepo.save(user);
        return "redirect:/dashboard";
    }

    @RequestMapping("/rewards")
    public String rewards(Model model, OAuth2AuthenticationToken token) {
        model.addAttribute("navitems", WebController.defaultNavItems);

        User user = userRepo.findByUsername(token.getPrincipal().getAttribute("preferred_username"));;
        if (user == null) return "redirect:/";
        model.addAttribute("user", user);

        Avatar avatar = user.getAvatar();
        model.addAttribute("heads", Body.Heads);
        model.addAttribute("headid", avatar.getHeadId());

        model.addAttribute("unlockedBadges", user.getRewardHandler().getBadgesUnlocked());
        model.addAttribute("lockedBadges", user.getRewardHandler().getBadgesLocked());
        model.addAttribute("principal", token.getPrincipal());
        model.addAttribute("details", token.getPrincipal().getAttributes());
        model.addAttribute("completed", user.getCoursesFinished());

        return "app/rewards";
    }

    @RequestMapping("/review")
    public String review(@RequestParam String id, Model model, OAuth2AuthenticationToken token){
        model.addAttribute("token", token);
        model.addAttribute("principal", token.getPrincipal());
        model.addAttribute("details", token.getPrincipal().getAttributes());
        model.addAttribute("navitems", WebController.defaultNavItems);
        User user = userRepo.findByUsername(token.getPrincipal().getAttribute("preferred_username"));
        if (user == null) return "redirect:/";

        Avatar avatar = user.getAvatar();
        model.addAttribute("heads", Body.Heads);
        model.addAttribute("headid", avatar.getHeadId());

        model.addAttribute("review", new Review());
        model.addAttribute("user", user);
        Optional<Course> course = courseRepo.findById(parseLong(id));
        if (course.isEmpty()) {
            return "/dashboard";
        } else {

            model.addAttribute("course", course.get());
            model.addAttribute("reviews", course.get().getCourseReviews());
        }
        return "app/review";
    }

    /* Method used to submit reviews, gets input from form, adds to database and course, then returns to the course's page */

    @RequestMapping("/submitReview")
    public String submitReview(@RequestParam String id, @ModelAttribute Review review, Model model, OAuth2AuthenticationToken token)
    {
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
        Review temp = new Review();
        temp.setComment(review.getComment());
        temp.setCourseId(Integer.parseInt(id));
        temp.setRating(review.getRating());
        reviewRepo.save(temp);
        Optional<Course> course = courseRepo.findById(parseLong(id));
        course.get().addCourseReview(temp);
        courseRepo.save(course.get());

        if (course.isEmpty()) {
            return "/dashboard";
        } else {
            List<Review> reviewList = course.get().getCourseReviews();
            if (reviewList.size() > 0) {
                int sum = 0;
                for (int i = 0; i < reviewList.size(); i++) {
                    sum += reviewList.get(i).getRating();
                }
                double average = sum / reviewList.size();
                model.addAttribute("average", average);
            }
            model.addAttribute("course", course.get());
            model.addAttribute("reviews", course.get().getCourseReviews());
        }

        return "app/course";
    }
    }