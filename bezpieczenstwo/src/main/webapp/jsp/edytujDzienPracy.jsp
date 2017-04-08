<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ada
  Date: 2017-04-08
  Time: 19:56
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
    <title>Edytuj dzien pracy</title>
</head>

<script type='text/javascript'>
    $(document).ready(function () {
        document.getElementById('inputDzien').value = "${dzienTygodnia}";
        document.getElementById('inputGodzinaRozpoczecia').value = "${godzinaRozpoczecia}";
        document.getElementById('inputGodzinaZakonczenia').value = "${godzinaZakonczenia}";

    });
</script>

<body>
<form class="form-signin" action="<c:url value="/index/edytowanieDniaPracy"/>?id=${idDnia}" method="POST">
    <h2 class="form-signin-heading">Edytuj dzien pracy</h2>
    <input name="dzienTygodnia" type="text" id="inputDzien" class="form-control" placeholder="Dzien tygodnia" required>
    <input type="text" name="godzinaRozpoczecia" id="inputGodzinaRozpoczecia" class="form-control" placeholder="Godzina rozpoczecia pracy" required>
    <input type="text" name="godzinaZakonczenia" id="inputGodzinaZakonczenia" class="form-control" placeholder="Godzina zakończenia pracy" required>


    <button class="btn btn-lg btn-primary btn-block" type="submit">Zapisz</button>
</form>
</body>
</html>
