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
 * 用来完善用户个人信息的后台逻辑处理
 *
 * @author 挺
 */
public class FinishServlet extends HttpServlet {

    private SqlHelper helper = null;
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        String data = "";  //返回的数据
        String user_no = request.getParameter("userid");
        String person = request.getParameter("person");
        String sql1 = "select * from b_stu_base where stu_no = '" + user_no + "'";
        ResultSet rs = helper.getRs(sql1);
        try {
            if (rs.next()) {
                String user_name = rs.getString("stu_name");
                String user_sex = rs.getString("stu_sex");
                String user_birthday = rs.getString("stu_birthday");
                String user_dep = rs.getString("stu_dep");
                String user_major = rs.getString("stu_major");
                String user_class = rs.getString("stu_class");
                String user_year = rs.getString("stu_year");
//                out.print("name:"+name+" sex:"+sex+"bir:"+birthday+" dep:"+dep+" major:"+major+"user_class"+user_class+" year:"+year);
                String sql = "update b_user "
                        + "set user_no = '" + user_no + "',user_type = '1',work_type = '0',"
                        + "user_name = '" + user_name + "',user_sex = '" + user_sex + "',user_p_no = '" + person + "',"
                        + "user_birthday = '" + user_birthday + "',"
                        + "user_dep = '" + user_dep + "',user_major = '" + user_major + "',"
                        + "user_class = '" + user_class + "',user_year = '" + user_year + "',user_right ='1'"
                        + "where user_no ='" + user_no + "'";
                if (helper.SqlQuery(sql) != 0) {
                    data = "itemsSearchGet({\"right\":\"1\"})";
                    out.println(data);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }

//        try {
//            if (rs.next()) {
//                String user_right = rs.getString(1);
//                if (user_right.equals("0")) {
//                    out.println("aasd");
//                } else {
//                    out.println("aa");
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace(System.err);
//        } finally {
//            helper.close();
//        }
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
