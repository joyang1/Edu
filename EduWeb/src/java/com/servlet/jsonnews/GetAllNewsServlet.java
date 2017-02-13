/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.jsonnews;

import com.dao.SqlHelper;
import com.slv.util.GetJson;
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
 * @author 挺
 */
public class GetAllNewsServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        String dep_id = request.getParameter("depid");
        String last_id = request.getParameter("lastid");
        
        ResultSet rs = null;
        helper = new SqlHelper();
        String content = "";
        String sql = "";
        if(last_id.equals("undefined")){
             sql = "SELECT * FROM o_article WHERE article_dep = " + dep_id + "";
        }else{
            sql = "SELECT * FROM o_article WHERE article_id > " + last_id + " AND article_dep = " + dep_id + "";
        }
        
        rs = helper.getRs(sql);
        try {
            content = GetJson.getTitleJson(rs,dep_id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        out.println(content);
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
