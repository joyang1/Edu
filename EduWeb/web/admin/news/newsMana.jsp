<%-- 
    Document   : newsMana
    Created on : 2014-7-11, 19:39:41
    Author     : 小小一
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            int startnews = Integer.parseInt(request.getAttribute("startnews").toString());
            int allnewscount = Integer.parseInt(request.getAttribute("allnewscount").toString());
            String[] title = new String[allnewscount];
            String[] time = new String[allnewscount];
            for (int i = startnews; i >= 0; i--) {
                title[i] = request.getAttribute("title" + i).toString();
                time[i] = request.getAttribute("time" + i).toString();
            }
        %>
        <table width="95%" border=1>
            <tr>
                <td align="center" width="50%">新闻标题</td><td align="center" width="25%">修改</td><td align="center" width="25%">删除</td>
            </tr>
            <%  for (int i = startnews; i >= 0; i--) {%>
            <tr>
<!--                <td align="center"> <a target="_blank" href='ShowNews?id=" + i + " '> <% out.print(title[i]); %> </a></td>-->
                <td align="center">
                <%
                        out.println("<a target='_blank' href='ShowNews?id=" + i + " '>");
                        out.print(title[i]);
                        out.println("</a>");
                %>
                </td>
                <td align="center"> <a href="#">修改</a> </td>
                <td align="center"> <input type="button" value="删除"></td>
            </tr>
            <% }%>
        </table>
    </body>
</html>
