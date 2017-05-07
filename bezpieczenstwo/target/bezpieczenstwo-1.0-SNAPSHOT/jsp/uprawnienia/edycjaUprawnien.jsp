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

    <title>Title</title>
</head>


<script type='text/javascript'>
    $(document).ready(function () {

        <d:forEach items="${listaUprawnien}" var="uprawnienie">
        var nazwa = "${uprawnienie}";
        if(~nazwa.indexOf("ADD")){
            document.getElementById("inputInsert").checked = true;
        }
        else if(~nazwa.indexOf("EDIT")){
            document.getElementById("inputUpdate").checked = true;
        }
        else if(~nazwa.indexOf("DELETE")){
            document.getElementById("inputDelete").checked = true;
        }
        else if(~nazwa.indexOf("READ")){
            document.getElementById("inputSelect").checked = true;
        }

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
<center>
    <h2 class="form-signin-heading">Proszę wybrać rolę, tablicę i uprawnienie do edycji</h2>
</center>
<form class="form-signin" action="<c:url value="/index/dokonajEdycjiUprawnien?id=${idRoli}&nazwa=${tablica}"/>" method="POST">
    <center> <label for="inputSelect">SELECT</label> </center>
    <input id="inputSelect" class="form-control" type="checkbox" name="SELECT" value="true"><br>
    <center> <label for="inputInsert">INSERT</label> </center>
    <input id="inputInsert" class="form-control" type="checkbox" name="INSERT" value="true"><br>
    <center> <label for="inputUpdate">UPDATE</label> </center>
    <input id="inputUpdate" class="form-control" type="checkbox" name="UPDATE" value="true"><br>
    <center> <label for="inputDelete">DELETE</label> </center>
    <input id="inputDelete" class="form-control" type="checkbox" name="DELETE" value="true"><br>
    <%--<input id="id" name="id" type="hidden" value="${idRoli}"/>--%>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Edytuj</button>
</form>

<jsp:include page="../footer.jsp"/>
</body>

</html>
