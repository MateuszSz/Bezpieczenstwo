<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Dodaj ksiazke</title>
</head>
<body>
<%--<body background="<c:url value="/resources/images/bodybg.png"/>">--%>
<form class="form-signin" action="<C:url value="/index/dodawanieKsiazki"/>" method="POST">
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
