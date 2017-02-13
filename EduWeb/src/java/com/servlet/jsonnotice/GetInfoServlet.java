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
 * @author 挺
 */
public class GetInfoServlet extends HttpServlet {

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
        response.setContentType("text/html; utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        helper = new SqlHelper();
        String flag = request.getParameter("flag");   //标志(公共info-public, 个人info-personal)
        String user_no = request.getParameter("userid");
        String sql = "";
        String content = "";
        ResultSet rs = null;
        if (user_no.equals("null") || user_no.equals("undefined")) {
            sql = "select * from o_notice where notice_to_dep=0 and notice_to_major=0 and notice_flag = 1 order by notice_id desc limit 10";
            rs = helper.getRs(sql);
            try {
                content = GetJson.getIndexInfoNotJson(rs);
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        } else {
            if (flag.equals("public")) {
                sql = "select * from o_notice where notice_to_dep=0 and notice_to_major=0 and notice_flag = 1 order by notice_id desc limit 10";
                rs = helper.getRs(sql);
                try {
                    content = GetJson.getIndexInfoNotJson(rs);
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            } else if (flag.equals("personal")) {
                sql = "SELECT a.notice_id as notice_id,a.notice_title as notice_title, "
                        + "a.notice_date as notice_date, a.notice_time as notice_time, "
                        + "a.notice_indate as notice_indate "
                        + "FROM o_notice a, b_user b "
                        + "WHERE ((a.notice_to_dep = b.user_dep  AND  a.notice_to_major = 0 ) "
                        + "OR (a.notice_to_major <> 0 AND  a.notice_to_dep = b.user_dep AND a.notice_to_major = b.user_major)) "
                        + "and b.user_no = '" + user_no + "' and notice_flag = 1 order by notice_id desc limit 10";
                rs = helper.getRs(sql);
                try {
                    content = GetJson.getIndexInfoNotJson(rs);
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }        
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
