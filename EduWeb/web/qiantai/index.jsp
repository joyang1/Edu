<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">    
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <LINK href="<%=path%>/css/css.css" type=text/css rel=stylesheet>
    </head>

    <BODY text=#000000  leftMargin=0 topMargin=0>
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
        <%
            int startnotices = Integer.parseInt(request.getAttribute("startnotices").toString());
            int allnoticescount = Integer.parseInt(request.getAttribute("allnoticescount").toString());
            String[] no_title = new String[allnoticescount];
            String[] no_time = new String[allnoticescount];
            for (int i = startnotices; i >= 0; i--) {
                no_title[i] = request.getAttribute("no_title" + i).toString();
                no_time[i] = request.getAttribute("no_time" + i).toString();
            }
        %>
        <div class="wrap"> 
            <TABLE  cellSpacing=0 cellPadding=0 width="100%" align=center border=0  background="<%=path%>/img/reservation01.gif">
                <TR height="90">
                    <TD align="center">
                        <jsp:include flush="true" page="/qiantai/inc/incTop1.jsp"></jsp:include> 
                        </TD>
                    </TR>
                </TABLE>


                <TABLE id=guide cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
                    <TR>
                        <TD align="left">
                        <jsp:include flush="true" page="/qiantai/inc/incTop2.jsp"></jsp:include>
                        </TD>
                    </TR>
                </TABLE>

                <TABLE class=MainTable style="MARGIN-TOP: 0px" cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
                    <TR>
                        <TD class=Side vAlign=top align=right width="25%">

                            <TABLE width="100%" height="160" border=0 cellPadding=0 cellSpacing=0 class=dragTable>
                                <TR>
                                    <TD class=head>
                                        <SPAN class=TAG>用户登录</SPAN>
                                    </TD>
                                </TR>
                                <TR>
                                    <TD class=middle align=left>
                                    <jsp:include flush="true" page="/qiantai/userlogin/userlogin.jsp"></jsp:include> 
                                    </TD>
                                </TR>
                            </TABLE>

                            <TABLE width="100%" height="160" border=0 cellPadding=0 cellSpacing=0 class=dragTable>
                                <TR>
                                    <TD class=head>
                                        <SPAN class=TAG>网站通知</SPAN>
                                    </TD>
                                </TR>
                                <TR>
                                    <TD class=middle align=left>
                                        <TABLE cellSpacing=0 cellPadding=0 width="99%" border=0>
                                        <%  for (int i = startnotices; i >= 0; i--) {%>
                                        <tr>
                                            <td>
                                                <%
                                                 out.println("<a target='_blank' href='ShowNotices?id=" + i + " '>");
                                                 out.print(no_title[i]);
                                                 out.println("</a>");
                                                %> 
                                            </td>
                                        </tr>
                                        <% }%>
                                    </TABLE> 
                                </TD>
                            </TR>
                        </TABLE>
                        <TABLE width="100%" height="160" border=0 cellPadding=0 cellSpacing=0 class=dragTable>
                            <TR>
                                <TD class=head>
                                    <SPAN class=TAG>日历表</SPAN>
                                </TD>
                            </TR>
                            <TR>
                                <TD class=middle align=left>
                                    <jsp:include flush="true" page="/qiantai/rili/rili.jsp"></jsp:include> 
                                    </TD>
                                </TR>
                            </TABLE>

                        </TD>
                        <td width="1">&nbsp;</td>
                        <td class=Side vAlign=top align=right width="75%">

                            <TABLE class=dragTable cellSpacing=0 cellPadding=0 width="99%" border=0>
                                <tr>
                                    <th width="60%">新闻标题</th><th width="40%">日期</th>
                                </tr>
                            <%  for (int i = startnews; i >= 0; i--) {%>
                            <tr>
                                <td align="center"> 
                                    <%
                                        out.println("<a target='_blank' href='ShowNews?id=" + i + " '>");
                                        out.print(title[i]);
                                        out.println("</a>");
                                    %> 
                                </td>
                                <td align="center"> <% out.print(time[i]);%> </td>
                            </tr>
                            <% }%>
                        </TABLE>                        
                    </td>
                </TR>
            </TABLE>
            <jsp:include flush="true" page="/qiantai/inc/incFoot.jsp"></jsp:include>
        </div>
    </BODY>
</html>
