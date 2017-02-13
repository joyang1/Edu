/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.action;

import com.javabean.notices.GetNotices;
import com.slv.gettime.GetTime;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 1.doGet将数据库中的通知读出来发送给前台页面
 * 
 * 2.doPost将管理员添加的通知写进数据库
 * 
 * @author slv
 */
public class ManaNotices extends HttpServlet {

    private GetNotices gn;
    private int allnoticescount;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();        
        gn = new GetNotices();
        allnoticescount = gn.getAllNoticesCount();
        String[] title = new String[allnoticescount];
        String[] time = new String[allnoticescount];
        int[] id = new int[allnoticescount];
        id = gn.getId();
        int startnotices = allnoticescount-1; //每页开始通知的序号
        for (int i = startnotices; i >= 0; i--) {
            title[i] = gn.getTitle(id[i]);
            time[i] = gn.getTime(id[i]);
            request.setAttribute("title" + i, title[i]);
            request.setAttribute("time" + i, time[i]);
        }
        request.setAttribute("startnotices", startnotices);
        request.setAttribute("allnoticescount", allnoticescount);
        request.getRequestDispatcher("admin/notices/noticesMana.jsp").forward(request, response); 
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        GetNotices gn = new GetNotices();
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String indate = request.getParameter("indate");
        String add = request.getParameter("add");
        String dep = request.getParameter("dep");
//        String click = new String(request.getParameter("click").getBytes("iso-8859-1"), "utf-8");
//        String D_Picture = new String(request.getParameter("D_Picture").getBytes("iso-8859-1"), "utf-8");
//        String D_OriginalFileName = new String(request.getParameter("D_OriginalFileName").getBytes("iso-8859-1"), "utf-8");
//        String D_SaveFileName = new String(request.getParameter("D_SaveFileName").getBytes("iso-8859-1"), "utf-8");
//        String D_SavePathFileName = new String(request.getParameter("D_SavePathFileName").getBytes("iso-8859-1"), "utf-8");
//        String article_flag = new String(request.getParameter("article_flag").getBytes("iso-8859-1"), "utf-8");
        String click = "1";
        String D_Picture = "1";
        String D_OriginalFileName = "1";
        String D_SaveFileName = "1";
        String D_SavePathFileName = "1";
        String article_flag = "1";
        String data = GetTime.getDate();
        String time = GetTime.getTime();
        if (gn.addnotices(title, add, dep, content, indate, data, time, click, D_Picture, D_OriginalFileName, D_SaveFileName, D_SavePathFileName, article_flag)) {
            out.print("添加成功"+"content"+content);
        } else {
            out.println("添加失败"+"content"+content+"title"+title+"time"+time);
        }
    }

}
