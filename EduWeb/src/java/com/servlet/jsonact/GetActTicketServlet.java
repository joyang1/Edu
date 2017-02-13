/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.jsonact;

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
public class GetActTicketServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String act_ticket_id = request.getParameter("ticketid");
        helper = new SqlHelper();
        String content = "";
        String sql1 = "select act_ticket_no,user_no,place_id,act_seat_id,act_id from c_act_ticket where  act_ticket_flag = 1 and act_ticket_id =" + act_ticket_id + "";
        ResultSet rs1 = helper.getRs(sql1);
        try {
            if (rs1.next()) {
                String sql2 = "select * from b_user where user_no = " + rs1.getString("user_no") + "";
                ResultSet rs2 = helper.getRs(sql2);
                String sql3 = "select place_name from b_place where place_id = " + rs1.getString("place_id") + "";
                ResultSet rs3 = helper.getRs(sql3);
                String sql4 = "select seat_name from b_seat where seat_id = " + rs1.getString("act_seat_id") + "";
                ResultSet rs4 = helper.getRs(sql4);
                String sql5 = "select d.dep_name,a.act_name,a.act_begin_date,a.act_begin_time,a.act_end_time, a.act_end_date from b_dep d, c_act a where a.act_id = " + rs1.getString("act_id") + " and d.dep_id = a.dep_id";
                ResultSet rs5 = helper.getRs(sql5);
                content = GetJson.getActTicketJson(rs1, rs2, rs3, rs4, rs5);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }finally{
            helper.close();
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
