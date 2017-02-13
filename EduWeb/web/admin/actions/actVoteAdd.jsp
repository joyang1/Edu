<%-- 
    Document   : actOptionAdd
    Created on : 2014-10-30, 19:05:37
    Author     : 小小一
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String path = request.getContextPath();
    String actid = request.getAttribute("actid").toString();
    int rows = Integer.parseInt(request.getAttribute("rows").toString());
    String[] title = new String[rows];
    String[] voteid = new String[rows];
    for (int i = 0; i < rows; i++) {
        voteid[i] = request.getAttribute("voteid" + i).toString();
        title[i] = request.getAttribute("title" + i).toString();
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>活动投票标题添加</title>
    </head>
    <body>
        <table>
            <th>已添加的投票标题：</th>
            <%for (int i = 0; i < rows; i++) {%>
            <tr>
                <td>
                    <%
                        out.println("<a target='_blank' href='AddActOption?voteid=" + voteid[i] + " '>");
                        out.print(i+1+"、"+title[i]);
                        out.println("</a>");
                    %>
                </td>
            </tr>
            <%}%>
        </table>
        <br/><br/><br/><hr/>
        <form action="<%=path%>/AddActVote" method="post">
            <input type="hidden" value="<%=actid%>" name="actid">
            <table>
                <th>添加新的投票标题：</th>
                <tr>
                    <td style="font-size: 11px;">投票标题</td>
                    <td><input type="text" name="vote_title" size="50"/></td>
                </tr>
                <tr>
                    <td style="font-size: 11px;">投票类型</td>
                    <td><input type="radio" name="vote_type" value="1"/>事件</td>
                    <td><input type="radio" name="vote_type" value="2"/>人物</td>
                </tr>
                <tr>
                    <td style="font-size: 11px;">选项类型</td>
                    <td><input type="radio" name="vote_select_type" value="1"/>单选</td>
                    <td><input type="radio" name="vote_select_type" value="2"/>多选</td>
                </tr>
                <tr><td><input type="submit" value="确定添加"></td></tr>
            </table>
        </form>
    </body>
</html>
