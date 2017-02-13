<%-- 
    Document   : newsdetial
    Created on : 2014-7-26, 15:31:34
    Author     : 小小一
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>新闻</title>
        <style type="text/css">
            body{
                text-align: center;
            }
        </style>

    </head>
    <body>
        <%
            String title = request.getAttribute("title").toString();
        %>
        <h1><% out.println(title); %></h1>
        <%
            
            String content = request.getAttribute("content").toString();
            out.println(content);
        %>
    </body>
</html>

