<%-- 
    Document   : oneNews
    Created on : 2014-9-19, 20:54:57
    Author     : 小小一
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<Script lang="JavaScript">
    Functin backRemove(){
        document.removeEventListener("backbutton", eventBackButton, false);
    }
</Script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
    </head>
    <body onload="backRemove()">
        <h1>EDU 新闻</h1>
        <%
            String content = request.getAttribute("content").toString();
        %>
        <p><%=content%></p>
    </body>
</html>
