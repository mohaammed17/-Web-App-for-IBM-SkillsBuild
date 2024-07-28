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
    <title>IBM SkillsBuild - Courses</title>
</head>
<body>
    <c:import url="/WEB-INF/views/template/navbar.jsp"/><!-- Links to the navbar -->

    <div class="grid-container">
        <h2>Courses</h2>
        <div class="grid">
            <c:forEach items="${courses}" var="course">
                <div class="tile">
                    <h2>${course.getName()}</h2>
                    <p>${course.getDescription()}</p>
                    <div class="links" tabindex=0>
                        <a href="${course.getLink()}">IBM SkillsBuild</a>
                        <a href="/course?id=${course.getId()}">View Course</a>
                        <c:if test="${!user.hasCourse(course) && course.hasAllPrerequisites(user)}">
                            <a href="/start?id=${course.getId()}">Start Course</a>
                        </c:if>
                        <c:if test="${!user.hasCourse(course) && !course.hasAllPrerequisites(user)}">
                            <a style="visibility: hidden">DEBUG</a>
                        </c:if>
                        <c:if test="${user.hasCourseInProgress(course)}">
                            <a href="/finish?id=${course.getId()}">Finish Course</a>
                        </c:if>
                        <c:if test="${user.hasCourseFinished(course)}">
                            <a href="https://skillsbuild.org/college-students/digital-credentials">Certificate</a>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <c:import url="/WEB-INF/views/template/footer.jsp"/><!-- Links to the footer -->
</body>
</html>