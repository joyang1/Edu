<%-- 
    Document   : gonggaoAdd
    Created on : 2014-7-26, 20:27:34
    Author     : 小小一
--%>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" href="<%=path%>/css/woncore.css" type="text/css"></link>
                <link rel="stylesheet" href="../../kindeditor/themes/default/default.css" />
                <link rel="stylesheet" href="../../kindeditor/plugins/code/prettify.css" />
                <script type="text/javascript">
                    function choice()
                    {
	                var url="<%=path %>/admin/notice/delChoice.jsp";
	                var n="";
	                var w="480px";
	                var h="500px";
	                var s="resizable:no;help:no;status:no;scroll:yes";
				    openWin(url,n,w,h,s);
                    }
                    <meta charset="utf-8" />	
                </script>
	<script charset="utf-8" src="../../kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="../../kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="../../kindeditor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content"]', {
				cssPath : '../../kindeditor/plugins/code/prettify.css',
				uploadJson : '../../kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '../../kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
               </script>
	</head>

	<BODY>
    <div class="body-box">
        <form action="<%=path %>/ManaNotices" method="post">
		<table border="0">
		     <tr>
		         <td style="font-size: 11px;">标题</td>
		         <td><input type="text" name="title" size="50"/></td>
		     </tr>
		     <tr>
		         <td style="font-size: 11px;">内容</td>
		         <td>
		             <textarea name="content" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"></textarea>
                         </td>
		     </tr>
                     <tr>
		         <td style="font-size: 11px;">学院</td>
<!--		         <td><input type="text" name="dep" size="50"/></td>-->
                         <td><select name="dep" style="width: 355px">
                            <option value="1">信息学院</option>
                            <option value="2">艺术学院</option>
                            <option value="2">管理学院</option>
                            <option value="2">机械学院</option>
                        </select></td>
		     </tr>
		     <tr>
		         <td style="font-size: 11px;">添加人</td>
<!--		         <td><input type="text" name="add" size="50"/></td>-->
                         <td><select name="add" style="width: 355px">
                            <option value="1">1号</option>
                            <option value="2">2号</option>
                            <option value="3">3号</option>
                        </select></td>
		     </tr>
                     <tr>
		         <td style="font-size: 11px;">有效期</td>
<!--		         <td><input type="text" name="indate" size="50"/></td>-->
                         <td><select name="indate" style="width: 355px">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>                            
                        </select></td>
		     </tr>
                     <tr>
<!--                     <tr>
                         <td style="font-size: 11px;">选择发布对象</td>
                         <td><input type="button" value="发布对象" onclick="choice()" style="border:#ccc 1px solid; background-color:#FFFFFF; font-size:12px; padding-top:3px;"/></td>
                     </tr>-->
		     <tr>
		         <td style="font-size: 11px;">&nbsp;</td>
		         <td>
		             <input type="submit" value="提交通知">
                 </td>
		     </tr>
		</table>
		</form>
	</div>
	</BODY>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>