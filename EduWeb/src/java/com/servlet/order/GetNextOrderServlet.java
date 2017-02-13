/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.servlet.order;

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
public class GetNextOrderServlet extends HttpServlet {
    private SqlHelper helper = null;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
         String user_no = request.getParameter("userid");
        String first_id = request.getParameter("firstid");
        String dep_type = request.getParameter("type"); //dep_type为0是校内，1是校外
        helper = new SqlHelper();
        String content = "";
        String sql = null;
        if(dep_type.equals("0")) {
            sql = "select p.order_pub_id,p.order_pub_title,p.order_pub_content,p.order_pub_date,p.order_pub_time from o_order_pub p,o_in_order i where p.order_pub_id < "+ first_id +" and p.order_pub_flag = '1' and p.order_dep_id = i.order_dep_id and i.user_no = "+ user_no +" order by p.order_pub_id desc limit 0,10";
        } else {
            sql = "select p.order_pub_id,p.order_pub_title,p.order_pub_content,p.order_pub_date,p.order_pub_time from o_order_pub p,o_out_order o where p.order_pub_id < "+ first_id +" and p.order_pub_flag = '1' and p.order_dep_id = o.order_dep_id and o.user_no = "+ user_no +" order by p.order_pub_id desc limit 0,10";
        }
        ResultSet rs = helper.getRs(sql);
        try {
            content = GetJson.getOrderTitleJson(rs);
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }
        out.println(content);


    }

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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
