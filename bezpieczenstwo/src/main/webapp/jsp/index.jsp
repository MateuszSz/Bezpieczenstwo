<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<c:forEach items="${listaLekow}" var="mapa">
    <c:forEach items="${mapa}" var="country">
        <option value="${country.key}">${country.value}</option>
    </c:forEach>
</c:forEach>
<br>
<jsp:include page="footer.jsp"/>


</body>
</html>