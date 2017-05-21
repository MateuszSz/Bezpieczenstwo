<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <link type="text/css" href="<c:url value="/resources/CSS/main.css" />" rel="stylesheet">
    <title>Dodaj dzien pracy</title>
</head>

<script type='text/javascript'>
    $(document).ready(function () {
        var select = document.getElementById("inputImieINazwisko");
        <d:forEach items="${ludzie}" var="tablicaUzytkownikow">
        var el = document.createElement("option");
        el.textContent = "<d:out value="${tablicaUzytkownikow[1]}"/> <d:out value="${tablicaUzytkownikow[2]}"/>";
        el.value = <d:out value="${tablicaUzytkownikow[0]}"/>;
        select.appendChild(el);
        </d:forEach>

    });

    function test(input) {

        var element = document.getElementById("inputGodzinaRozpoczecia").value;
        var element2 = input.value;
        if (Date.parse('01/01/2011 ' + element + ':45') > Date.parse('01/01/2011 ' + element2 + ':10')) {
            input.setCustomValidity('Godzina rozpoczęcia musi być mniejsza od godziny zakończenia!');
        }
        else {
            input.setCustomValidity('');
        }


    }
</script>
<body background="<c:url value="/resources/images/bodybg.png"/>">
<h2 class="form-signin-heading">Proszę wpisać dane nowego dnia pracy</h2>
<form class="form-signin" action="<c:url value="/index/dodawanieDniaPracy"/> " method="POST">
    <select name="idPracownika" id="inputImieINazwisko" class="form-control"></select>
    <select name="dzienTygodnia" id="inputDzien" class="form-control" required>
        <option value="Poniedzialek">Poniedziałek</option>
        <option value="Wtorek">Wtorek</option>
        <option value="Sroda">Środa</option>
        <option value="Czwartek">Czwartek</option>
        <option value="Piatek">Piątek</option>
        <option value="Sobota">Sobota</option>
        <option value="Niedziela">Niedziela</option>
    </select>
    <label for="inputGodzinaRozpoczecia" class="sr-only">Godzina rozpoczecia pracy</label>
    <input type="time" name="godzinaRozpoczecia" id="inputGodzinaRozpoczecia" class="form-control"
           placeholder="Godzina rozpoczecia pracy" required>
    <label for="inputGodzinaZakonczenia" class="sr-only">Godzina zakończenia pracy</label>
    <input type="time" name="godzinaZakonczenia" oninput="test(this)" id="inputGodzinaZakonczenia" class="form-control"
           placeholder="Godzina zakończenia pracy" required>


    <button class="btn btn-lg btn-primary btn-block" type="submit">Dodaj nowy dzień pracy</button>
</form>
</body>
</html>
