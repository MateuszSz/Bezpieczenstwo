<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <title>Title</title>
</head>
<style>
    body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-repeat: no-repeat;
        background-color: burlywood;

    }

    .form-signin {
        max-width: 330px;
        padding: 15px;
        margin: 0 auto;

    }

    .form-signin .form-signin-heading,
    .form-signin .checkbox {
        margin-bottom: 10px;
    }

    .form-signin .checkbox {
        font-weight: normal;
    }

    .form-signin .form-control {
        position: relative;
        height: auto;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        padding: 10px;
        font-size: 16px;
    }

    .form-signin .form-control:focus {
        z-index: 2;
    }

    .form-signin input[type="email"] {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }

    .form-signin input[type="password"] {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }
</style>
<body>
<%--<body background="<c:url value="/resources/images/bodybg.png"/>">--%>
<form class="form-signin" action="<c:url value="/j_spring_security_check"/>" method="POST">
    <h2 class="form-signin-heading">Proszę się zalogować</h2>
    <label for="inputEmail" class="sr-only">Login</label>
    <input name="j_username" type="email" id="inputEmail" class="form-control" placeholder="Login" required autofocus>
    <label for="inputPassword" class="sr-only">Hasło</label>
    <input type="password" name="j_password" id="inputPassword" class="form-control" placeholder="Hasło" required>
    <label for="inputRole">Zaloguj jako:</label>
    <select name="role" id="inputRole">
        <option>UCZEN</option>
        <option>ADMINISTRATOR</option>
        <option>NAUCZYCIEL</option>
        <option>DYREKTOR</option>
        <option>BIBLIOTEKARZ</option>
        <option>HIGIENISTKA</option>
    </select>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Zaloguj</button>
</form>

<jsp:include page="footer.jsp"/>
</body>

</html>
