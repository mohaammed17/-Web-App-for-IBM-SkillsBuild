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
    <title>IBM SkillsBuild - ${course.getName()}</title>
</head>
<body>
    <c:import url="/WEB-INF/views/template/navbar.jsp"/><!-- Links to the navbar -->

    <div class="tile" tabindex=0>
        <h2>${course.getName()}</h2>
        <p>${course.getDescription()}</p>
        <div class="links">
            <a href="${course.getLink()}">IBM SkillsBuild</a>
            <c:if test="${user.hasCourse(course)}">
                <p><strong>Started: </strong></p>
                <p>
                        ${user.getCourseTimeById(course.getId()).stringStartDate()}
                </p>
                <p><strong>Finished: </strong><p>
                <p>
                        ${(user.getCourseTimeById(course.getId()).getEndDate() != null) ?
                        user.getCourseTimeById(course.getId()).stringEndDate() :
                        "N/A"}
                </p>
            </c:if>
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
            <a href="/review?id=${course.getId()}">Review Course</a>
        </div>
    </div>

    <c:if test="${course.getPrerequisites().size() > 0}">
        <div class="carousel-container">
            <h2>Prerequisite Courses</h2>
            <div class="carousel">
                <c:forEach items="${course.getPrerequisites()}" var="course">
                    <div class="tile" tabindex=0>
                        <h2>${course.getName()}</h2>
                        <p>${course.getDescription()}</p>
                        <div class="links">
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
                            <a href="/review?id=${course.getId()}">Review Course</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:if>
    <div class="grid-container">
        <h2 class="sub-title">Course Reviews</h2>
            <c:if test="${reviews.size() > 0}">
                <h2> Current rating: ${average}</h2>
                <div class="grid">
                <c:forEach items="${reviews}" var="review">
                    <div class="tile" tabindex=0>
                        <c:if test="${review.getComment().length() > 0}">
                            <h2>Rating: ${review.getRating()}</h2>
                            <h2>${review.getComment()}</h2>
                        </c:if>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${reviews.size() == 0}">
                <div class="tile error" tabindex=0>
                    <h2>This course has no reviews</h2>
                    <div>
                        <p>
                            Be the first to review this course!
                        </p>
                    </div>
                </div>
            </c:if>
        </div>
    </div>

    <c:import url="/WEB-INF/views/template/footer.jsp"/><!-- Links to the footer -->
</body>
</html>