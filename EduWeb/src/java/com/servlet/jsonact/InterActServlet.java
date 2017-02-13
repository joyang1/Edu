/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.servlet.jsonact;

import com.dao.SqlHelper;
import com.slv.gettime.GetTime;
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
public class InterActServlet extends HttpServlet {
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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String ticket_id = request.getParameter("ticketid");
        String acontent = request.getParameter("content");
        String date = GetTime.getDate();
        String time = GetTime.getTime();
        helper = new SqlHelper();
        String act_id = "";
        String  user_no = "";
        int flag = 0; 
        String sql1 = "select act_id,user_no from c_act_ticket where act_ticket_id = "+ ticket_id +"";
        ResultSet rs1 = helper.getRs(sql1);
        try {
            if(rs1.next()) {
                act_id = rs1.getString("act_id");
                user_no = rs1.getString("user_no");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        String sql2 = "insert into c_interaction values("+ null +",'"+ act_id +"','"+ user_no +"','"+ acontent +"','"+ date +"','"+ time +"','"+ 0 +"','"+ 1 +"')";
        if(helper.SqlQuery(sql2) != 0) {
            //flag为0时失败，1时成功
            flag = 1;
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
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
