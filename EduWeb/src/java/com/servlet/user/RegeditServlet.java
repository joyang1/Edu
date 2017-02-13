/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.user;

import com.dao.SqlHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 挺
 */
public class RegeditServlet extends HttpServlet {
    private SqlHelper helper = null;
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("utf-8");
        helper = new SqlHelper();
        PrintWriter out = response.getWriter();
        String u_name = request.getParameter("username");
        String u_pass = request.getParameter("passwd");
        String sql = "insert into b_user values(" + null + ",'" + u_name + "','" + null + "','" + 0 + "','" + 0 + "', '" + u_pass + "','" + null + "'"
                + ",'" + 0 + "','" + null + "','" + null + "','" + new Date(0) + "'," + 0 + "," + 0 + ",'" + null + "','" + null + "','" + null + "','" + null + "','" + null + "','" + 0 + "'"
                + ",'" + 0 + "','" + null + "','" + null + "','" + null + "','" + null + "','" + null + "','" + null + "')";
        if (helper.Inserter(sql)) {
            //response.sendRedirect("login/login.jsp");
            String json = "";
            json += "itemsSearchGet({\"success\":\"true\"})";
            out.println(json);
        } else {
            //response.sendRedirect("fail/fail.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
