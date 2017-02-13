/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.javabean.news.GetNews;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author slv
 */
public class ShowNews extends HttpServlet {

    private GetNews gn;
    private int allnewscount;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        gn = new GetNews();
        allnewscount = gn.getAllNewsCount();
        String[] content = new String[allnewscount];
        String[] title = new String[allnewscount];
        int[] id = new int[allnewscount];
        id = gn.getId();
        //从前台传过来的id
        int tid = Integer.parseInt(request.getParameter("id"));
        for (int i = allnewscount - 1; i >= 0; i--) {
            title[i] = gn.getTitle(id[i]);
            content[i] = gn.getContent(id[i]);
        }
        request.setAttribute("title" , title[tid]);
        request.setAttribute("content" , content[tid]);
        if(id.length>0){
            request.getRequestDispatcher("admin/news/newsDetial.jsp").forward(request, response);
        }

    }

}
