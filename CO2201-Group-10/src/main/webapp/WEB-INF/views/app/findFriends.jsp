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
    <title>IBM SkillsBuild - Friends</title>
</head>
<body>
<c:import url="/WEB-INF/views/template/navbar.jsp"/><!-- Links to the navbar -->

<!-- Options for friends from the database -->

<div class="grid-container">
        <h1>Options</h1>
        <div class="grid">
            <c:if test="${not empty options}">
                <c:forEach items="${options}" var="option">
                    <div class="tile" tabindex=0>
                        <h2>${option.getName()}</h2>
                        <ul>
                            <li><strong>Level</strong>: ${option.getProgressionHandler().getCurrentLevel()} <i class="fa-solid fa-star" style="color: var(--highlight-color);"></i></li>
                        </ul>
                    <c:if test="${user.hasFriend(option)}">
                        <a href="/profile/${friend.getId()}">View profile <i class="fas fa-user"></i></a>
                        <a class="error" href="/removeFriend?id=${option.getId()}">Remove Friend <i class="fas fa-user-slash"></i></a>
                    </c:if>
                    <c:if test="${not user.hasFriend(option) && not user.getFriendRequestsOutgoing().contains(option) && not user.getFriendRequestsIncoming().contains(option)}">
                        <a class="errorPositive" href="/requestFriend?id=${option.getId()}">Add Friend <i class="fas fa-user-plus"></i></a>
                    </c:if>
                    <c:if test="${not user.hasFriend(option) && user.getFriendRequestsOutgoing().contains(option)}">
                        <a class="error" href="/removeFriend?id=${option.getId()}">Cancel Request <i class="fas fa-user-minus"></i></a>
                    </c:if>
                    <c:if test="${not user.hasFriend(option) && user.getFriendRequestsIncoming().contains(option)}">
                        <a class="errorPositive" href="/acceptFriend?id=${option.getId()}">Accept Request <i class="fas fa-user-plus"></i></a>
                        <a class="error" href="/removeFriend?id=${option.getId()}">Reject Request <i class="fas fa-user-minus"></i></a>
                    </c:if>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${empty options}">
                <div class="tile error">
                    <h2>No users found</h2>
                </div>
            </c:if>
        </div>
    </div>


<c:import url="/WEB-INF/views/template/footer.jsp"/><!-- Links to the footer -->
</body>
</html>