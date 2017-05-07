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
    <title>Dodaj ksiazke</title>
</head>
<style>
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

    body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-repeat: repeat-y;
        background-color: tan;
        background-size: 100%;
        color: #6b5633;
    }

    .btn-primary {
        background-image: linear-gradient(to bottom, #6b5633 0%, rgba(226, 162, 50, 0.62) 100%);
        border-color: #886933;
        background-repeat: inherit;
    }

    .btn-primary:hover {
        background-image: linear-gradient(to bottom, #6b5633 100%, rgba(226, 162, 50, 0.62) 0%);
        border-color: #886933;
    }

</style>
<body background="<c:url value="/resources/images/bodybg.png"/>">
<%--<body background="<c:url value="/resources/images/bodybg.png"/>">--%>
<form class="form-signin" action="<c:url value="/index/dodawanieUzytkownika"/>" method="POST">
    <h2 class="form-signin-heading">Proszę wpisać dane nowego użytkownika</h2>
    <label for="inputEmail" class="sr-only">E-mail</label>
    <input name="email" type="email" id="inputEmail" class="form-control" placeholder="E-mail" required>
    <label for="inputImieINazwisko" class="sr-only">Imię i nazwisko</label>
    <input type="text" name="imieINazwisko" id="inputImieINazwisko" class="form-control" placeholder="Imię i naziwsko" required>
    <label for="inputHaslo" class="sr-only">Hasło</label>
    <input type="password" name="haslo" id="inputHaslo" class="form-control" placeholder="hasło" required>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Dodaj nowego użytkownika</button>
</form>
</body>
</html>
