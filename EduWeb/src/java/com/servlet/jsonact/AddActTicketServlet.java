/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.jsonact;

import com.dao.SqlHelper;
import com.javabean.act.GetAct;
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
public class AddActTicketServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        String user_no = request.getParameter("userid");
        String act_id = request.getParameter("actid");
        helper = new SqlHelper();
        GetAct at = new GetAct();
        String content = "";
        int flag1 = 0;
        int flag2 = 0;
        //根据活动act_id得到活动地点place_id
        String sql1 = "select * from c_act where act_id = " + act_id + "";
        ResultSet rs1 = helper.getRs(sql1);
        //根据活动act_id得到一共有多少人参与该活动
        String sql2 = "select * from c_act_ticket where act_id = " + act_id + "";
        ResultSet rs2 = helper.getRs(sql2);
        //根据用户的user_no得到其参与的所有活动act_id
        String sql3 = "select act_id from c_act_ticket where user_no = '" + user_no + "'";
//        out.println(sql3);
        ResultSet rs3 = helper.getRs(sql3);
        try {
            while (rs3.next()) {
                //判断user_no的用户是否已经抢过act_id的票
                if (rs3.getString("act_id").equals(act_id)) {
                    flag2 = 1;
                }
            }
            rs2.last();
            int rows = rs2.getRow();
            //判断指定act_id的票是否还有余票
            if (flag2 == 0) {
                if (rs1.next()) {
                    String place_id = rs1.getString("place_id");
                    String act_seat_id = "1";
                    String sql4 = "SELECT s.seat_id as new_seat_id "
                            + "FROM b_seat s, "
                            + " b_place p "
                            + "WHERE p.place_id = s.place_id "
                            + "  AND p.place_id = "+ rs1.getString("place_id") +" "
                            + "  AND s.seat_flag = '1' "
                            + "  AND s.hold_seat_flag = '0' "
                            + "  AND s.seat_id NOT IN (SELECT distinct t.act_seat_id FROM c_act_ticket t where place_id = "+ rs1.getString("place_id") +")";
                    ResultSet rs4 = helper.getRs(sql4);
                    if(rs4.next()) {
                        act_seat_id = rs4.getString("new_seat_id");
                    }
                    String act_ticket_no = act_id + "dlpu";
                    String act_ticket_code = "1";
                    if (rows < rs1.getInt("act_ticket_num")) {
                        if (at.addActTicket(act_id, act_seat_id, act_ticket_no, act_ticket_code, user_no, place_id)) {
                            flag1 = 1;
                        } else {
                            flag1 = 0;
                        }
                    }
                }
            }
            content = GetJson.getActFlagJson(flag1, flag2);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
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
