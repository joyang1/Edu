/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.servlet.jsonnotice;

import com.dao.SqlHelper;
import com.slv.util.GetJson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joyang
 */
public class GetNoticeContentServlet extends HttpServlet {

    private SqlHelper helper;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
 methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        int type = Integer.parseInt(request.getParameter("type"));
        ArrayList al = new ArrayList();
        helper = new SqlHelper();
        int article_id = type;
        String content = "";
        String sql = "select * from o_notice where notice_id = ?";
        al = helper.getArrayList(sql, article_id);   
        content = GetJson.getNoticeContentJson(al);
        out.println(content);  
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
