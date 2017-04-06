<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ada
  Date: 2017-04-06
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
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


    <title>Dodaj Ocene</title>
</head>
<script type='text/javascript'>
    $(document).ready(function () {
        var select = document.getElementById("inputImieINazwisko");
        <d:forEach items="${uczniowie}" var="tablicaUczniow">
        var el = document.createElement("option");
        el.textContent = "<d:out value="${tablicaUczniow[1]}"/>";
        el.value = <d:out value="${tablicaUczniow[0]}"/>;
        select.appendChild(el);
        </d:forEach>

    });
</script>




<body>
<form class="form-signin" action="<c:url value="/index/dodawanieRoliUzytkownikowi" />" method="POST">
    <h2 class="form-signin-heading">Proszę wybrać ucznia, wpisać przedmiot oraz ocenę.</h2>
    <label for="inputImieINazwisko" class="sr-only">Podaj imię i naziwsko</label>
    <select name="imieINazwisko" id="inputImieINazwisko" class="form-control"></select>
    <label for="inputOcena" class="sr-only">Ocena</label>
    <input type="text" name="ocena" id="inputOcena" class="form-control" placeholder="Ocena" required>
    <label for="inputPrzedmiot" class="sr-only">Przedmiot</label>
    <input type="text" name="przedmiot" id="inputPrzedmiot" class="form-control" placeholder="Przedmiot" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Dodaj</button>
</form>


</body>
</html>
