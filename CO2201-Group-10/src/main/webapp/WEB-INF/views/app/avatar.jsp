<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="styles/styles.css">
    <script src="https://kit.fontawesome.com/e1ffb954fb.js" crossorigin="anonymous"></script>
    <title>IBM SkillsBuild - Avatar Selection</title>
</head>
<body>
<c:import url="/WEB-INF/views/template/navbar.jsp"/><!-- Links to the navbar -->

    <h1 class="title">Choose your Avatar!</h1>
    <div class="avatar-layout">
    <div class="left-buttons">
    <a href="/avatar-updatehead/back"><input class="left-bodybutton" type="submit" value="Previous Head"></a>
    <a href="/avatar-updatebody/back"><input class="left-bodybutton" type="submit" value="Previous Body"></a>
    <a href="/avatar-updatelegs/back"><input class="left-bodybutton" type="submit" value="Previous Legs"></a>
    </div>
    <div class="avatar-align">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <img class="bodypart" src="${heads.get(headid)}">

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <img class="bodypart" src="${bodies.get(bodyid)}">

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <img class="bodypart" src="${legs.get(legid)}">
    </div>
    <div class="right-buttons">
    <a href="/avatar-updatehead/next"><input class="right-bodybutton" type="submit" value="Next Head"></a>
    <a href="/avatar-updatebody/next"><input class="right-bodybutton" type="submit" value="Next Body"></a>
    <a href="/avatar-updatelegs/next"><input class="right-bodybutton" type="submit" value="Next Legs"></a>
    </div>
    </div>
    <a href="/profile"><input class="change-avatar-button" type="submit" value="Save your Avatar!"></a>

<c:import url="/WEB-INF/views/template/footer.jsp"/><!-- Links to the footer -->
</body>
</html>