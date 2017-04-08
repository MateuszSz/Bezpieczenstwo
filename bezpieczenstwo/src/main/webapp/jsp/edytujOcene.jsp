<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ada
  Date: 2017-04-08
  Time: 11:43
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




    <title>Edytuj ocene</title>
</head>
<script type='text/javascript'>
    $(document).ready(function () {
        document.getElementById('inputPrzedmiot').value = "${przedmiot}";
        document.getElementById('inputOcena').value = "${ocena}";
        document.getElementById('inputUczen').value = "${nazwiskoUcznia}";

    });
</script>



<body>
<form class="form-signin" action="<c:url value="/index/edytowanieOceny" />?id=${idOceny}" method="POST">
    <h2 class="form-signin-heading">Edytuj ocene</h2>
    <input name="przedmiot" type="text" id="inputPrzedmiot" class="form-control" placeholder="Przedmiot" required>
    <input type="text" name="ocena" id="inputOcena" class="form-control" placeholder="Ocena" required>
    <input type="text" name="uczen" id="inputUczen" class="form-control" placeholder="Uczen" required readonly>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Zapisz</button>
</form>

</body>
</html>
