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

    <title>Doda rolę użytkownikowi</title>
</head>


<script type='text/javascript'>
    $(document).ready(function () {
        var select = document.getElementById("inputImieINazwisko");
        <d:forEach items="${listaUzytkownikow}" var="tablicaUzytkownikow">
        var el = document.createElement("option");
        el.textContent = "<d:out value="${tablicaUzytkownikow[1]}"/>";
        el.value = <d:out value="${tablicaUzytkownikzow[0]}"/>;
        select.appendChild(el);
        </d:forEach>

        select = document.getElementById("inputRola");
        <d:forEach items="${listaRol}" var="tablicaRol">
        el = document.createElement("option");
        el.textContent = "<d:out value="${tablicaRol[1]}"/>";
        el.value = <d:out value="${tablicaRol[0]}"/>;
        select.appendChild(el);
        </d:forEach>
    });
</script>
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
<form class="form-signin" action="<c:url value="/index/dodawanieRoliUzytkownikowi"/>" method="POST">
    <h2 class="form-signin-heading">Proszę wybrać użytkownika i jego nową rolę</h2>
    <label for="inputImieINazwisko" class="sr-only">Podaj imię i naziwsko</label>
    <select name="imieINazwisko" id="inputImieINazwisko" class="form-control"></select>
    <label for="inputRola" class="sr-only">Podaj rolę</label>
    <select name="rola" id="inputRola" class="form-control"></select>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Dodaj</button>
</form>

<jsp:include page="../footer.jsp"/>
</body>

</html>
