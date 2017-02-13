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
public class IsLoginServlet extends HttpServlet {
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
        //request.setCharacterEncoding("utf-8");
        helper = new SqlHelper();
        String username = request.getParameter("username");
        String json = "";
        String sql = "select bak_extend1,user_id,user_name from b_user where user_no='" + username + "'";
        ResultSet rs = helper.getRs(sql);
        try {
            if (rs.next()) {
                String islogin = rs.getString(1);
                if(islogin.equals("1")){
                    json = "itemsSearchGet({\"data\":\"1\",\"userid\":\""+rs.getString(2)+"\",\"name\":\""+rs.getString(3)+"\"})";  //1表示已经登陆过
                }else{
                    json = "itemsSearchGet({\"data\":\"0\"})";  //0表示未登陆过
                }
            }
        }catch(SQLException e){
            e.printStackTrace(System.err);
        }finally{
            helper.close();
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
