/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.jsonact;

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
public class GetOrderListServlet extends HttpServlet {
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
        String user_no = request.getParameter("userid");
        String order_type = request.getParameter("ordertype");
        helper = new SqlHelper();
        String content = "";
        String sql = "select order_dep_id,order_dep_name from b_order_dep where order_dep_type = '"+order_type+"' and  order_dep_id not in (select order_dep_id from o_in_order  where user_no = '"+ user_no +"') and  order_dep_id not in (select order_dep_id from o_out_order  where user_no = '"+ user_no +"') and order_dep_id not in (select user_dep from b_user where user_no = '"+ user_no +"')";
        ResultSet rs = helper.getRs(sql);
        try {
            content = GetJson.getOrderListJson(rs);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
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
