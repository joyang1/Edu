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
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joyang
 */
public class GetNoticeTitleServlet extends HttpServlet {

    private SqlHelper helper;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        String user_no = request.getParameter("userid");
        helper = new SqlHelper();
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        String content = "";
        String sql1 = "select * from o_notice where notice_to_dep = 0 and notice_to_major = 0 and notice_flag = 1 order by notice_id desc limit 0,5";
        rs1 = helper.getRs(sql1);
        if (user_no.equals("null") || user_no.equals("undefined")) {
            try {
                content = GetJson.getIndexInfoNotJson(rs1);
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        } else {
           String sql2 = "SELECT a.notice_id as notice_id,a.notice_title as notice_title, "
                + "a.notice_date as notice_date, a.notice_time as notice_time, "
                + "a.notice_indate as notice_indate "
                + "FROM o_notice a, b_user b "
                + "WHERE ((a.notice_to_dep = b.user_dep  AND  a.notice_to_major = 0 ) "
                + "OR (a.notice_to_major <> 0 AND  a.notice_to_dep = b.user_dep AND a.notice_to_major = b.user_major)) "
                + "and b.user_no ='"+user_no+"' order by notice_id desc limit 0,5";
            rs2 = helper.getRs(sql2);  
            //String sql = "select notice_id,notice_title,notice_to_dep,major_to_major from o_notice where notice_flag = 1 order by notice_id desc limit 0,5";         
            try {
                content = GetJson.getIndexInfosJson(rs1, rs2);
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }
        out.print(content);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
