package com.example.co2201group10;

import com.example.co2201group10.model.Badge;
import com.example.co2201group10.model.Body;
import com.example.co2201group10.model.Course;
import com.example.co2201group10.repository.BadgeRepo;
import com.example.co2201group10.repository.CourseRepo;
import com.example.co2201group10.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.co2201group10.model.User;

import java.util.ArrayList;

@SpringBootApplication
public class Co2201Group10Application implements CommandLineRunner {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BadgeRepo badgeRepo;

    public static void main(String[] args) {
        SpringApplication.run(Co2201Group10Application.class, args);
    }

    /* This method is used to add some initial data to the database when the application is started
    * @param args - the command line arguments
    * @throws Exception - if there is an error adding the initial data
    * @Author - Jakob (jkep1)
    */
    @Override
    public void run(String... args) throws Exception {

        // Create some test courses

        Course bitcoin = new Course();
        bitcoin.setName("Blockchain");
        bitcoin.setDescription("Ever heard of Bitcoin? Well, blockchain is the technology that makes Bitcoin possible.");
        bitcoin.setLink("https://skillsbuild.org/students/course-catalog/blockchain");
        bitcoin = courseRepo.save(bitcoin);

        Course cc = new Course();
        cc.setName("Cloud computing");
        cc.setDescription("You know how a lot of the stuff on your phone isn’t actually on your phone? That’s right—all those photos and Spotify playlists are actually on the “cloud”.");
        cc.setLink("https://skillsbuild.org/students/course-catalog/cloud-computing");
        cc = courseRepo.save(cc);

        Course datascience = new Course();
        datascience.setName("Data science");
        datascience.setDescription("Over five billion people around the world are online these days. And we're all creating tons of data every time we do a google search or open an app.");
        datascience.setLink("https://skillsbuild.org/students/course-catalog/data-science");
        datascience = courseRepo.save(datascience);

        Course emergingtech = new Course();
        emergingtech.setName("Emerging technologies");
        emergingtech.setDescription("Curious about tech but not sure where to focus?");
        emergingtech.setLink("https://skillsbuild.org/students/course-catalog/emerging-tech-intro");
        emergingtech = courseRepo.save(emergingtech);

        Course cybersec = new Course();
        cybersec.setName("Cybersecurity");
        cybersec.setDescription("We're connected to the internet constantly. And every online interaction creates information. But what happens to it?");
        cybersec.setLink("https://skillsbuild.org/students/course-catalog/cybersecurity");
        cybersec.addPrerequisite(emergingtech);
        cybersec = courseRepo.save(cybersec);

        Course enterprise = new Course();
        enterprise.setName("Enterprise computing");
        enterprise.setDescription("Behind the scenes, enterprise computing makes it possible for us to make credit card payments, take out money from ATMs, check our bank balances on our phones, trade on the stock market, and book flights or shop online.");
        enterprise.setLink("https://skillsbuild.org/students/course-catalog/enterprise-computing");
        enterprise.addPrerequisite(bitcoin);
        enterprise.addPrerequisite(datascience);
        enterprise = courseRepo.save(enterprise);

        Course it = new Course();
        it.setName("IT support");
        it.setDescription("With over 14 billion devices connected to the internet – from computers and tablets to servers and routers - there’s a critical need to keep them working every day.");
        it.setLink("https://skillsbuild.org/students/course-catalog/it-support");
        it.addPrerequisite(emergingtech);
        it = courseRepo.save(it);

        Course qc = new Course();
        qc.setName("Quantum computing");
        qc.setDescription("Quantum computing uses quantum mechanics to solve really complex problems—the types of problems today's supercomputers can't handle.");
        qc.setLink("https://skillsbuild.org/students/course-catalog/quantum-computing");
        qc.addPrerequisite(cybersec);
        qc = courseRepo.save(qc);

        Course design = new Course();
        design.setName("Principals of Design");
        design.setDescription("Are you a visual person? Do you have an eye for color and patterns? Come explore the world of design!");
        design.setLink("https://skillsbuild.org/students/course-catalog/principles-of-design");
        design = courseRepo.save(design);

        Course sustainability = new Course();
        sustainability.setName("Sustainability");
        sustainability.setDescription("From oceans to outer space and everywhere in between, come explore sustainability!");
        sustainability.setLink("https://skillsbuild.org/students/course-catalog/sustainability");
        sustainability = courseRepo.save(sustainability);

        Course pro = new Course();
        pro.setName("Professional skills");
        pro.setDescription("We all know technical skills are important. But professional skills are just as important if you want to succeed in your future career.");
        pro.setLink("https://skillsbuild.org/students/course-catalog/professional-skills");
        pro = courseRepo.save(pro);

        Course ux = new Course();
        ux.setName("UX design");
        ux.setDescription("In a world where digital experiences matter, UX designers play a crucial role.");
        ux.setLink("https://skillsbuild.org/students/course-catalog/ux-design");
        ux.addPrerequisite(pro);
        ux = courseRepo.save(ux);

        Course webdev = new Course();
        webdev.setName("Web development");
        webdev.setDescription("There are almost 2 billion websites live on the internet today. Who builds all these sites and how do they do it?");
        webdev.setLink("https://skillsbuild.org/students/course-catalog/web-development");
        webdev.addPrerequisite(ux);
        webdev.addPrerequisite(design);
        webdev.addPrerequisite(cc);
        webdev = courseRepo.save(webdev);

        Course ai = new Course();
        ai.setName("Artificial Intelligence");
        ai.setDescription("How much do you really know about how AI works or how it's changing the world around us?");
        ai.setLink("https://skillsbuild.org/students/course-catalog/artificial-intelligence");
        ai.addPrerequisite(datascience);
        ai = courseRepo.save(ai);



        badgeRepo.save(new Badge(
                "Youngling",
                "Complete a course!",
                "/img/course1.png",
                Badge.Condition.Courses,
                1
        ));
        badgeRepo.save(new Badge(
                "Padawan",
                "Complete 3 courses!",
                "/img/course3.png",
                Badge.Condition.Courses,
                3
        ));
        badgeRepo.save(new Badge("Knight",
                "Complete 5 courses!",
                "/img/course5.png",
                Badge.Condition.Courses,
                5
        ));
        badgeRepo.save(new Badge("Master",
                "Complete 10 courses!",
                "/img/course10.png",
                Badge.Condition.Courses,
                10
        ));
        badgeRepo.save(new Badge(
                "Grandmaster",
                "Complete every course!",
                "/img/everyCourse.png",
                Badge.Condition.Courses,
                ((ArrayList<Course>) courseRepo.findAll()).size()
        ));
        badgeRepo.save(new Badge(
                "Getting started",
                "Level up once!",
                "/img/level1.png",
                Badge.Condition.Level,
                2
        ));
        badgeRepo.save(new Badge(
                "Level 5",
                "Reach level 5!",
                "/img/level5.png",
                Badge.Condition.Level,
                5
        ));
        badgeRepo.save(new Badge(
                "Level 10",
                "Reach level 10!",
                "/img/level10.png",
                Badge.Condition.Level,
                10
        ));
        badgeRepo.save(new Badge(
                "Level 15",
                "Reach level 15!",
                "/img/level15.png",
                Badge.Condition.Level,
                15
        ));
        badgeRepo.save(new Badge(
                "Level 20",
                "Reach level 20!",
                "/img/level20.png",
                Badge.Condition.Level,
                20
        ));
        badgeRepo.save(new Badge(
                "Daily 2",
                "Complete 2 courses in 1 day!",
                "/img/2in1.png",
                Badge.Condition.Daily,
                2
        ));
        badgeRepo.save(new Badge(
                "Daily 3",
                "Complete 3 courses in 1 day!",
                "/img/3in1.png",
                Badge.Condition.Daily,
                3
        ));

        // Create Body parts
        Body.Heads.add("/img/head0.png");
        Body.Heads.add("/img/head1.webp");
        Body.Heads.add("/img/head2.webp");
        Body.Heads.add("/img/head3.webp");

        Body.Bodies.add("/img/shirt1.png");
        Body.Bodies.add("/img/shirt2.png");
        Body.Bodies.add("/img/shirt3.webp");

        Body.Legs.add("/img/legs1.png");
        Body.Legs.add("/img/legs2.png");
        Body.Legs.add("/img/legs3.png");

        // Create some test users
        User test1 = new User();
        test1.setUsername("johnsmith@gmail.com");
        test1.setName("John Smith");
        test1.addCourseInProgress(bitcoin);
        test1.addCourseInProgress(cc);
        test1.addCourseInProgress(datascience);
        test1.addCourseFinished(emergingtech);
        test1.getProgressionHandler().earnPoints(250);
        if (userRepo.findByUsername(test1.getUsername()) != null) userRepo.save(test1);

        User test2 = new User();
        test2.setUsername("mgold@gmail.com");
        test2.setName("Mary Gold");
        test2.addCourseInProgress(cybersec);
        test2.addCourseInProgress(enterprise);
        test2.addCourseInProgress(it);
        test2.addCourseFinished(qc);
        test2.getProgressionHandler().earnPoints(500);
        if (userRepo.findByUsername(test2.getUsername()) != null) userRepo.save(test2);

        User test3 = new User();
        test3.setUsername("johnsonbob@outlook.com");
        test3.setName("Bob Johnson");
        test3.addCourseInProgress(design);
        test3.addCourseInProgress(sustainability);
        test3.addCourseInProgress(pro);
        test3.addCourseFinished(ux);
        test3.getProgressionHandler().earnPoints(750);

        if (userRepo.findByUsername(test3.getUsername()) != null) userRepo.save(test3);

    }
}