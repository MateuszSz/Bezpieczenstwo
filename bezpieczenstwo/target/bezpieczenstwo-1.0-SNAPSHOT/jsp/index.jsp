<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/xml" %>

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
    <script c:url src="../resources/js/funkcje.js"></script>
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
                <th>Lp.</th>
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
                <th>Id Leku</th>
                <th>Nazwa leku</th>
                <th>Ilosc na stanie</th>
                <th>Dawkowanie</th>
            </tr>
            <c:forEach items="${listaLekow}" var="listaParametrowLeku">
                <tr>

                    

                    <c:forEach items="${listaParametrowLeku}" var="lek">
                        <td>${lek}</td>
                    </c:forEach>
                    <td>
                        <sec:authorize access="hasPermission(#user, 'EDIT_LEKI')">
                            <a href="<c:url value="/index/edytujLek.htm"/>?id=${listaParametrowLeku[0]}" class="btn btn-default">Edytuj</a>
                        </sec:authorize>

                    </td>
                </tr>

            </c:forEach>
        </table>
    </sec:authorize>

    <sec:authorize access="hasPermission(#user, 'ADD_LEKI')">
        <a href="<c:url value="/index/dodajLek.htm"/>" class="btn btn-default">Dodaj lek</a>
    </sec:authorize>



    <sec:authorize access="hasPermission(#user, 'READ_KSIAZKI')">
        <table class="sortable">
            <tr>
                <th>Lp.</th>
                <th>ISBN</th>
                <th>Autor</th>
                <th>Tytuł</th>
                <th>Seria</th>
                <th>Dostepnosc</th>
            </tr>
            <c:forEach items="${listaKsiazek}" var="mapaKsiazek">
                <tr>
                    <c:forEach items="${mapaKsiazek}" var="ksiazka">
                        <td>${ksiazka.value}</td>
                    </c:forEach>
                    <td>
                        <sec:authorize access="hasPermission(#user, 'READ_KSIAZKI')">
                            <a href="<c:url value="/index/dodajLek.htm"/>" class="btn btn-default">Edytuj</a>
                        </sec:authorize>

                    </td>
                </tr>

            </c:forEach>
        </table>
    </sec:authorize>

</center>
<br>
<jsp:include page="footer.jsp"/>


</body>
</html>