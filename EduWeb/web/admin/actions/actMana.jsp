<%-- 
    Document   : actMana
    Created on : 2014-8-15, 9:20:07
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
    <%
            int startact = Integer.parseInt(request.getAttribute("startact").toString());
            int allactcount = Integer.parseInt(request.getAttribute("allactcount").toString());
            String[] actid = new String[allactcount];
            String[] name = new String[allactcount];
            for (int i = startact; i >= 0; i--) {
                actid[i] = request.getAttribute("actid"+i).toString();
                name[i] = request.getAttribute("title" + i).toString();
            }
        %>
        <table width="95%" border=1>
            <tr>
                <td align="center" width="25%">活动标题</td><td align="center" width="25%">选项</td><td align="center" width="25%">电子票</td><td align="center" width="25%">删除</td>
            </tr>
            <%  for (int i = startact; i >= 0; i--) {%>
            <tr>
<!--                <td align="center"> <a target="_blank" href='ShowNews?id=" + i + " '> <% out.print(name[i]); %> </a></td>-->
                <td align="center">
                <%
                        out.println("<a target='_blank' href='ShowAct?id=" + i + " '>");
                        out.print(name[i]);
                        out.println("</a>");
                %>
                </td>
               <td align="center"> 
                <%
                        out.println("<a target='_blank' href='AddActVote?actid=" + actid[i] + " '>");
                        out.print("添加选项");
                        out.println("</a>");
                %>
                </td>
                <td align="center"> 
                <%
                        out.println("<a target='_blank' href='ActTicket?actid=" + actid[i] + " '>");
                        out.print("电子票");
                        out.println("</a>");
                %>
                </td>
                <td align="center"> <input type="button" value="删除"></td>
            </tr>
            <% }%>
        </table>
    </body>
</html>
