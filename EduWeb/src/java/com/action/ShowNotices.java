/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.javabean.notices.GetNotices;
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
public class ShowNotices extends HttpServlet {

    private GetNotices gn;
    private int allnoticescount;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        gn = new GetNotices();
        allnoticescount = gn.getAllNoticesCount();
        String[] content = new String[allnoticescount];
        String[] title = new String[allnoticescount];
        int[] id = new int[allnoticescount];
        id = gn.getId();
        //从前台传过来的id
        int tid = Integer.parseInt(request.getParameter("id"));
        for (int i = allnoticescount - 1; i >= 0; i--) {
            title[i] = gn.getTitle(id[i]);
            content[i] = gn.getContent(id[i]);
        }
        request.setAttribute("title" , title[tid]);
        request.setAttribute("content" , content[tid]);
        if(id.length>0){
//            out.print("title"+title[1]+"content"+content[1]);
            request.getRequestDispatcher("admin/notices/noticesDetial.jsp").forward(request, response);
        }

    }

}
