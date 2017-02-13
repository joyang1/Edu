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
public class AddActOption extends HttpServlet {

    private SqlHelper helper = null;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String voteid = request.getParameter("voteid");
        helper = new SqlHelper();
        int i = 0;
        int rows = 0;
        String[] option;
        String sql = "select vote_option from c_act_vote_option where vote_id = "+ voteid +"";
        ResultSet rs = helper.getRs(sql);
        try {
            rs.last();
            rows = rs.getRow();
            option = new String[rows];
            rs.beforeFirst();
            while(rs.next()) {
                option[i] = rs.getString("vote_option");
                request.setAttribute("option" + i, option[i]);
                i++;
            }
            request.setAttribute("rows",rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        request.setAttribute("voteid", voteid);
        request.getRequestDispatcher("admin/actions/actOptionAdd.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        helper = new SqlHelper();
        String vote_id = request.getParameter("voteid");
        String vote_option = request.getParameter("vote_option");
        out.println(vote_id);
        String sql = "insert into c_act_vote_option values(" + null + ",'" + vote_id + "','"+ vote_option +"','1')";
        if(helper.SqlQuery(sql) != 0) {
            this.doGet(request, response);
        } else {
            out.println("false");
        }
    }

}
