<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="d" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/styles/styles.css">
    <link rel="stylesheet" href="/styles/scrollbar.css">
    <script src="https://kit.fontawesome.com/e1ffb954fb.js" crossorigin="anonymous"></script>
    <title>IBM SkillsBuild - ${course.getName()}</title>
</head>
<body>
<c:import url="/WEB-INF/views/template/navbar.jsp"/>
<div class="tile" tabindex=0>
    <h2>${course.getName()}</h2>
    <p>${course.getDescription()}</p>
    <div class="links">
        <a href="${course.getLink()}">IBM SkillsBuild</a>

    </div>
</div>
<div class="tile">

    <d:form action="/submitReview?id=${course.getId()}" modelAttribute="review" method="post">
        <h3>Please give the course a rating between 1 and 5</h3>
        <d:input type="number" path="rating" min="1" max="5"></d:input>
        <h3>Please leave your review in the text box below</h3>
        <d:textarea cols="45" rows="20" path="comment" required="True"></d:textarea><br>

        <input type="submit" value="Submit">
    </d:form>


</div>
<c:import url="/WEB-INF/views/template/footer.jsp"/>
</body>
</html>