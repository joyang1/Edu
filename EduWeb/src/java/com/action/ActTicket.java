/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.action;

import com.dao.SqlHelper;
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
public class ActTicket extends HttpServlet {

    private SqlHelper helper = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String actid = request.getParameter("actid");
        int i = 0;
        int rows = 0;
        String userno[];
        String username[];
        helper = new SqlHelper();
        String sql = "select t.user_no,u.user_name from c_act_ticket t,b_user u where t.act_id = "+ actid +" and u.user_no = t.user_no";
        ResultSet rs = helper.getRs(sql);
        try {
            rs.last();
            rows = rs.getRow();
            userno = new String[rows];
            username = new String[rows];
            rs.beforeFirst();
            while(rs.next()) {
                userno[i] = rs.getString("user_no");
                username[i] = rs.getString("user_name");
                request.setAttribute("userno" + i, userno[i]);
                request.setAttribute("username" + i, username[i]);
            }
            request.setAttribute("rows", rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        request.getRequestDispatcher("admin/actions/actTicket.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

}
