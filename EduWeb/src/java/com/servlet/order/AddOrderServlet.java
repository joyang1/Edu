/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.order;

import com.dao.SqlHelper;
import com.slv.gettime.GetTime;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 挺
 */
public class AddOrderServlet extends HttpServlet {

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
        String order_dep_id = request.getParameter("depid");
        String type = request.getParameter("type");
        helper = new SqlHelper();
        int flag = 0;
        String order_date = GetTime.getDate();
        String order_time = GetTime.getTime();
        String order_file = "1";
         String sql = "";
        if (type.equals("0")) {
            sql = "insert into o_in_order values(" + null + ",'" + user_no + "','" + order_dep_id + "','1','" + order_date + "','" + order_time + "','1','" + order_file + "')";
        }else if(type.equals("1")){
            sql = "insert into o_out_order values(" + null + ",'" + user_no + "','" + order_dep_id + "','1','" + order_date + "','" + order_time + "','1','" + order_file + "')";
        }
        if (helper.SqlQuery(sql) != 0) {
            flag = 1;
        };
        String content = "itemsSearchGet({\"flag\":\"" + flag + "\"})";
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
