<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<sec:authorize access="hasPermission(#user, 'READ_UPRAWNIENIA')">
    <table class="sortable">
        <tr>
            <th></th>
            <th>Leki</th>
            <th>Role</th>
            <th>Uprawnienia</th>
            <th>Ksiazki</th>
            <th>Oceny</th>
            <th>Dni pracy</th>
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
