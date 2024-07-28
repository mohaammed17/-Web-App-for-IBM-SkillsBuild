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
    <title>IBM SkillsBuild - Dashboard</title>
</head>
<body>
    <c:import url="/WEB-INF/views/template/navbar.jsp"/><!-- Links to the navbar -->

    <!-- CURRENT COURSES -->
    <div class="carousel-container">
        <h2>Current Courses</h2>
        <div class="carousel">
            <c:if test="${coursesInProgress.size() > 0}">
                <c:forEach items="${coursesInProgress}" var="course">
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
            <c:if test="${coursesInProgress.size() == 0}">
                <div class="tile error" tabindex=0>
                    <h2>No courses started!</h2>
                    <div>
                        <p>
                            Start a course from recommended below or from courses page:
                        </p>
                        <div class="links">
                            <a href="/courses">IBM SkillsBuild</a>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>

    <!-- RECOMMENDED COURSES -->
    <div class="carousel-container">
        <h2>Recommended Courses</h2>
        <div class="carousel">
            <c:if test="${coursesRecommended.size() > 0}">
                <c:forEach items="${coursesRecommended}" var="course">
                    <div class="tile" tabindex=0>
                        <h2>${course.getName()}</h2>
                        <p>${course.getDescription()}</p>
                        <div class="links">
                            <a href="${course.getLink()}">IBM SkillsBuild</a>
                            <a href="/course?id=${course.getId()}">View Course</a>
                            <a href="/start?id=${course.getId()}">Start Course</a>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${coursesRecommended.size() == 0}">
                <div class="tile errorPositive" tabindex=0>
                    <h2>You've completed everything!</h2>
                    <div>
                        <p>
                            Well done!
                        </p>
                    </div>
                </div>
            </c:if>
        </div>
    </div>


    <!-- Completed Courses -->
    <div class="carousel-container">
        <h2>Completed Courses</h2>
        <div class="carousel">
            <c:if test="${coursesFinished.size() > 0}">
                <c:forEach items="${coursesFinished}" var="course">
                    <div class="tile" tabindex=0>
                        <h2>${course.getName()}</h2>
                        <p>${course.getDescription()}</p>
                        <div class="links">
                            <a href="${course.getLink()}">IBM SkillsBuild</a>
                            <a href="/course?id=${course.getId()}">View Course</a>
                            <a href="https://skillsbuild.org/college-students/digital-credentials">Certificate</a>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${coursesFinished.size() == 0}">
                <div class="tile error" tabindex=0>
                    <h2>No courses completed!</h2>
                    <div>
                        <p>
                            Start a course from recommended above or from courses page:
                        </p>
                        <div class="links">
                            <a href="/courses">IBM SkillsBuild</a>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>

    <div class="carousel-container">
        <h2>Top 3 Courses</h2>
        <div class="carousel">
            <c:if test="${top3Courses.size() > 0}">
            <c:forEach items="${top3Courses}" var="course">
            <div class="tile" tabindex=0>
                <h2>${course.getName()}</h2>
                <p>${course.getDescription()}</p>
                <div class="links">
                    <a href="${course.getLink()}">IBM SkillsBuild</a>
                    <a href="/course?id=${course.getId()}">View Course</a>
                </div>
            </div>
            </c:forEach>
            </c:if>

        </div>
    </div>

    <c:import url="/WEB-INF/views/template/footer.jsp"/><!-- Links to the footer -->
</body>
</html>