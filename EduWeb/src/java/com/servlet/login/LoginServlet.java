/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.login;

import com.javabean.login.Login;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author slv
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        //out.print("asd");
        String admin_id = request.getParameter("loginname");
        String admin_pass = request.getParameter("loginpw");
        HttpSession session = request.getSession();

        Login lg = new Login();
        if (lg.checkadmin(admin_id, admin_pass)) {
            //管理员登陆成功
            session.setAttribute("admin_id", admin_id);
            response.sendRedirect("admin/center.jsp");
        } else {
            //用户登陆失败
            response.sendRedirect("index");
        }

    }
}
