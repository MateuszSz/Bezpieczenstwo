<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%--
  Created by IntelliJ IDEA.
  User: mateu
  Date: 21.03.2017
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <script c:url src="../resources/js/sortedTable.js"></script>
</head>
<style>
    body {
        padding-top: 40px;
        padding-bottom: 40px;
        /*
        background: <c:url value="/resources/images/bodybg.png"/> ;
        */
    }
    table.sortable th:not(.sorttable_sorted):not(.sorttable_sorted_reverse):not(.sorttable_nosort):after {
        content: " \25B4\25BE"
    }






</style>
<body>

<jsp:include page="classic.jsp"/>
<br><br>
<center>
    <h2>Witaj ${imieINazwisko}!</h2>
    rola: ${rola}
</center>
<br><br>
<center>
    <sec:authorize access="hasPermission(#user, 'READ_ROLE')">
        <table class="sortable">
            <tr>
                <th>Nazwa roli</th>
                <th>Imię i nazwisko posiadacza</th>
            </tr>
            <c:forEach items="${listaRol}" var="mapaRol">
                <tr>
                    <c:forEach items="${mapaRol}" var="rola">
                        <td>${rola.value}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </sec:authorize>



    <sec:authorize access="hasPermission(#user, 'READ_LEKI')">
    <table class="sortable">
        <tr>
            <th>Nazwa leku</th>
            <th>Ilosc na stanie</th>
            <th>Dawkowanie</th>
        </tr>
        <c:forEach items="${listaLekow}" var="mapaLekow">
            <tr>
                <c:forEach items="${mapaLekow}" var="lek">
                    <td>${lek.value}</td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
    </sec:authorize>

    <sec:authorize access="hasPermission(#user, 'ADD_LEKI')">
        <a href="<c:url value="/index/dodajLek.htm"/>" class="btn btn-default">Dodaj lek</a>
    </sec:authorize>

</center>
<br>
<jsp:include page="footer.jsp"/>

</body>
</html>