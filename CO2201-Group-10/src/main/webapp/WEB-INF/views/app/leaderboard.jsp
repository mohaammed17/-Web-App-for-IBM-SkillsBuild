<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/styles/styles.css">
    <link rel="stylesheet" href="/styles/scrollbar.css">
    <link rel="icon" type="image/x-icon" href="/img/ibm-logo/favicon.ico">
    <script src="https://kit.fontawesome.com/e1ffb954fb.js" crossorigin="anonymous"></script>
    <title>IBM SkillsBuild - Leaderboard</title>
</head>
<body>
<c:import url="/WEB-INF/views/template/navbar.jsp"/><!-- Links to the navbar -->
<div class="grid-container">
    <c:if test="${not isFriends}">
        <h2>Global Leaderboard</h2>
        <p class="leaderboardText">See how you stack up against all other users or switch to the <a class="leaderboardLink" href="/leaderboard/friends" tabindex=0>Friends Leaderboard</a></p>
    </c:if>
    <c:if test="${isFriends}">
        <h2>Friends Leaderboard</h2>
        <p class="leaderboardText">See how you stack up against all your friends or switch to the <a class="leaderboardLink" href="/leaderboard" tabindex=0>Global Leaderboard</a></p>
    </c:if>
    <div class="grid">
        <table class="leaderboardTable">
            <tr tabindex=0>
                <th>Rank</th>
                <th>Level</th>
                <th>Username</th>
                <c:if test="${isFriends}"><th>Profile</th></c:if>
            </tr>
            <c:forEach items="${users}" var="c_user" varStatus="ranking">
                <tr <c:if test="${c_user.getId() == user.getId()}">class="user"</c:if> tabindex=0>
                    <td>${ranking.index + 1}</td>
                    <td>${c_user.getProgressionHandler().getCurrentLevel()}</td>
                    <td>${c_user.getUsername()}</td>
                    <c:if test="${isFriends && not (c_user.getId() == user.getId())}"><td><a href="/profile/${c_user.getId()}" class="leaderboardProfile"><i class="fas fa-user"></i></a></td></c:if>
                    <c:if test="${isFriends && (c_user.getId() == user.getId())}"><td></td></c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<!-- Links to the footer -->
<c:import url="/WEB-INF/views/template/footer.jsp"/>
</body>
</html>