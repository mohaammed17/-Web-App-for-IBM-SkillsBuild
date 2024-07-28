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
    <title>IBM SkillsBuild - Rewards</title>
</head>
<body>
<c:import url="/WEB-INF/views/template/navbar.jsp"/><!-- Links to the navbar -->

<!-- grid for unlocked badges -->
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

<!-- grid for locked badges -->
<div class="grid-container">
    <h2 class="sub-title">Locked badges</h2>
    <div class="grid">
        <c:if test="${lockedBadges.size() > 0}">
            <c:forEach items="${lockedBadges}" var="badge">
                <div class="tile badgeLocked" tabindex=0>
                    <h2>${badge.getName()}</h2>
                    <p>${badge.getDescription()}</p>
                    <div>
                        <img class="badge" src="${badge.getLink()}" alt="${badge.getName()}">
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${lockedBadges.size() == 0}">
            <div class="tile errorPositive" tabindex=0>
                <h2>You've unlocked everything!</h2>
                <div>
                    <p>
                        Well done you!
                    </p>
                </div>
            </div>
        </c:if>
    </div>
</div>

<c:import url="/WEB-INF/views/template/footer.jsp"/><!-- Links to the footer -->
</body>
</html>