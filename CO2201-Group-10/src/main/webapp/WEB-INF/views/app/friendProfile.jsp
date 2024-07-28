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
<h1 class="title">${user.getName()}</h1>

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
        <p><strong>Name: </strong>${user.getName()}</p>
        <p><strong>Friends: </strong>${user.getFriends().size()}</p>
    </div>
</div>

<!-- Friends here -->
<div class="grid-container">
    <h2 class="sub-title">Their friends</h2>
    <div class="grid">
        <c:if test="${user.getFriends().size() > 0}">
            <c:forEach items="${user.getFriends()}" var="friend">
                <div class="tile" tabindex=0>
                    <h2>${friend.getName()}</h2>
                    <ul>
                        <li><strong>Level: </strong>${friend.getProgressionHandler().getCurrentLevel()}</li>
                        <li><strong>Current Exp: </strong>${friend.getProgressionHandler().getCurrentPoints()}</li>
                    </ul>

                </div>
            </c:forEach>
        </c:if>
        <c:if test="${user.getFriends().size() == 0}">
            <div class="tile error" tabindex=0>
                <h2>No friends here!</h2>
                <div>
                    <p>
                        Add them as your friend by clicking the button below
                    </p>
                    <a class="errorPositive" href="/requestFriend?id=${user.getId()}">Add Friend <i class="fas fa-user-plus"></i></a>
                </div>
            </div>
        </c:if>
    </div>
</div>

<!-- Unlocked badges here -->
<div class="grid-container">
    <h2 class="sub-title">Their badges</h2>
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
                        Add ${user.getName()} as your friend to unlock their first badge!
                    </p>
                </div>
            </div>
        </c:if>
    </div>
</div>

<!-- current courses carousel -->
<div class="carousel-container">
    <h2>Their Current Courses</h2>
    <div class="carousel">
        <c:if test="${currentCourses.size() > 0}">
            <c:forEach items="${currentCourses}" var="course">
                <div class="tile" tabindex=0>
                    <h2>${course.getName()}</h2>
                    <p>${course.getDescription()}</p>
                    <div class="links">
                        <a href="${course.getLink()}">IBM SkillsBuild</a>
                        <a href="/course?id=${course.getId()}">View Course</a>
                        <c:if test="${!current_user.hasCourse(course)}">
                            <a href="/start?id=${course.getId()}">Start Course</a>
                        </c:if>
                        <c:if test="${current_user.hasCourseInProgress(course)}">
                            <a href="/finish?id=${course.getId()}">Finish Course</a>
                        </c:if>
                        <c:if test="${current_user.hasCourseFinished(course)}">
                            <a href="https://skillsbuild.org/college-students/digital-credentials">Certificate</a>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${currentCourses.size() == 0}">
            <div class="tile error" tabindex=0>
                <h2>No courses started!</h2>
                <div>
                    <p>
                        ${user.getName()} is not taking any courses at the moment.
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