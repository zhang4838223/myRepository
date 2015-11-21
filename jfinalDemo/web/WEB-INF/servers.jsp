<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>
    <table>
        <thead>
            <tr>
                <th>show_id</th>
                <th>host</th>
                <th>is_new</th>
                <th>name</th>
                <th>cp_show_only</th>
                <th>cp_enter_only</th>
                <th>online_limit</th>
                <th>target_id</th>
            </tr>
        </thead>
        <c:forEach items="${servers}" var="server">
            <tr>
                <td>${server.show_id}</td>
                <td>${server.host}</td>
                <td>${server.is_new}</td>
                <td>${server.name}</td>
                <td>${server.cp_show_only}</td>
                <td>${server.cp_enter_only}</td>
                <td>${server.online_limit}</td>
                <td>${server.target_id}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
