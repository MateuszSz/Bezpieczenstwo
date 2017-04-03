<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<sec:authorize access="hasPermission(#user, 'READ_ROLE')">
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
</sec:authorize>

<sec:authorize access="hasPermission(#user, 'ADD_ROLE')">
    <a href="<c:url value="/index/dodajRoleUzytkownikowi.htm"/>" class="btn btn-default">Dodaj role</a>
</sec:authorize>