/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.dao.SqlHelper;
import com.javabean.news.GetNews;
import com.slv.gettime.GetTime;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 1.doGet将数据库中的新闻读出来发送给前台页面
 * 
 * 2.doPost将管理员添加的新闻写进数据库
 * 
 * @author slv
 */
public class ManaNews extends HttpServlet {
    private SqlHelper helper = null;
    private GetNews gn;
    private int allnewscount;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();        
        gn = new GetNews();
        allnewscount = gn.getAllNewsCount();
        String[] title = new String[allnewscount];
        String[] time = new String[allnewscount];
        int[] id = new int[allnewscount];
        id = gn.getId();
        int startnews = allnewscount-1; //每页开始新闻的序号
//        out.print("st"+startnews);
//        out.print("id"+id[0]);
        for (int i = startnews; i >= 0; i--) {
            title[i] = gn.getTitle(id[i]);
            time[i] = gn.getTime(id[i]);
            request.setAttribute("title" + i, title[i]);
            request.setAttribute("time" + i, time[i]);
        }
        request.setAttribute("startnews", startnews);
        request.setAttribute("allnewscount", allnewscount);
        request.getRequestDispatcher("admin/news/newsMana.jsp").forward(request, response); 
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        GetNews gn = new GetNews();
        helper = new SqlHelper();
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String add = request.getParameter("id");
        String sql = "select admin_dep from b_admin where admin_id = '"+ add +"'";
        ResultSet rs = helper.getRs(sql);
        String dep = "";
        try {
            if(rs.next()) {
                dep = rs.getString("admin_dep");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }

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
        if (gn.addnews(title, add, dep, content, data, time, click, D_Picture, D_OriginalFileName, D_SaveFileName, D_SavePathFileName, article_flag)) {
            out.print("添加成功"+"content"+content);
        } else {
            out.println("添加失败"+"content"+content+"title"+title+"time"+time);
        }
    }

}
