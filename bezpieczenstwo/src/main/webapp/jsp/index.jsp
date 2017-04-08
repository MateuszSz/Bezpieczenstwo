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
        background:
    <c:url value="/resources/images/bodybg.png"/>  ;
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
    <jsp:include page="obslugaRol.jsp"/>
    <jsp:include page="obslugaUprawnien.jsp"/>
    <sec:authorize access="hasPermission(#user, 'READ_LEKI')">
        <table class="sortable">
            <tr>

                <th>Nazwa leku</th>
                <th>Ilosc na stanie</th>
                <th>Dawkowanie</th>
            </tr>
            <c:forEach items="${listaLekow}" var="tablicaLekow">
                <tr>
                    <td>${tablicaLekow[1]}</td>
                    <td>${tablicaLekow[2]}</td>
                    <td>${tablicaLekow[3]}</td>

                    <td>
                        <sec:authorize access="hasPermission(#user, 'EDIT_LEKI')">
                            <a href="<c:url value="/index/edytujLek.htm"/>?id=${tablicaLekow[0]}"
                               class="btn btn-default">Edytuj</a>
                        </sec:authorize>
                    </td>
                    <td>
                        <sec:authorize access="hasPermission(#user, 'DELETE_LEKI')">
                            <a href="<c:url value="/index/usunLek"/>?id=${tablicaLekow[0]}"
                               class="btn btn-default">Usuń</a>
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
            <c:forEach items="${listaKsiazek}" var="tablicaKsiazek">
                <tr>
                    <c:forEach items="${tablicaKsiazek}" var="ksiazka">
                        <td>${ksiazka}</td>
                    </c:forEach>
                    <td>
                        <sec:authorize access="hasPermission(#user, 'EDIT_KSIAZKI')">
                            <a href="<c:url value="/index/edytujKsiazki.htm"/>?id=${tablicaKsiazek[0]}"
                               class="btn btn-default">Edytuj</a>
                        </sec:authorize>

                    </td>
                    <td>
                        <sec:authorize access="hasPermission(#user, 'DELETE_KSIAZKI')">
                            <a href="<c:url value="/index/usunKsiazke"/>?id=${tablicaKsiazek[0]}"
                               class="btn btn-default">Usuń</a>
                        </sec:authorize>

                    </td>
                </tr>

            </c:forEach>
        </table>
    </sec:authorize>

    <sec:authorize access="hasPermission(#user, 'ADD_KSIAZKI')">
        <a href="<c:url value="/index/dodajKsiazke.htm"/>" class="btn btn-default">Dodaj ksiazke</a>
    </sec:authorize>

    <sec:authorize access="hasPermission(#user, 'READ_MOJEOCENY')">
        <table class="sortable">
            <tr>
                <th>Przedmiot</th>
                <th>Ocena</th>
                <th>Nauczyciel</th>

            </tr>
            <c:forEach items="${listaMoichOcen}" var="tablicaOcen">
                <tr>
                    <c:forEach items="${tablicaOcen}" var="ocena">
                        <td>${ocena}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </sec:authorize>

    <sec:authorize access="hasPermission(#user, 'READ_WYSTAWIONEOCENY')">
        <table class="sortable">
            <tr>

                <th>Przedmiot</th>
                <th>Ocena</th>
                <th>Uczen</th>

            </tr>
            <c:forEach items="${listaWystawionychOcen}" var="tablicaKsiazek">
                <tr>
                    <td>${tablicaKsiazek[1]}</td>
                    <td>${tablicaKsiazek[2]}</td>
                    <td>${tablicaKsiazek[3]}</td>

                    <td>
                        <sec:authorize access="hasPermission(#user, 'EDIT_WYSTAWIONEOCENY')">
                            <a href="<c:url value="/index/edytujOcene.htm"/>?id=${tablicaKsiazek[0]}"
                               class="btn btn-default">Edytuj</a>
                        </sec:authorize>

                    </td>
                </tr>

            </c:forEach>
        </table>
    </sec:authorize>

    <sec:authorize access="hasPermission(#user, 'ADD_WYSTAWIONEOCENY')">
        <a href="<c:url value="/index/dodajOcene.htm"/>" class="btn btn-default">Dodaj ocene</a>
    </sec:authorize>

    <sec:authorize access="hasPermission(#user, 'READ_DNIPRACY')">
        <table class="sortable">
            <tr>

                <th>Dzień tygodnia</th>
                <th>Godzina rozpoczecia</th>
                <th>Godzina zakonczenia</th>

            </tr>
            <c:forEach items="${listaDniPracy}" var="tablicaDni">
                <tr>

                    <td>${tablicaDni[1]}</td>
                    <td>${tablicaDni[2]}</td>
                    <td>${tablicaDni[3]}</td>
                    <td>
                        <sec:authorize access="hasPermission(#user, 'EDIT_DNIPRACY')">
                            <a href="<c:url value="/index/edytujDzienPracy.htm"/>?id=${tablicaDni[0]}"
                               class="btn btn-default">Edytuj</a>
                        </sec:authorize>

                    </td>
                    <td>
                        <sec:authorize access="hasPermission(#user, 'DELETE_DNIPRACY')">
                            <a href="<c:url value="/index/usunDzienPracy"/>?id=${tablicaDni[0]}"
                               class="btn btn-default">Usuń</a>
                        </sec:authorize>

                    </td>
                </tr>

            </c:forEach>
        </table>
    </sec:authorize>

    <sec:authorize access="hasPermission(#user, 'ADD_DNIPRACY')">
        <a href="<c:url value="/index/dodajDzienPracy.htm"/>" class="btn btn-default">Dodaj dzień pracy</a>
    </sec:authorize>
















</center>
<br>
<jsp:include page="footer.jsp"/>


</body>
</html>