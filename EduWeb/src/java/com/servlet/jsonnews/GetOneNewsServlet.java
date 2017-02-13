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
 * @author 小小一
 */
public class GetOneNewsServlet extends HttpServlet {

    SqlHelper helper = null;
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        int article_id = Integer.parseInt(request.getParameter("id"));
        //ArrayList al = new ArrayList();
        helper = new SqlHelper();
        String content = "";
        String sql = "select * from o_article where article_id = " + article_id + "";
//        al = helper.getArrayList(sql, article_id);
        ResultSet rs = helper.getRs(sql);
        try {
//            if (rs.next()) {
//                content = rs.getString("article_content");
//            }
            content = GetJson.getNewsContentJson(rs);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        //request.setAttribute("content", content);
        out.println(content);
        //request.getRequestDispatcher("showpage/oneNews.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
