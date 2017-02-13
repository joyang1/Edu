<%-- 
    Document   : actAdd
    Created on : 2014-8-7, 19:44:45
    Author     : slv
--%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("content") != null ? request.getParameter("content") : "";
%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>--%>
<%@ page isELIgnored="false" %> 
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
        String id = session.getAttribute("admin_id").toString();
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
                <script language="javascript" src="../../js/Calendar.js"></script>
                <script language="javascript" src="../../js/rili.js"></script>
                <script language="javascript">
                    var cdr = new Calendar("cdr");
                    document.write(cdr);
                    cdr.showMoreDay = true;
                </script>
                
        <meta charset="utf-8" />
	<title>KindEditor JSP</title>
	<link rel="stylesheet" href="../../kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="../../kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="../../kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="../../kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="../../kindeditor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content"]', {
				cssPath : '../kindeditor/plugins/code/prettify.css',
				uploadJson : '../kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '../kindeditor/jsp/file_manager_json.jsp',
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
<!--    <div class="body-box">-->
        <form action="<%=path %>/ManaAct" method="post">
            <input type="hidden" value="<%=id%>" name="id">
		<table border="0">
		     <tr>
		         <td style="font-size: 11px;">活动名称</td>
		         <td><input type="text" name="A_name" size="50"/></td>
		     </tr>
		     <tr>
		         <td style="font-size: 11px;">活动介绍</td>
<!--                         <td><input type="text" name="content" size="50"/></td>-->
		         <td>
		              
                         <textarea name="content" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea>
                 </td>
		     </tr>
                     <tr>
		         <td style="font-size: 11px;">活动类型</td>
		         <td><select name="A_type" style="width: 355px">
                            <option value="1">体育类</option>
                            <option value="2">博会讲坛</option>
                        </select></td>
		     </tr>
                     <tr>
		         <td style="font-size: 11px;">组织单位</td>
                         <td><select name="A_dep" style="width: 355px">
                            <option value="1">信息学院</option>
                            <option value="2">艺术学院</option>
                        </select></td>
		     </tr>
                     <tr>
		         <td style="font-size: 11px;">活动级别</td>
                         <td><select name="A_level" style="width: 355px">
                            <option value="1">校级</option>
                            <option value="2">院级</option>
                        </select></td>
		     </tr>                    
                     <tr>
		         <td style="font-size: 11px;">开始日期</td>
		         <td><input type="text" name="A_begin_date" style="width:355px" onfocus="cdr.show(this,'1980-01-01');"/></td>
		     </tr>
                     <tr>
		         <td style="font-size: 11px;">开始时间</td>
<!--		         <td><input type="text" name="A_begin_time" size="50"/></td>-->
                         <td><table>
                            <td><select name="A_begin_time_h" style="width: 100px">
                            <option value="01">01</option>
                            <option value="02">02</option>
                            <option value="03">03</option>
                            <option value="04">04</option>
                            <option value="05">05</option>
                            <option value="06">06</option>
                            <option value="07">07</option>
                            <option value="08">08</option>
                            <option value="09">09</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                                </select></td>  <td>时&nbsp;</td>
                        <td><select name="A_begin_time_m" style="width: 100px">
                            <option value="05">05</option>
                            <option value="10">10</option>
                            <option value="15">15</option>
                            <option value="20">20</option>
                            <option value="25">25</option>
                            <option value="30">30</option>
                            <option value="35">35</option>
                            <option value="40">40</option>
                            <option value="45">45</option>
                            <option value="50">50</option>
                            <option value="55">55</option>
                            <option value="60">60</option>
                        </select></td><td>分</td>
                         </table><td>                         
		     </tr>
                     <tr>
		         <td style="font-size: 11px;">结束日期</td>
                         <td><input type="text" name="A_end_date"  style="width:355px" onfocus="HS_setDate(this)"></td>
		     </tr>                  
                     <tr>
		         <td style="font-size: 11px;">结束时间</td>
<!--		         <td><input type="text" name="A_end_time" size="50"/></td>-->
                         <td><table>
                            <td><select name="A_end_time_h" style="width: 100px">
                            <option value="01">01</option>
                            <option value="02">02</option>
                            <option value="03">03</option>
                            <option value="04">04</option>
                            <option value="05">05</option>
                            <option value="06">06</option>
                            <option value="07">07</option>
                            <option value="08">08</option>
                            <option value="09">09</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                                </select></td>  <td>时&nbsp;</td>
                        <td><select name="A_end_time_m" style="width: 100px">
                            <option value="05">05</option>
                            <option value="10">10</option>
                            <option value="15">15</option>
                            <option value="20">20</option>
                            <option value="25">25</option>
                            <option value="30">30</option>
                            <option value="35">35</option>
                            <option value="40">40</option>
                            <option value="45">45</option>
                            <option value="50">50</option>
                            <option value="55">55</option>
                            <option value="60">60</option>
                        </select></td><td>分</td>
                         </table><td>
		     </tr>
                     <tr>
		         <td style="font-size: 11px;">活动添加人</td>
<!--		         <td><input type="text" name="A_add" size="50"/></td>-->
                         <td><select name="A_add" style="width: 355px">
                            <option value="1">1号</option>
                            <option value="2">2号</option>
                            <option value="3">3号</option>
                        </select></td>
		     </tr>
                     <tr>
                     <tr>
		         <td style="font-size: 11px;">是否接受门票预订</td>
                         <td>
                             是<input type="radio" name="A_ticket_flag" value="1" checked="checked"/>&nbsp;&nbsp;
                             否<input type="radio" name="A_ticket_flag" value="0"/>
                         </td>

		     </tr>
                     <tr>
		         <td style="font-size: 11px;">门票总数</td>
		         <td><input type="text" name="A_ticket_num" size="50"/></td>
		     </tr>
                     <tr>
		         <td style="font-size: 11px;">报名截止日期</td>
                         <td><input type="text" name="A_ticket_end"  style="width:355px" onfocus="HS_setDate(this)"></td>
		     </tr>
                     <tr>
		         <td style="font-size: 11px;">活动场地</td>
                         <td><select name="A_place_id" style="width: 355px">
                            <option value="1">C区报告厅</option>
                            <option value="2">俱乐部</option>
                            <option value="3">图书馆</option>
                        </select></td>
		     </tr>
		     <tr>
		         <td style="font-size: 11px;">&nbsp;</td>
		         <td>
		             <input type="submit" value="发布活动">
                 </td>
		     </tr>
		</table>
		</form>
	<!--</div>-->
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
