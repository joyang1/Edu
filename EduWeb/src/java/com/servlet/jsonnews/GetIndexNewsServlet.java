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
 * @author joyang
 */
public class GetIndexNewsServlet extends HttpServlet {

    private SqlHelper helper;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
        //int type = 23;
        String user_no = request.getParameter("userid");
        helper = new SqlHelper();
        ResultSet allrs = helper.getRs("select dep_id,dep_name from b_dep where dep_level = 1");  //得到所有的发公共通知的部门
//        String dep_id = "23";
        ResultSet rs2 = null;
        String dep_name = null;
        int dep_id = 0;
//        String sql = "select * from o_article where article_dep = '" + dep_id + "' and article_flag=1 order by article_id desc limit 0,3";
//        ResultSet rs1 = helper.getRs(sql);  //得到教务处新闻的结果集
        String content = "";
        if (user_no.equals("null") || user_no.equals("undefined")) {
            try {
                content = GetJson.getIndexNewsNotJson(allrs);
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        } else {
            ResultSet rs = helper.getRs("select user_dep from b_user where user_no = '" + user_no + "'");   //得到学院id
            try {
                if (rs.next()) {
                    String user_dep = rs.getString(1);
                    rs2 = helper.getRs("select * from o_article where article_dep='" + user_dep + "' and article_flag=1 order by article_id desc limit 0,3"); //得到用户所属学院的最新三条新闻
                    ResultSet rs3 = helper.getRs("select dep_id,dep_name from b_dep where dep_id=" + user_dep + "");  //得到学院名
                    if (rs3.next()) {
                        dep_id = rs3.getInt(1);
                        dep_name = rs3.getString(2);
                    }
                    content = GetJson.getIndexNewsJson(allrs, rs2, dep_id,dep_name);
                }
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            } finally {
                helper.close();
            }
        }
        out.println(content);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
