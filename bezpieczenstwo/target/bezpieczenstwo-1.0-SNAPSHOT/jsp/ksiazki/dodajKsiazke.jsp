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
<form class="form-signin" action="<c:url value="/index/dodawanieKsiazki"/>" method="POST">
    <h2 class="form-signin-heading">Proszę wpisać dane nowej ksiazki</h2>
    <label for="inputAutor" class="sr-only">Autor ksiazki</label>
    <input name="autor" type="text" id="inputAutor" class="form-control" placeholder="Autor ksiazki" required>
    <label for="inputTytul" class="sr-only">Tytuł</label>
    <input type="text" name="tytul" id="inputTytul" class="form-control" placeholder="Tytyl" required>
    <label for="inputISBN" class="sr-only">ISBN</label>
    <input type="text" name="ISBN" id="inputISBN" class="form-control" placeholder="ISBN" required>
    <label for="inputSeria" class="sr-only">Seria</label>
    <input type="text" name="seria" id="inputSeria" class="form-control" placeholder="Seria">
    <label for="inputDostepnosc" class="sr-only">Dostepnosc</label>
    <input type="text" name="dostepnosc" id="inputDostepnosc" class="form-control" placeholder="Dostepnosc" required>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Dodaj nowa ksiazke</button>
</form>
</body>
</html>
