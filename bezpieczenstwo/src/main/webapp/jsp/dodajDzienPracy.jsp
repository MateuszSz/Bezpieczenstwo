<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ada
  Date: 2017-04-08
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodaj dzien pracy</title>
</head>
<body>
<form class="form-signin" action="<c:url value="/index/dodawanieDniaPracy"/> " method="POST">
    <h2 class="form-signin-heading">Proszę wpisać dane nowego dnia pracy</h2>
    <label for="inputDzien" class="sr-only">Dzień tygodnia</label>
    <input name="dzienTygodnia" type="text" id="inputDzien" class="form-control" placeholder="Dzien tygodnia" required>
    <label for="inputGodzinaRozpoczecia" class="sr-only">Godzina rozpoczecia pracy</label>
    <input type="text" name="godzinaRozpoczecia" id="inputGodzinaRozpoczecia" class="form-control" placeholder="Godzina rozpoczecia pracy" required>
    <label for="inputGodzinaZakonczenia" class="sr-only">Godzina zakończenia pracy</label>
    <input type="text" name="godzinaZakonczenia" id="inputGodzinaZakonczenia" class="form-control" placeholder="Godzina zakończenia pracy" required>


    <button class="btn btn-lg btn-primary btn-block" type="submit">Dodaj nowy dzien pracy</button>

</body>
</html>
