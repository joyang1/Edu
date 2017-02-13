/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.servlet.user;

import com.dao.SqlHelper;
import com.slv.util.GetJson;
import com.slv.util.GetTime;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 挺
 */
public class OwnInfoServlet extends HttpServlet {
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
        PrintWriter out = response.getWriter();
        
        helper = new SqlHelper();
        String user_no = request.getParameter("userid");
        String sql = "select user_id, user_birthday,user_type,user_name, user_no, user_sex, user_class from b_user where user_no='" + user_no + "'";
        ResultSet rs = helper.getRs(sql);
        String content ="";
        try {            
            content = GetJson.getOwnInfo(rs);         
        } catch (SQLException e) {
            e.printStackTrace(System.err);
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
