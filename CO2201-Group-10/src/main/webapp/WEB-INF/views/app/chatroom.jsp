<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="/styles/chatStyles.css">
    <link rel="icon" type="image/x-icon" href="/img/ibm-logo/favicon.ico">

    <link rel="stylesheet" href="/styles/styles.css">
    <link rel="stylesheet" href="/styles/scrollbar.css">


    <script src="https://kit.fontawesome.com/e1ffb954fb.js" crossorigin="anonymous"></script>


    <script>
        function connect() {
            console.log("Started connect()");
            // gather username passed in info
            username = "${Uname}";

            // create the socket
            var socket = new SockJS('/ws');
            stompClient = Stomp.over(socket);

            // start connection
            stompClient.connect({}, onConnected, onError);

        }
    </script>

    <title>IBM SkillsBuild - ChatRoom</title>
</head>
<body onload="connect()">

    <!-- Custom Links to the navbar -->
    <nav>
        <div class="navlinks">
            <c:forEach items="${navitems}" var="navitem">
                <a class="navitem" href="/${navitem.toLowerCase()}" onclick="onLeave()">${navitem}</a>
            </c:forEach>
        </div>
        <div class="profilelink">
            <c:if test="${empty principal}">
                <a href="/login" onclick="onLeave()">Login</a>
            </c:if>
            <c:if test="${not empty principal}">
                <img class="profile-icon"  src="${heads.get(headid)}">
                <a href="/profile" onclick="onLeave()">${details.get("given_name")}</a>
                <a href="/logout" onclick="onLeave()">Logout</a>
            </c:if>
        </div><!-- Links to the navbar ends -->
    </nav>

    <div id="chat-page">
        <div class="chat-header">
            <h2>SkillsBuild Chat</h2>
        </div>
        <div class="chat-container">
            <ul id="chatArea">

            </ul>
            <form id="messageForm" name="messageForm">
                <div class="form-group">
                    <div class="input-group clearfix">
                        <input type="text" id="messageInput" placeholder="Type a message..." autocomplete="off" class="form-control"/>
                        <button id="messageSubmit" type="submit">Send</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <c:import url="/WEB-INF/views/template/footer.jsp"/><!-- Links to the footer -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="/js/chatRoom.js" defer></script>
</body>
</html>