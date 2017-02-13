<%-- 
    Document   : actVoteAdd
    Created on : 2014-10-31, 20:11:52
    Author     : 小小一
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String path = request.getContextPath();
    String voteid = request.getAttribute("voteid").toString();
    int rows = Integer.parseInt(request.getAttribute("rows").toString());
    String[] option = new String[rows];
    for (int i = 0; i < rows; i++) {
        option[i] = request.getAttribute("option" + i).toString();
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>活动投票选项添加</title>
    </head>
    <body>
        <table>
            <th>已添加的投票选项：</th>
            <%for (int i = 0; i < rows; i++) {%>
            <tr>
                <td>
                    <%
                        out.print(i+1+"、"+option[i]);
                    %>
                </td>
            </tr>
            <%}%>
        </table>
        <br/><br/><br/><hr/>
        <form action="<%=path%>/AddActOption" method="post">
            <input type="hidden" value="<%=voteid%>" name="voteid">
            <table>
                <th>添加新的投票选项：</th>
                <tr>
                    <td style="font-size: 11px;">选项内容</td>
                    <td><input type="text" name="vote_option" size="50"/></td>
                </tr>
                <tr><td><input type="submit" value="确定添加"></td></tr>
            </table>
        </form>
    </body>
</html>