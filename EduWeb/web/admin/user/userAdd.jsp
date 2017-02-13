<%-- 
    Document   : userAdd
    Created on : 2014-7-11, 19:56:25
    Author     : 小小一
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <form action="<%=path %>/ManaAdmin" method="post">
		<table border="0">
		     <tr>
		         <td style="font-size: 11px;">管理员姓名</td>
		         <td><input type="text" name="admin_name" size="50"/></td>
		     </tr>
		     <tr>
		         <td style="font-size: 11px;">登陆密码</td>
		         <td><input type="text" name="admin_password" size="50"/></td>
		     </tr>
                     <tr>
		         <td style="font-size: 11px;">权限</td>
                         <td><input type="text" name="admin_level" size="50"/></td>
		     </tr>
                     <tr>
		         <td style="font-size: 11px;">校园一卡通号码</td>
                         <td><input type="text" name="admin_dep" size="50"/></td>
		     </tr>
                     <tr>
		         <td style="font-size: 11px;">IP地址</td>
                         <td><input type="text" name="admin_ip" size="50"/></td>
		     </tr>
                     <tr>
		         <td style="font-size: 11px;">电话</td>
                         <td><input type="text" name="admin_tel" size="50"/></td>
		     </tr>
		     <tr>
		         <td style="font-size: 11px;">&nbsp;</td>
		         <td>
		             <input type="submit" value="确认添加">
                 </td>
		     </tr>
		</table>
		</form>
    </body>
</html>
