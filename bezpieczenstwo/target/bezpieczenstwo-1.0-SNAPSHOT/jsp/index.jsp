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
</head>
<body>

<jsp:include page="classic.jsp"/>
<br><br>
<center>
    <h2>Witaj ${imieINazwisko}!</h2>
    rola: ${rola}
</center>
<br><br>
<center>
    <sec:authorize access="hasPermission(#user, 'READ_LEKI')">
    <table>
        <tr>
            <td>Nazwa leku</td>
            <td>Ilosc na stanie</td>
            <td>Dawkowanie</td>
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
        <a href="<c:url value="/index/dodajLek"/>" class="btn btn-default">Dodaj ksiazki</a>
    </sec:authorize>

</center>
<br>
<jsp:include page="footer.jsp"/>

</body>
</html>