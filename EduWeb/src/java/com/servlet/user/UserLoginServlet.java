/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.user;

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
 * @author 挺
 */
public class UserLoginServlet extends HttpServlet {

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
        helper = new SqlHelper();
        PrintWriter out = response.getWriter();
        String u_no = request.getParameter("username");  //学生学号，老师工号
        String u_pass = request.getParameter("passwd");
        String json = "";
        ResultSet rs = helper.getRs("select user_pass,user_right from b_user where user_no ='" + u_no + "'");
        try {
            if (rs.next()) {
                String realpasswd = rs.getString(1);
                String user_right = rs.getString(2);   //用户权限
                if (u_pass.equals(realpasswd)) {
                    String sql = "update b_user set bak_extend1 = 1 where user_no='" + u_no + "'";
                    if (helper.SqlQuery(sql) != 0) {
                        json += "itemsSearchGet({\"data\":\"true\",\"right\":\"" + user_right + "\"})";
                    }
                } else {
                    json = "itemsSearchGet({\"data\":\"1\",\"right\":\"0\"})";  //密码错误
                }
            } else {
                json = "itemsSearchGet({\"data\":\"0\",\"right\":\"0\"})";  //用户名不存在
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        out.write(json);
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
