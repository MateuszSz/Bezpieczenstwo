<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="<d:url value="/resources/CSS/main.css" />">
    <title>Title</title>
</head>
<style>
    /*body {*/
    /*padding-top: 40px;*/
    /*padding-bottom: 40px;*/
    /*background-repeat: repeat-y;*/
    /*background-color: tan;*/
    /*background-size: 100%;*/
    /*color: #6b5633;*/
    /*}*/

    .btn-primary {
        background-image: linear-gradient(to bottom, #6b5633 0%, rgba(226, 162, 50, 0.62) 100%);
        border-color: #886933;
        background-repeat: inherit;
    }

    .btn-primary:hover {
        background-image: linear-gradient(to bottom, #6b5633 100%, rgba(226, 162, 50, 0.62) 0%);
        border-color: #886933;
    }

    .errorMessage {
        color: red;
    }
</style>

<script type='text/javascript'>
    $(document).ready(function () {


        var select = document.getElementById("inputRole");
        <d:forEach items="${listaRol}" var="tablicaRol">
        var el = document.createElement("option");
        el.textContent = "<d:out value="${tablicaRol[1]}"/>";
        el.value = "<d:out value="${tablicaRol[1]}"/>";
        select.appendChild(el);
        </d:forEach>
    });
</script>

<body>
<form class="form-signin" action="<c:url value="/j_spring_security_check"/>" method="POST">
    <h2 class="form-signin-heading">Proszę się zalogować</h2>
    <label for="inputEmail" class="sr-only">Login</label>
    <input name="j_username" type="email" id="inputEmail" class="form-control" placeholder="Login" required autofocus>
    <label for="inputPassword" class="sr-only">Hasło</label>
    <input type="password" name="j_password" id="inputPassword" class="form-control" placeholder="Hasło" required>
    <label for="inputRole">Zaloguj jako:</label>
    <select name="role" id="inputRole"></select>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Zaloguj</button>
</form>
<center>
    <div class="errorMessage">${wiadomosc}</div>
    ${login_error}
</center>
<jsp:include page="footer.jsp"/>
</body>

</html>
