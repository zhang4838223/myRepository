<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="/jquery-2.1.4/jquery.js" type="application/javascript"></script>
<html>
<head>
    <title>服务器列表</title>
</head>
<body>

<form id="saveForm" action="/jfinal/hello/save" method="post" onsubmit="return saveServer();">
    <table>
        <tr>
            <td>
                serverId
            </td>
            <td>
                <input type="number" name="server.show_id"/>
            </td>
            <td>
                host
            </td>
            <td>
                <input type="text" name="server.host"/>
            </td>
            <td>
                is_new
            </td>
            <td>
                <select name="server.is_new">
                    <option value="1">新服</option>
                    <option value="0">旧服</option>
                </select>
            </td>
            <td>
                name
            </td>
            <td>
                <input type="text" name="server.name"/>
                <input type="hidden" name="server.cp_enter_only" value="1"/>
            </td>
            <td>
                cp_show_only
            </td>
            <td>
                <select name="server.cp_show_only">
                    <option value="1">是</option>
                    <option value="0">否</option>
                </select>
            </td>
            <td>
                online_limit
            </td>
            <td>
                <input type="number" name="server.online_limit"/>
            </td>
            <td>
                <input type="submit" value="保存" />
            </td>
        </tr>
    </table>
</form>


    <table >
        <thead>
            <tr>
                <th>show_id</th>
                <th>host</th>
                <th>is_new</th>
                <th>name</th>
                <th>cp_show_only</th>
                <th>cp_enter_only</th>
                <th>online_limit</th>
                <th>操作</th>
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
                <td><a href="/jfinal/hello/delete?show_id=${server.id}">删除</a></td>
            </tr>
        </c:forEach>
    </table>
</body>

<script type="text/javascript">

    $(document).ready(function(){

        function saveServer() {
            // jquery 表单提交
            //document.getElementById("saveForm").submit();
            $("#saveForm").ajaxSubmit(function(message) {
                alert("11");
                if('SUCC'==message){
                    alert("保存成功！")
                }
            });

            return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
        }
    });

    function saveServer() {
        // jquery 表单提交
        //document.getElementById("saveForm").submit();
        $("#saveForm").ajaxSubmit(function(message) {
            alert("11");
           if('SUCC'==message){
               alert("保存成功！")
           }
        });

        return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
    }


</script>

</html>
