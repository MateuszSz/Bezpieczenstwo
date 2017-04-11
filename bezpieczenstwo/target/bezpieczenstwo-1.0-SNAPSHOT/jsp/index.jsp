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
    <link href="<c:url value="/resources/CSS/main.css" />" rel="stylesheet">
    <script c:url src="../resources/js/sortedTable.js"></script>
    <script c:url src="../resources/js/funkcje.js"></script>
    <%--<link c:url href="../resources/CSS/mainCss.css" rel="stylesheet" type="text/css">--%>
</head>
<%--<style>--%>
    <%--body {--%>
        <%--padding-top: 40px;--%>
        <%--padding-bottom: 40px;--%>
        <%--background-repeat: repeat-y;--%>
        <%--background-color: tan;--%>
        <%--background-size: 100%;--%>
        <%--color: #6b5633;--%>
    <%--}--%>

    <%--.form-signin {--%>
        <%--max-width: 330px;--%>
        <%--padding: 15px;--%>
        <%--margin: 0 auto;--%>

    <%--}--%>

    <%--.form-signin .form-signin-heading,--%>
    <%--.form-signin .checkbox {--%>
        <%--margin-bottom: 10px;--%>
    <%--}--%>

    <%--.form-signin .checkbox {--%>
        <%--font-weight: normal;--%>
    <%--}--%>

    <%--.form-signin .form-control {--%>
        <%--position: relative;--%>
        <%--height: auto;--%>
        <%---webkit-box-sizing: border-box;--%>
        <%---moz-box-sizing: border-box;--%>
        <%--box-sizing: border-box;--%>
        <%--padding: 10px;--%>
        <%--font-size: 16px;--%>
    <%--}--%>

    <%--.form-signin .form-control:focus {--%>
        <%--z-index: 2;--%>
    <%--}--%>

    <%--.form-signin input[type="email"] {--%>
        <%--margin-bottom: -1px;--%>
        <%--border-bottom-right-radius: 0;--%>
        <%--border-bottom-left-radius: 0;--%>
    <%--}--%>

    <%--.form-signin input[type="password"] {--%>
        <%--margin-bottom: 10px;--%>
        <%--border-top-left-radius: 0;--%>
        <%--border-top-right-radius: 0;--%>
    <%--}--%>
    <%--.btn-primary {--%>
        <%--background-image: linear-gradient(to bottom, #6b5633 0, rgba(226, 162, 50, 0.62) 100%);--%>
        <%--border-color: #886933;--%>
    <%--}--%>
    <%--.btn-primary:hover {--%>
        <%--background-image: linear-gradient(to bottom, #6b5633 0, rgba(226, 162, 50, 0.62) 100%);--%>
        <%--border-color: #886933;--%>
    <%--}--%>

    <%--table{--%>
        <%--padding: 15px;--%>
        <%--border: 1px solid #6b5633;--%>
        <%--border-collapse: collapse;--%>

    <%--}--%>

    <%--th{--%>
        <%--background-color: wheat;--%>
        <%--text-align: center;--%>
        <%--padding: 5px;--%>
    <%--}--%>
    <%--td{--%>
        <%--padding: 5px;--%>
    <%--}--%>

<%--</style>--%>

<body background="<c:url value="/resources/images/bodybg.png"/> ">

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
                <sec:authorize access="hasPermission(#user, 'EDIT_LEKI')">
                    <th>Edytuj</th>
                </sec:authorize>
                <sec:authorize access="hasPermission(#user, 'DELETE_LEKI')">
                    <th>Usuń</th>
                </sec:authorize>

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
        </table><br/>
    </sec:authorize>

    <sec:authorize access="hasPermission(#user, 'ADD_LEKI')">
        <a href="<c:url value="/index/dodajLek.htm"/>" class="btn btn-default">Dodaj lek</a> <br/>
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
                <sec:authorize access="hasPermission(#user, 'EDIT_KSIAZKI')">
                    <th>Edytuj</th>
                </sec:authorize>
                <sec:authorize access="hasPermission(#user, 'DELETE_KSIAZKI')">
                    <th>Usuń</th>
                </sec:authorize>

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
        </table><br/>
    </sec:authorize>

    <sec:authorize access="hasPermission(#user, 'ADD_KSIAZKI')">
        <a href="<c:url value="/index/dodajKsiazke.htm"/>" class="btn btn-default">Dodaj ksiazke</a>
    </sec:authorize>
    <br/>
    <sec:authorize access="hasPermission(#user, 'READ_MOJEOCENY')">
        <table class="sortable">
            <tr>
                <th>Przedmiot</th>
                <th>Ocena</th>
                <th>Nauczyciel</th>
                <sec:authorize access="hasPermission(#user, 'EDIT_MOJEOCENY')">
                    <th>Edytuj</th>
                </sec:authorize>
                <sec:authorize access="hasPermission(#user, 'DELETE_MOJEOCENY')">
                <th>Usuń</th>
                </sec:authorize>

            </tr>
            <c:forEach items="${listaMoichOcen}" var="tablicaOcen">
                <tr>
                    <c:forEach items="${tablicaOcen}" var="ocena">
                        <td>${ocena}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table> <br/>
    </sec:authorize>

    <sec:authorize access="hasPermission(#user, 'READ_WYSTAWIONEOCENY')">
        <table class="sortable">
            <tr>

                <th>Przedmiot</th>
                <th>Ocena</th>
                <th>Uczen</th>
                <sec:authorize access="hasPermission(#user, 'EDIT_WYSTAWIONEOCENY')">
                    <th>Edytuj</th>
                </sec:authorize>
                <sec:authorize access="hasPermission(#user, 'DELETE_WYSTAWIONEOCENY')">
                    <th>Usuń</th>
                </sec:authorize>


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
        </table><br/>
    </sec:authorize>

    <sec:authorize access="hasPermission(#user, 'ADD_WYSTAWIONEOCENY')">
        <a href="<c:url value="/index/dodajOcene.htm"/>" class="btn btn-default">Dodaj ocene</a>  <br/>
    </sec:authorize>

    <sec:authorize access="hasPermission(#user, 'READ_DNIPRACY')">
        <table class="sortable">
            <tr>

                <th>Dzień tygodnia</th>
                <th>Godzina rozpoczecia</th>
                <th>Godzina zakonczenia</th>
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
        <br/>
    </sec:authorize>

    <sec:authorize access="hasPermission(#user, 'ADD_DNIPRACY')">
        <a href="<c:url value="/index/dodajDzienPracy.htm"/>" class="btn btn-default">Dodaj dzień pracy</a><br/>
    </sec:authorize>
















</center>
<br>
<jsp:include page="footer.jsp"/>


</body>
</html>