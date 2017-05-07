<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<sec:authorize access="hasPermission(#user, 'READ_ROLE')">
    <p>Użytkownicy i ich role.</p>
    <table class="sortable">
        <tr>
            <th>Nazwa roli</th>
            <th>Imię i nazwisko posiadacza</th>
        </tr>
        <c:forEach items="${listaRol}" var="tablicaRol">
            <tr>
                <c:forEach items="${tablicaRol}" var="rola">
                    <td>${rola}</td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
    <br/>
</sec:authorize>

<sec:authorize access="hasPermission(#user, 'ADD_ROLE')">
    <a href="<c:url value="/index/dodajRoleUzytkownikowi.htm"/>" class="btn btn-default">Przyporządkuj</a>
</sec:authorize>

<sec:authorize access="hasPermission(#user, 'ADD_ROLE')">
    <a href="<c:url value="/index/dodajRole.htm"/>" class="btn btn-default">Dodaj nową rolę</a>
</sec:authorize>
<sec:authorize access="hasPermission(#user, 'EDIT_ROLE')">
    <a href="<c:url value="/index/edytujRole.htm"/>" class="btn btn-default">Edytuj rolę</a>
</sec:authorize>

<sec:authorize access="hasPermission(#user, 'DELETE_ROLE')">
    <a href="<c:url value="/index/usunRole.htm"/>" class="btn btn-default">Usuń rolę</a> <br/><br/>
</sec:authorize>


<sec:authorize access="hasPermission(#user, 'ADD_UZYTKOWNICY')">
    <a href="<c:url value="/index/dodajUzytkownika.htm"/>" class="btn btn-default">Stwórz użytkownika</a>
</sec:authorize>

<sec:authorize access="hasPermission(#user, 'DELETE_UZYTKOWNICY')">
    <a href="<c:url value="/index/usunUzytkownika.htm"/>" class="btn btn-default">Usuń użytkownika</a> 
</sec:authorize>

<sec:authorize access="hasPermission(#user, 'EDIT_UZYTKOWNICY')">
    <a href="<c:url value="/index/edytujUzytkownika.htm"/>" class="btn btn-default">Edycja użytkownika</a> <br/><br/>
</sec:authorize>