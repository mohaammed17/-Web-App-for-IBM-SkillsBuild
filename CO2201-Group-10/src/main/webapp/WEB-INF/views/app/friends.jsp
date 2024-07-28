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
<script src="/js/search.js" defer></script>
<c:import url="/WEB-INF/views/template/navbar.jsp"/><!-- Links to the navbar -->

<!-- Search bar for adding friends -->
<div class="tile search">
    <h2 class="sub-title">Add friends</h2>
    <div>
        <label for="search">Enter name or id of user:</label>
        <input type="text" id="search" name="search" placeholder="Search for friends"
        <c:if test="${searched != null}">
               value="${searched}"
        </c:if>
        >
    </div>
    <button type="button" id="search-input" style="flex-grow: 1;">Search <i class="fa-solid fa-right-to-bracket"></i></button>
    <p id="search-error" class="error errorData"
       <c:if test="${error == null}">style="visibility: hidden"</c:if>
       tabindex=0
    >${error}</p>
</div>

<!-- Grid for friends -->
<div class="grid-container">
    <h2 class="sub-title">Your friends</h2>
    <div class="grid">
        <c:if test="${friends.size() > 0}">
            <c:forEach items="${friends}" var="friend">
                <div class="tile" tabindex=0>
                    <h2>${friend.getName()}</h2>
                    <ul>
                        <li><strong>Level</strong>: ${friend.getProgressionHandler().getCurrentLevel()} <i class="fa-solid fa-star" style="color: var(--highlight-color);"></i></li>
                    </ul>
                    <div class="links">
                        <a href="/profile/${friend.getId()}">View profile <i class="fas fa-user"></i></a>
                        <a class="error" href="/removeFriend?id=${friend.getId()}">Remove <i class="fas fa-user-slash"></i></a>
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${friends.size() == 0}">
            <div class="tile error" tabindex=0>
                <h2>You've got no friends!</h2>
                <div>
                    <p>
                        Try requesting your first friend by searching for them in the search bar!
                    </p>
                </div>
            </div>
        </c:if>
    </div>
</div>

<!-- Carousel for friend requests -->
<div class="carousel-container">
    <h2 class="sub-title">${friendRequestsIncoming.size()} users want to be friends with you</h2>
    <div class="carousel">
        <c:if test="${friendRequestsIncoming.size() > 0}">
            <c:forEach items="${friendRequestsIncoming}" var="friendRequest">
                <div class="tile" tabindex=0>
                    <h2>${friendRequest.getName()}</h2>
                    <ul>
                        <li><strong>Level</strong>: ${friendRequest.getProgressionHandler().getCurrentLevel()} <i class="fa-solid fa-star" style="color: var(--highlight-color);"></i></li>
                    </ul>
                    <div>
                        <a class="errorPositive" href="/acceptFriend?id=${friendRequest.getId()}">Accept <i class="fas fa-user-plus"></i></a>
                        <a class="error" href="/removeFriend?id=${friendRequest.getId()}">Reject <i class="fas fa-user-minus"></i></a>
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${friendRequestsIncoming.size() == 0}">
            <div class="tile errorPositive" tabindex=0>
                <h2>No friend requests</h2>
                <div>
                    <p>
                        You're all caught up friend requests at the moment.
                    </p>
                </div>
            </div>
        </c:if>
    </div>
</div>

<!-- Carousel for outgoing friend requests -->
<div class="carousel-container">
    <h2 class="sub-title">You want to be friends with ${friendRequestsOutgoing.size()} users</h2>
    <div class="carousel">
        <c:if test="${friendRequestsOutgoing.size() > 0}">
            <c:forEach items="${friendRequestsOutgoing}" var="friendRequest">
                <div class="tile" tabindex=0>
                    <h2>${friendRequest.getName()}</h2>
                    <ul>
                        <li><strong>Level</strong>: ${friendRequest.getProgressionHandler().getCurrentLevel()} <i class="fa-solid fa-star" style="color: var(--highlight-color);"></i></li>
                    </ul>
                    <div>
                        <a class="error" href="/removeFriend?id=${friendRequest.getId()}">Cancel Request <i class="fas fa-user-minus"></i></a>
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${friendRequestsOutgoing.size() == 0}">
            <div class="tile error" tabindex=0>
                <h2>You've got no friends!</h2>
                <p>
                    Try requesting your first friend by searching for them in the search bar!
                </p>
            </div>
        </c:if>
    </div>
</div>

<c:import url="/WEB-INF/views/template/footer.jsp"/><!-- Links to the footer -->
</body>
</html>