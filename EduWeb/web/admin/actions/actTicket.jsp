<%-- 
    Document   : actTicket
    Created on : 2014-10-31, 21:24:49
    Author     : 小小一
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String path = request.getContextPath();
    int rows = Integer.parseInt(request.getAttribute("rows").toString());
    String[] userno = new String[rows];
    String[] username = new String[rows];
    for (int i = 0; i < rows; i++) {
        userno[i] = request.getAttribute("userno" + i).toString();
        username[i] = request.getAttribute("username" + i).toString();
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>查看电子票</title>
    </head>
    <body>
        <table align="center" border="1">
            <tr>
                <td align="center" width="25%">学号</td><td align="center" width="25%">姓名</td>
            </tr>
            <% for(int i=0;i<rows;i++) { %>
                <td align="center"><%=userno[i]%></td>
                <td align="center"><%=username[i]%></td>
            <%}%>
        </table>
    </body>
</html>
