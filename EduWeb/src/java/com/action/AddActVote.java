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
public class AddActVote extends HttpServlet {

    private SqlHelper helper = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String actid = request.getParameter("actid");
        helper = new SqlHelper();
        int i = 0;
        int rows = 0;
        String[] title;
        String[] voteid;
        String sql = "select vote_title,vote_id from c_act_vote where act_id = "+ actid +"";
        ResultSet rs = helper.getRs(sql);
        try {
            rs.last();
            rows = rs.getRow();
            title = new String[rows];
            voteid = new String[rows];
            rs.beforeFirst();
            while(rs.next()) {
                title[i] = rs.getString("vote_title");
                voteid[i] = rs.getString("vote_id");
                request.setAttribute("voteid" + i, voteid[i]);
                request.setAttribute("title" + i, title[i]);
                i++;
            }
            request.setAttribute("rows", rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        request.setAttribute("actid", actid);
//        out.println(actid);
        request.getRequestDispatcher("admin/actions/actVoteAdd.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        helper = new SqlHelper();
        String act_id = request.getParameter("actid");
        String vote_title = request.getParameter("vote_title");
        String vote_type = request.getParameter("vote_type");
        String vote_select_type = request.getParameter("vote_select_type");
        String vote_option = request.getParameter("vote_option");
        char flag = '1';
        String sql = "insert into c_act_vote values(" + null + ",'" + act_id + "','" + vote_title + "','" + vote_type + "','" + vote_select_type + "','" + 0 + "','" + flag + "')";
        if(helper.SqlQuery(sql) != 0) {
            this.doGet(request, response);
//            request.setAttribute("actid", act_id);
//            request.getRequestDispatcher("admin/actions/actOptionAdd.jsp").forward(request, response);
        } else {
            out.println("false1");
//            this.doGet(request, response);
        }
    }

}
