<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/styles/styles.css">
    <link rel="stylesheet" href="/styles/scrollbar.css">
    <link rel="icon" type="image/x-icon" href="/img/ibm-logo/favicon.ico">
    <script src="https://kit.fontawesome.com/e1ffb954fb.js" crossorigin="anonymous"></script>
    <title>IBMSkillsBuild - Profile</title>
</head>
<body>
    <c:import url="/WEB-INF/views/template/navbar.jsp"/><!-- Links to the navbar -->

    <!-- display name here -->
    <h1 class="title">${details.get("name")}</h1>
    <form action="/avatar-creation">
        <div class="avatar-align grid">
        <img width="100px" height="100px" style="margin-top: 10px" src="${heads.get(headid)}">
            <c:if test="${headid != 0}">
        <img width="100px" height="100px" src="${bodies.get(bodyid)}">
        <img width="100px" height="100px" style="margin-bottom: 10px" src="${legs.get(legid)}">
            </c:if>
        <input class="change-avatar-button" type="submit" name="Change Avatar" value="Change Avatar">
        </div>
    </form>
    <div class="detailsContainer">
        <div class="tile level" tabindex=0>
            <!-- level here -->
            <h2 class="levelBadge">Level: <i style="font-style: normal; color: var(--highlight-color);">${user.getProgressionHandler().getCurrentLevel()}</i><i class="fa-solid fa-star" style="color: var(--highlight-color);"></i></h2>
            <!-- next level progress bar here -->
            <h4>Progress to next level:</h4>
            <div class="progBarContainer">
                <div class="progBar" style="width:${(progress*0.9)+10}%;">
                    <p tabindex=0><strong>${progress}% </strong></p>
                </div>
            </div>
            <!-- Courses completed -->
            <div>
                <p><strong>Courses Started: </strong>${user.getCoursesFinished().size()+user.getCoursesInProgress().size()}</p>
                <p><strong>Courses in Progress: </strong>${user.getCoursesInProgress().size()}</p>
                <p><strong>Courses Completed: </strong>${user.getCoursesFinished().size()}</p>
            </div>
        </div>
        <div class="tile details" tabindex=0>
            <!-- details here -->
            <h2>Account Details</h2>
            <p><strong>Full Name: </strong>${details.get("name")}</p>
            <p><strong>Preferred Name: </strong>${details.get("given_name")}</p>
            <p><strong>ID: </strong>${user.getId()}</p>
            <p><strong>Email: </strong>${details.get("email")}</p>
        </div>
    </div>

    <!-- Friends here -->
    <div class="grid-container">
        <h2 class="sub-title">Your friends</h2>
        <div class="grid">
            <c:if test="${user.getFriends().size() > 0}">
                <c:forEach items="${user.getFriends()}" var="friend">
                    <div class="tile" tabindex=0>
                        <h2>${friend.getName()}</h2>
                        <div class="links">
                            <a href="/profile/${friend.getId()}">View profile <i class="fas fa-user"></i></a>
                            <a href="/removeFriend?id=${friend.getId()}" class="error">Remove friend <i class="fas fa-user-slash"></i></a>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${user.getFriends().size() == 0}">
                <div class="tile error" tabindex=0>
                    <h2>No friends here!</h2>
                    <div>
                        <p>
                            Add friends through your friends page
                        </p>
                        <a href="/friends">Go to friends</a>
                    </div>
                </div>
            </c:if>
        </div>
    </div>

    <!-- Unlocked badges here -->
    <div class="grid-container">
        <h2 class="sub-title">Your badges</h2>
        <div class="grid">
            <c:if test="${unlockedBadges.size() > 0}">
                <c:forEach items="${unlockedBadges}" var="badge">
                    <div class="tile" tabindex=0>
                        <h2>${badge.getName()}</h2>
                        <p>${badge.getDescription()}</p>
                        <div>
                            <img class="badge" src="${badge.getLink()}" alt="${badge.getName()}">
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${unlockedBadges.size() == 0}">
                <div class="tile error" tabindex=0>
                    <h2>No badges here!</h2>
                    <div>
                        <p>
                            Try completing your first course
                        </p>
                    </div>
                </div>
            </c:if>
        </div>
    </div>

    <!-- current courses carousel -->
    <div class="carousel-container">
        <h2>Current Courses</h2>
        <div class="carousel">
            <c:if test="${currentCourses.size() > 0}">
            <c:forEach items="${currentCourses}" var="course">
                <div class="tile" tabindex=0>
                    <h2>${course.getName()}</h2>
                    <p>${course.getDescription()}</p>
                    <div class="links">
                        <a href="${course.getLink()}">IBM SkillsBuild</a>
                        <a href="/course?id=${course.getId()}">View Course</a>
                        <a href="/finish?id=${course.getId()}">Finish Course</a>
                    </div>
                </div>
            </c:forEach>
            </c:if>
            <c:if test="${currentCourses.size() == 0}">
                <div class="tile error" tabindex=0>
                    <h2>No courses started!</h2>
                    <div>
                        <p>
                            Start a course through your dashboard
                        </p>
                        <div class="links">
                            <a href="/dashboard">Go to dashboard</a>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>

    <c:import url="/WEB-INF/views/template/footer.jsp"/><!-- Links to the footer -->
</body>
</html>