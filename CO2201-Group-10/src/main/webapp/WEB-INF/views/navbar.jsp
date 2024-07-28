</main>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<nav>
    <div class="navlinks">
        <c:forEach items="${navitems}" var="navitem">
            <a class="navitem" href="/${navitem.toLowerCase()}">${navitem}</a>
        </c:forEach>
    </div>
    <div class="profilelink">
        <c:if test="${empty principal}">
            <a href="/login">Login</a>
        </c:if>
        <c:if test="${not empty principal}">
            <img class="profile-icon"  src="${heads.get(headid)}">
            <a href="/profile">${details.get("given_name")}</a>
            <a href="/logout">Logout</a>
        </c:if>
    </div>
</nav>