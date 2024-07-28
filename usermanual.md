# User Manual
1. [Open webpage](#open-webpage)
1. [Login](#login)
    - [Introduction](#login-introduction)
    - [Registering Account](#registering-account)
    - [Login](#login) 
    - [Reset Passwowrd](#reset-password)
1. [Viewing dashboard](#viewing-dashboard)
    - [Introduction](#introduction)
    - [Current Courses](#current-courses)
    - [Recommended Courses](#recommended-courses)
    - [Completed Courses](#completed-courses)
    - [Top 3 Courses](#top-3-courses)
1. [Starting and finishing course](#starting-and-finishing-course)
1. [Friends](#friends)
    - [Organise Friends](#organise-friends)
    - [Find Friends](#find-friends)
    - [Interacting with Friends](#interacting-with-friends)
1. [Experience System](#experience-system)
1. [Badges](#badges)
8. [Chatroom](#Chatroom)
    - [navigate to chatroom](#navigate-to-chatroom)
    - [sending messages](#sending-messages)
    - [leaving the chatroom](#leaving-the-chatroom)
9. [Avatar Selection](#avatar)

## Open webpage

## Login
### Introduction<a name="login-introduction">
Welcome to the login screen! Using Okta you are able to create/register an account, login to the website and reset your password if you were to ever forget.

### Registering Account<a name="registering-account">

First you will need to create an account so that you are able to login. You will need to provide your email, last name and first name. 

![Registering Account](productimages/register.png)

You will then be prompted to enter a password.

Once you have entered all the information you should recieve a confirmation email sent to the one specified during registration. Once you have verified your account by activiating it through the confirmation email, your account will be all set.

### Login<a name="login">

Now that you have created an account, you are able to login. To login into IBM SkillsBuild enter the username (email used in registration) and password you have used during registration.

![Login](productimages/login.png)

### Reset Password<a name="reset-password">

If you forget your password, you are able to reset it by clicking "forgot password" within and underneath the login page form. You should then recieve an email sent to the one used during registration, prompting you to change your password.

![Reset Password](productimages/forgot-password.png)

## Dashboard
### Introduction<a name="introduction"></a>
Welcome to the IBM SkillsBuild Dashboard! This dashboard provides an overview of your current, recommended, and completed courses. Here's a guide on how to navigate through the dashboard:

![Dashboard](productimages/dashboard.png)

### Current Courses<a name="current-courses"></a>
The "Current Courses" section displays the courses you are currently enrolled in. For each course, you can find the following information:
- **Course Name:** The name of the course.
- **Description:** A brief description of the course content.
- **Links:**
  - "IBM SkillsBuild": Direct link to the IBM SkillsBuild platform.
  - "View Course": Detailed information about the course.
  - "Finish Course": Mark the course as completed.

![Current Course](productimages/currentcourse.png)

If you have not started any courses, a message will prompt you to start a course from the recommended ones or visit the courses page.

![Error Current Course](productimages/errorcurrentcourse.png)


### Recommended Courses<a name="recommended-courses"></a>
The "Recommended Courses" section suggests new courses based on your profile. Details for each course include:
- **Course Name:** The name of the recommended course.
- **Description:** A brief overview of the course content.
- **Links:**
  - "IBM SkillsBuild": Direct link to the IBM SkillsBuild platform.
  - "View Course": Detailed information about the course.
  - "Start Course": Enroll in the recommended course.

![Recommended Course](productimages/recommendedcourse.png)

If you have completed all recommended courses, a positive message will congratulate you.

![Completed All Course](productimages/errorrecomendedcourse.png)

### Completed Courses<a name="completed-courses"></a>
The "Completed Courses" section showcases the courses you have successfully finished. For each completed course, you will find:
- **Course Name:** The name of the completed course.
- **Description:** A brief summary of the course content.
- **Links:**
  - "IBM SkillsBuild": Direct link to the IBM SkillsBuild platform.
  - "View Course": Detailed information about the course.
  - "Certificate": Link to your course completion certificate.
  
![Completed Course](productimages/completedcourse.png)

If no courses have been completed, a message will guide you to start a course from the recommended ones or visit the courses page.

![Start A Course](productimages/errorcompletedcourse.png)

## Top 3 courses

![Top 3 Courses](productimages/top3.png)

In this section you are able to view the 3 most popular courses. This is based on the number of views each course gets. To get to this section all you need to do is go on to the dashboad and scroll down to the bottom and you will be able to view this section.

## Starting and finishing a course

## Experience System
This part of the program lets you track your progress and see the level your working at.
To view your level start of by click your username at the top right hand corner.

![profile](productimages/profile.png)

This takes you to your profile page were you will start off at level 1.

![initial_stage](productimages/initial_stage.png)


To progress on to the next level you need to start a cource.
Go back to your dashboard and select a cource to compete.

![Current Course](productimages/currentcourse.png)


upon selecting the cource you will need to complete it,in order to progress.

![finish_cource](productimages/finish_cource.png)


After the completion of the cource you need to go back to your profile page following the initial steps provided above.

![level_up](productimages/level_up.png)

Once you finish the cource you can see you have progressed slightly.
Keep repeting the above steps to move on to the next level.
The more cources you complete the higher you level up.

## Friends

### Organise friends
![friends_dashboard](productimages/friends_dashboard.png)
![friends_dashboard_requests](productimages/friends_dashboard_requests.png)

The friends page allows users to manage their list of friends. They can view pending friend requests and accept or reject them, as well as removing friends.

### Find friends
![friends_searchbar](productimages/friends_searchbar.png)
Users can search for other users to add them as friends. They can type in a username and see if there are any matches.
![friends_searchbar_error_noname](productimages/friends_searchbar_error_noname.png)
![friends_searchbar_error_nousers](productimages/friends_searchbar_error_nousers.png)
 If there are no matches or if the search query is invalid, appropriate error messages are displayed.

![friends_searchbar_options](productimages/friends_searchbar_options.png)

When a username is searched, the corresponding users are displayed in this grid. Friend requests can be sent or canceled, as well as removing a friend.

### Interacting with friends
- **Friends Leaderboard**: Users can see a leaderboard that displays rankings of their friends based on their level.
![friends_leaderboard](productimages/friends_leaderboard.png)
- **Friends Profile**: Users can view profiles of their friends, which includes information such as their username, level, current courses and more.
![friends_profile_other](productimages/friends_profile_other.png)
- **Your Profile**: View your friends in your profile, and compare yourself to them!
![your_profile](productimages/friends_profile.png)

## Badges

---

## Chatroom
This aspect of our program uses spring boot websockets in order to create a simple chatroom where users can join and send messages to a shared are through the use of STOMP.

### navigate to chatroom <a name="navigate-to-chatroom">
In order to navigate to the chatroom from any from, you will now find it in the header as shown bellow: 

![chatroom-navbar](productimages/chatroom-navbar.png)

Once the chatroom link has been clicked, a join message will be broadcast to everyone currently in the chatroom as shown bellow.

![chatroom-joinRoom](productimages/chatroom-joinRoom.png)

### sending messages <a name="sending-messages">
To send a message to the chatroom, please input the desired message into the text input field as shown below and either press 'enter' on your keyboard or click the 'send' button to the right of the input field:

![chatroom-inputForm](productimages/chatroom-inputForm.png)

Once this is done, your message will appear for all users with the username followed by a timestamp and the message content as shown bellow:

![chatroom-messageExample](productimages/chatroom-messageExample.png)

### leaving the chatroom <a name="leaving-the-chatroom">
To leave the chatroom, press any of the links in the navbar to go to another page of the website.
This will broadcast a leave message to the chatroom users which will appear as such: 

![chatroom-leaveExample](productimages/chatroom-leaveExample.png)

---

## Avatar Selection <a name="avatar">

Once you accessed the application dashboard, in the top left you will see a profile avatar next to your username as shown below:

![dashboard-avatar](productimages/dashboard-avatar.png)

Clicking on your name will take you to the profile page of the application:

![default-avatar](productimages/default-avatar.png)

From here, you can click on "Change Avatar", which will load the avatar selection screen, where you can cycle through the available Head, Body and Legs categories to create an avatar that suits you:

![avatar-selection](productimages/avatar-selection.png)

When you are happy, you can click "Save Avatar" in the bottom left corner of the screen, which will take you back to the profile page, save your avatar, and display it on your profile!

![save-avatar](productimages/save-avatar.png)
