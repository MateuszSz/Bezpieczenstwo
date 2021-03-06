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
    <link type="text/css" href="<c:url value="/resources/CSS/main.css" />" rel="stylesheet">
    <script c:url src="../resources/js/sortedTable.js"></script>
    <script c:url src="../resources/js/funkcje.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <%--<link c:url href="../resources/CSS/mainCss.css" rel="stylesheet" type="text/css">--%>
</head>


<body background="<c:url value="/resources/images/bodybg.png"/>">

<jsp:include page="classic.jsp"/>
<br><br>
<center>
    <c:if test="${wiadomosc.equals('Nie_mozna_przydzielic_roli')}">
        <div class="errorMessage">Nie można przydzielić roli - statyczna separacja ról!</div>
    </c:if>
    <c:if test="${wiadomosc.equals('Uzytkownik_posiada_dana_role')}">
        <div class="errorMessage">Nie można przydzielić roli - użytkownik już ją posiada!</div>
    </c:if>
    <c:if test="${wiadomosc.equals('Dodano_role_uzytkownikowi')}">
        <div class="successMessage">Rola została przydzielona!</div>
    </c:if>
    <c:if test="${wiadomosc.equals('Usunieto_role_uzytkownikowi')}">
        <div class="successMessage">Rola została usunięta!</div>
    </c:if>
    <c:if test="${wiadomosc.equals('usun_role')}">
        <div class="errorMessage">Istnieje użytkownik o tych dwóch rolach, zabierz mu je albo usuń użytkownika!</div>
    </c:if>
    <c:if test="${wiadomosc.equals('usuwanie_admina')}">
        <div class="errorMessage">Roli administratora nie można usunąć!</div>
    </c:if>
    <c:if test="${wiadomosc.equals('powodzenie')}">
    <div class="successMessage">Prawidłowo dokonano operacji!</div>
</c:if>
    <c:if test="${wiadomosc.equals('dwie_takie_same_role')}">
        <div class="errorMessage">Wybrano dwie takie same role!</div>
    </c:if>
    <c:if test="${wiadomosc.equals('dodano_uzytkownika')}">
        <div class="successMessage">Stworzono nowego użytkownika!</div>
    </c:if>
    <c:if test="${wiadomosc.equals('dokonano_educjo')}">
        <div class="successMessage">Dokonano edycji użytkownika!</div>
    </c:if>
    <c:if test="${wiadomosc.equals('uprawnienie_posiadane')}">
        <div class="errorMessage">Użytkownik już posiada uprawnienie!</div>
    </c:if>
    <h2>Witaj ${imieINazwisko}!</h2>
    rola: ${rola}
    <h2></h2>
    <%--<div class="errorMessage">${wiadomosc}</div>--%>


</center>
<br><br>
<center>
    <jsp:include page="role/obslugaRol.jsp"/>
    <jsp:include page="uprawnienia/obslugaUprawnien.jsp"/>
    <sec:authorize access="hasPermission(#user, 'READ_LEKI')">
        <p>Lista leków, które aktualnie znajdują się w pokoju 513.</p>
        <table class="sortable">
            <tr>

                <th>Nazwa leku</th>
                <th>Dawkowanie</th>
                <th>Ilosc na stanie</th>
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


                    <sec:authorize access="hasPermission(#user, 'EDIT_LEKI')">
                        <td>
                            <a href="<c:url value="/index/edytujLek.htm"/>?id=${tablicaLekow[0]}"
                               class="btn btn-default">Edytuj</a>
                        </td>
                    </sec:authorize>


                    <sec:authorize access="hasPermission(#user, 'DELETE_LEKI')">
                        <td>
                            <a href="<c:url value="/index/usunLek"/>?id=${tablicaLekow[0]}"
                               class="btn btn-default">Usuń</a>
                        </td>
                    </sec:authorize>

                </tr>

            </c:forEach>
        </table>
        <br/>
    </sec:authorize>

    <sec:authorize access="hasPermission(#user, 'ADD_LEKI')">
        <a href="<c:url value="/index/dodajLek.htm"/>" class="btn btn-default">Dodaj lek</a> <br/><br/>
    </sec:authorize>


    <sec:authorize access="hasPermission(#user, 'READ_KSIAZKI')">
        <p>Lista książek aktualnie znajdujących się w bilbiotece.</p>
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

                    <sec:authorize access="hasPermission(#user, 'EDIT_KSIAZKI')">
                        <td>
                            <a href="<c:url value="/index/edytujKsiazki.htm"/>?id=${tablicaKsiazek[0]}"
                               class="btn btn-default">Edytuj</a>
                        </td>
                    </sec:authorize>


                    <sec:authorize access="hasPermission(#user, 'DELETE_KSIAZKI')">
                        <td>
                            <a href="<c:url value="/index/usunKsiazke"/>?id=${tablicaKsiazek[0]}"
                               class="btn btn-default">Usuń</a>
                        </td>
                    </sec:authorize>


                </tr>

            </c:forEach>
        </table>
        <br/>
    </sec:authorize>

    <sec:authorize access="hasPermission(#user, 'ADD_KSIAZKI')">
        <a href="<c:url value="/index/dodajKsiazke.htm"/>" class="btn btn-default">Dodaj ksiazke</a><br>
    </sec:authorize>
    <br/>


    <sec:authorize access="hasPermission(#user, 'READ_MOJEOCENY')">
        <p>Elektorniczny dziennik ocen.</p>
        <table class="sortable">
            <tr>
                <th>Przedmiot</th>
                <th>Ocena</th>
                <th>Wystawił</th>
                <sec:authorize access="hasPermission(#user, 'EDIT_MOJEOCENY')">
                    <th>Edytuj</th>
                </sec:authorize>
                <sec:authorize access="hasPermission(#user, 'DELETE_MOJEOCENY')">
                    <th>Usuń</th>
                </sec:authorize>

            </tr>
            <c:forEach items="${listaMoichOcen}" var="tablicaOcen">
                <tr>
                    <td>${tablicaOcen[1]}</td>
                    <td>${tablicaOcen[2]}</td>
                    <td>${tablicaOcen[3]}</td>
                    <sec:authorize access="hasPermission(#user, 'EDIT_MOJEOCENY')">
                        <td>
                            <a href="<c:url value="/index/edytujOcene.htm"/>?id=${tablicaOcen[0]}"
                               class="btn btn-default">Edytuj</a>
                        </td>
                    </sec:authorize>

                    <sec:authorize access="hasPermission(#user, 'DELETE_MOJEOCENY')">
                        <td>
                            <a href="<c:url value="/index/usunOcene"/>?id=${tablicaOcen[0]}"
                               class="btn btn-default">Usuń</a>
                        </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>
        <br/><br>
    </sec:authorize>
    <sec:authorize access="hasPermission(#user, 'ADD_MOJEOCENY')">
        <a href="<c:url value="/index/dodajMojaOcene.htm"/>" class="btn btn-default">Dodaj ocene</a> <br/><br>
    </sec:authorize>


    <sec:authorize access="hasPermission(#user, 'READ_WYSTAWIONEOCENY')">
        <p>Lista wystawionych przez Ciebie ocen.</p>
        <table class="sortable">
            <tr>

                <th>Przedmiot</th>
                <th>Ocena</th>
                <%--<th>Wystawił</th>--%>
                <th>Uczeń</th>
                <sec:authorize access="hasPermission(#user, 'EDIT_WYSTAWIONEOCENY')">
                    <th>Edytuj</th>
                </sec:authorize>
                <sec:authorize access="hasPermission(#user, 'DELETE_WYSTAWIONEOCENY')">
                    <th>Usuń</th>
                </sec:authorize>


            </tr>
            <c:forEach items="${listaWystawionychOcen}" var="tablicaWystawionychOcen">
                <tr>
                    <td>${tablicaWystawionychOcen[1]}</td>
                    <td>${tablicaWystawionychOcen[2]}</td>
                    <td>${tablicaWystawionychOcen[3]}</td>
                    <%--<td>${tablicaWystawionychOcen[4]}</td>--%>


                        <sec:authorize access="hasPermission(#user, 'EDIT_WYSTAWIONEOCENY')">
                    <td>
                            <a href="<c:url value="/index/edytujOcene.htm"/>?id=${tablicaWystawionychOcen[0]}"
                               class="btn btn-default">Edytuj</a>
                    </td>
                        </sec:authorize>

                    <sec:authorize access="hasPermission(#user, 'DELETE_WYSTAWIONEOCENY')">
                        <td>
                            <a href="<c:url value="/index/usunOcene"/>?id=${tablicaWystawionychOcen[0]}"
                               class="btn btn-default">Usuń</a>
                        </td>
                    </sec:authorize>

                </tr>

            </c:forEach>
        </table>
        <br/>
    </sec:authorize>

    <sec:authorize access="hasPermission(#user, 'ADD_WYSTAWIONEOCENY')">
        <a href="<c:url value="/index/dodajOcene.htm"/>" class="btn btn-default">Dodaj ocene</a> <br/><br>
    </sec:authorize>



    <sec:authorize access="hasPermission(#user, 'READ_DNIPRACY')  ">
        <p>Grafik</p>
        <table class="sortable">
            <tr>

                <th>Dzień tygodnia</th>
                <th>Godzina rozpoczecia</th>
                <th>Godzina zakonczenia</th>
                <%--<th>Imie i nazwisko</th>--%>

                <sec:authorize access="hasPermission(#user, 'EDIT_DNIPRACY')">
                    <th>Edytuj</th>
                </sec:authorize>
                <sec:authorize access="hasPermission(#user, 'DELETE_DNIPRACY')">
                    <th>Usuń</th>
                </sec:authorize>
            </tr>
            <c:forEach items="${listaMoichDniPracy}" var="tablicaDni">
                <tr>

                    <td>${tablicaDni[1]}</td>
                    <td>${tablicaDni[2]}</td>
                    <td>${tablicaDni[3]}</td>
                    <%--<td>${tablicaDni[4]}</td>--%>
                    <sec:authorize access="hasPermission(#user, 'EDIT_DNIPRACY')">
                        <td>
                            <a href="<c:url value="/index/edytujDzienPracy.htm"/>?id=${tablicaDni[0]}"
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
        <a href="<c:url value="/index/dodajDzienPracy.htm"/>" class="btn btn-default">Dodaj dzień pracy</a><br/>
        <br>
    </sec:authorize>
    <sec:authorize access="hasPermission(#user, 'EDIT_DNIPRACY')">
        <sec:authorize access="hasPermission(#user, 'READ_UZYTKOWNICY')">
            <a href="<c:url value="/index/pokazDniPracyInnychPracownikow"/>" class="btn btn-default">Ustal grafiki</a><br/>
        </sec:authorize>
    </sec:authorize>

</center>
<br>
<jsp:include page="footer.jsp"/>


</body>
</html>