<%@ page language="java" pageEncoding="UTF-8"%>

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
        <meta charset="utf-8" />
        <link rel="stylesheet" href="../../kindeditor/themes/default/default.css" />
        <link rel="stylesheet" href="../../kindeditor/plugins/code/prettify.css" />
        <script charset="utf-8" src="../../kindeditor/kindeditor.js"></script>
        <script charset="utf-8" src="../../kindeditor/lang/zh_CN.js"></script>
        <script charset="utf-8" src="../../kindeditor/plugins/code/prettify.js"></script>
        <script>
            KindEditor.ready(function(K) {
                var editor1 = K.create('textarea[name="content"]', {
                    cssPath: '../../kindeditor/plugins/code/prettify.css',
                    uploadJson: '../../kindeditor/jsp/upload_json.jsp',
                    fileManagerJson: '../../kindeditor/jsp/file_manager_json.jsp',
                    allowFileManager: true,
                    afterCreate: function() {
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
            <form action="<%=path%>/ManaNews" method="post">
                <input type="hidden" name="id" value="<%=id%>">
                <table border="0">
                    <tr>
                        <td style="font-size: 11px;">标题</td>
                        <td><input type="text" name="title" size="50"/></td>
                    </tr>
                    <tr>
                        <td style="font-size: 11px;">内容</td>
                        <!--                         <td><input type="text" name="content" size="50"/></td>-->
                        <td>
                            <textarea name="content" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"></textarea>
                        </td>
                    </tr>

                    <tr>
                        <td style="font-size: 11px;">&nbsp;</td>
                        <td>
                            <input type="submit" value="提交新闻">
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
