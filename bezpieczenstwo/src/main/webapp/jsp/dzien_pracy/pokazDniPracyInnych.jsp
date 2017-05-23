<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mateu
  Date: 18.05.2017
  Time: 19:39
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
    <link type="text/css" href="<c:url value="/resources/CSS/main.css" />" rel="stylesheet">
    <script c:url src="../resources/js/sortedTable.js"></script>
    <script c:url src="../resources/js/funkcje.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
   <title>Plany pracowników</title>
</head>
<body background="<c:url value="/resources/images/bodybg.png"/>">
<center>
    <jsp:include page="/jsp/classic.jsp"/>
<sec:authorize access="hasPermission(#user, 'READ_DNIPRACY')">
    <p>Grafiki</p>
    <table class="sortable">
        <tr>

            <th>Dzień tygodnia</th>
            <th>Godzina rozpoczecia</th>
            <th>Godzina zakonczenia</th>
            <th>Osoba</th>
            <sec:authorize access="hasPermission(#user, 'EDIT_DNIPRACY')">
                <th>Edytuj</th>
            </sec:authorize>
            <sec:authorize access="hasPermission(#user, 'DELETE_DNIPRACY')">
                <th>Usuń</th>
            </sec:authorize>

        </tr>
        <c:forEach items="${listaDniPracy}" var="tablicaDni">
            <tr>

                <td>${tablicaDni[1]}</td>
                <td>${tablicaDni[2]}</td>
                <td>${tablicaDni[3]}</td>
                <td>${tablicaDni[4]}</td>
                <sec:authorize access="hasPermission(#user, 'EDIT_DNIPRACY')">
                    <td>
                        <a href="<c:url value="/index/edytujDzienPracy.htm"/>?id=${tablicaDni[0]}&idPracownika=${tablicaDni[5]}"
                           class="btn btn-default">Edytuj</a>
                    </td>
                </sec:authorize>


                <sec:authorize access="hasPermission(#user, 'DELETE_DNIPRACY')">
                    <td>
                        <a href="<c:url value="/index/usunDzienPracy"/>?id=${tablicaDni[0]}"
                           class="btn btn-default">Usuń</a>
                    </td>
                </sec:authorize>


            </tr>

        </c:forEach>
    </table>
    <br/>
</sec:authorize>

<sec:authorize access="hasPermission(#user, 'ADD_DNIPRACY')">
    <a href="<c:url value="/index/dodajDzienPracyInnym.htm"/>" class="btn btn-default">Dodaj dzień pracy</a><br/>
</sec:authorize>
    <br>
    <jsp:include page="/jsp/footer.jsp"/>
</center>
</body>
</html>
