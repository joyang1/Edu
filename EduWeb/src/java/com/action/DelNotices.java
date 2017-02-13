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
public class DelNotices extends HttpServlet {

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
        String[] flag = new String[allnoticescount];
        int[] id = new int[allnoticescount];
        id = gn.getId();
        int tid = Integer.parseInt(request.getParameter("id"));
        int startnotices = allnoticescount-1; //每页开始通知的序号
//        for (int i = startnotices; i >= 0; i--) {
//            flag[i] = gn.delNotices(id[i]);
//            out.println("flag"+flag[i]);
//        }
        if(gn.delNotices(tid)){
            out.println("chenggong"+gn.delNotices(tid));
        }else {
            out.println("shibai"+gn.delNotices(tid));
        }
    }

}
