/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.javabean.news.GetNews;
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
 * 1.doGet将数据库中的新闻读出来发送给前台页面
 * 
 * @author slv
 */
public class index extends HttpServlet {

    private GetNews gn;
    private int allnewscount;
    private GetNotices gno;
    private int allnoticescount;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();  
        gno = new GetNotices();
        allnoticescount = gno.getAllNoticesCount();
        String[] no_title = new String[allnoticescount];
        String[] no_time = new String[allnoticescount];
        int[] no_id = new int[allnoticescount];
        no_id = gno.getId();
        int startnotices = allnoticescount-1; //每页开始通知的序号
        for (int i = startnotices; i >= 0; i--) {
            no_title[i] = gno.getTitle(no_id[i]);
            no_time[i] = gno.getTime(no_id[i]);
            request.setAttribute("no_title" + i,no_title[i]);
            request.setAttribute("no_time" + i, no_time[i]);
        }
        request.setAttribute("startnotices", startnotices);
        request.setAttribute("allnoticescount", allnoticescount);
        
        gn = new GetNews();
        allnewscount = gn.getAllNewsCount();
        String[] title = new String[allnewscount];
        String[] time = new String[allnewscount];
        int[] id = new int[allnewscount];
        id = gn.getId();
        int startnews = allnewscount-1; //每页开始新闻的序号
        for (int i = startnews; i >= 0; i--) {
            title[i] = gn.getTitle(id[i]);
            time[i] = gn.getTime(id[i]);
            request.setAttribute("title" + i, title[i]);
            request.setAttribute("time" + i, time[i]);
        }
        request.setAttribute("startnews", startnews);
        request.setAttribute("allnewscount", allnewscount);
        request.getRequestDispatcher("qiantai/index.jsp").forward(request, response);
        
    }
}
