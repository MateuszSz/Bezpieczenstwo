<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ada
  Date: 2017-04-03
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <link type="text/css" href="<c:url value="/resources/CSS/main.css" />" rel="stylesheet">
    <title>Dodaj użytkownika</title>
</head>
<script>
    function check(input) {
        if (input.value !== document.getElementById('inputHaslo').value) {
            input.setCustomValidity('Hasła muszą sie zgadzać');
        } else {
    // input is valid -- reset the error message
            input.setCustomValidity('');
        }
    }
</script>
<body background="<c:url value="/resources/images/bodybg.png"/>">
<%--<body background="<c:url value="/resources/images/bodybg.png"/>">--%>
<form class="form-signin" action="<c:url value="/index/dodawanieUzytkownika"/>" method="POST">
    <h2 class="form-signin-heading">Proszę wpisać dane nowego użytkownika</h2>
    <input name="email" type="email" id="inputEmail" class="form-control" placeholder="E-mail" required>
    <input type="text" name="imieINazwisko" id="inputImieINazwisko" class="form-control" placeholder="Imię i naziwsko" required>
    <input type="password" name="haslo" id="inputHaslo" class="form-control" placeholder="hasło" required>
    <input type="password" name="powtorzHaslo" id="inputPowtorzHaslo" class="form-control" oninput="check(this)" placeholder="Powtórz hasło" required>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Dodaj nowego użytkownika</button>
</form>
</body>
</html>
