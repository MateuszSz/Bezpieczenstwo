<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>
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
    <link type="text/css" href="<c:url value="/resources/CSS/main.css" />" rel="stylesheet">
    <title>Usuń rolę </title>
</head>


<script type='text/javascript'>
    $(document).ready(function () {

        var select = document.getElementById("inputRola");
        <d:forEach items="${listaRol}" var="tablicaRol">
        var el = document.createElement("option");
        el.textContent = "<d:out value="${tablicaRol[1]}"/>";
        el.value = <d:out value="${tablicaRol[0]}"/>;
        select.appendChild(el);
        </d:forEach>
    });
</script>

<body background="<c:url value="/resources/images/bodybg.png"/>">
    <h2 class="form-signin-heading">Proszę wybrać rolę do usunięcia</h2>
<form class="form-signin" action="<c:url value="/index/usuwanieRoli"/>" method="POST">
    <label for="inputRola" class="sr-only">Podaj rolę</label>
    <select name="rola" id="inputRola" class="form-control"></select>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Usuń</button>
</form>

<jsp:include page="../footer.jsp"/>
</body>

</html>
