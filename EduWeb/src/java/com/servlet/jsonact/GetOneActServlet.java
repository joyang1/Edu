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
 * @author 挺
 */
public class GetOneActServlet extends HttpServlet {

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
        String act_id = request.getParameter("actid");
        String user_no = request.getParameter("userid");
        helper = new SqlHelper();
        String content = "";
        int flag1 = 0; //是否还有余票
        int flag2 = 0; //是否已经抢票
        int ticket_id = 0;
        String sql1 = "select * from c_act where act_id = " + act_id + "";
        ResultSet rs1 = helper.getRs(sql1);
        String sql5 = "select act_id from c_act_ticket where user_no = " + user_no + "";
        ResultSet rs5 = helper.getRs(sql5);
        try {
            if (rs1.next()) {
                String sql2 = "select admin_name from b_admin where admin_id = '" + rs1.getString("act_add") + "'";
                String sql3 = "select place_name from b_place where place_id = " + rs1.getString("place_id") + "";
                ResultSet rs2 = helper.getRs(sql2);
                ResultSet rs3 = helper.getRs(sql3);
                String sql4 = "select * from c_act_ticket where act_id = " + act_id + "";
                ResultSet rs4 = helper.getRs(sql4);
                rs4.last();
                int rows = rs4.getRow();
                //判断指定act_id的票是否还有余票："1"有，"0"无
                if (rows < rs1.getInt("act_ticket_num")) {
                    flag1 = 1;
                }
                //判断指定user_no用户是否已抢票："1"已抢，"0"未抢
                while (rs5.next()) {
                    if (rs5.getString("act_id").equals(act_id)) {
                        String sql6 = "select act_ticket_id from c_act_ticket where user_no='"+user_no+"' and act_id ='"+act_id+"'";
                        ResultSet rs6 = helper.getRs(sql6);
                        if(rs6.next()){
                            ticket_id = rs6.getInt(1);
                        }
                        flag2 = 1;
                    }
                }
                content = GetJson.getActContentJson(rs1, rs2, rs3, flag1, flag2,ticket_id);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
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

}
