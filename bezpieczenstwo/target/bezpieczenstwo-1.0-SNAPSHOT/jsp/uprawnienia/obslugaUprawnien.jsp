<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<sec:authorize access="hasPermission(#user, 'READ_UPRAWNIENIA')">
    <p>Role i ich uprawnienia do odczytania, dodawania, edycji i usuwania pól w tablicach.</p>
    <table class="sortable">
        <tr>
            <th></th>
            <th>Leki</th>
            <th>Role</th>
            <th>Uprawnienia</th>
            <th>Ksiazki</th>
            <th>Wystawione oceny</th>
            <th>Moje oceny</th>
            <th>Dni pracy</th>
            <th>Użytkownicy</th>
        </tr>
        <c:forEach items="${listaUprawnien}" var="tablicaUprawnien">
            <tr>

                <c:forEach items="${tablicaUprawnien}" var="uprawnienie">
                    <td>${uprawnienie}</td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
</sec:authorize>

<sec:authorize access="hasPermission(#user, 'ADD_UPRAWNIENIA')">
    <a href="<c:url value="/index/dodajUprawnienie.htm"/>" class="btn btn-default">Dodaj uprawnienie</a>
</sec:authorize>

<sec:authorize access="hasPermission(#user, 'DELETE_UPRAWNIENIA')">
    <a href="<c:url value="/index/usunUprawnienie.htm"/>" class="btn btn-default">Usuń uprawnienie</a>
</sec:authorize>

<sec:authorize access="hasPermission(#user, 'EDIT_UPRAWNIENIA')">
    <a href="<c:url value="/index/edytujUprawnienie.htm"/>" class="btn btn-default">Edytuj uprawnienia</a><br><br>
</sec:authorize>