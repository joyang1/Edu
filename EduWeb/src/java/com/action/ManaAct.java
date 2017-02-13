/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.dao.SqlHelper;
import com.javabean.act.GetAct;
import com.javabean.admin.GetAdmin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ManaAct extends HttpServlet {

    private GetAct ac;
    private int allactcount;
    private SqlHelper helper;
    private GetAdmin ad;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();        
        ac = new GetAct();
        allactcount = ac.getAllActCount();
        String[] name = new String[allactcount];
        int[] id = new int[allactcount];
        id = ac.getId();
        int startact = allactcount-1; //每页开始活动的序号
//        out.print("st"+startnews);
//        out.print("id"+id[0]);
        for (int i = startact; i >= 0; i--) {
            name[i] = ac.getName(id[i]);
            request.setAttribute("actid" + i, id[i]);
            request.setAttribute("title" + i, name[i]);
        }
        request.setAttribute("startact", startact);
        request.setAttribute("allactcount", allactcount);
        request.getRequestDispatcher("admin/actions/actMana.jsp").forward(request, response); 
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        GetAct ac = new GetAct();
        helper = new SqlHelper();
        String name = request.getParameter("A_name");
        String content = request.getParameter("content");
        String org_id = "1";
        String type = request.getParameter("A_type");    
        String add = request.getParameter("id");
        
        String sql = "select admin_dep from b_admin where admin_id = '"+ add +"'";
        ResultSet rs = helper.getRs(sql);
        String dep = "";
        try {
            if(rs.next()) {
                dep = rs.getString("admin_dep");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        
        String level =request.getParameter("A_level");
        String begindate = request.getParameter("A_begin_date");
        String enddate = request.getParameter("A_end_date");
        String begintime_h = request.getParameter("A_begin_time_h");
        String begintime_m = request.getParameter("A_begin_time_m");
        String begintime = begintime_h + ":" +begintime_m + ":00";
        String endtime_h = request.getParameter("A_end_time_h");
        String endtime_m = request.getParameter("A_end_time_m");
        String endtime = endtime_h + ":" +endtime_m + ":00";
//        String add = new String(request.getParameter("A_add").getBytes("iso-8859-1"), "utf-8");
        String actflag = "1";
        String place = new String(request.getParameter("A_place_id").getBytes("iso-8859-1"), "utf-8");
        String D_Picture = "1";
        String D_OriginalFileName = "1";
        String D_SaveFileName = "1";
        String D_SavePathFileName = "1";
        
        String ticketflag = new String(request.getParameter("A_ticket_flag").getBytes("iso-8859-1"), "utf-8");
        String ticketnum = "0";
        if ("1".equals(ticketflag)){
            ticketnum = new String(request.getParameter("A_ticket_num").getBytes("iso-8859-1"), "utf-8");
        }
        String ticketend = new String(request.getParameter("A_ticket_end").getBytes("iso-8859-1"), "utf-8");
        if (ac.addact(org_id, type, dep, name, level, begindate, enddate, begintime, endtime, content, actflag, add, D_Picture, D_OriginalFileName, D_SaveFileName, D_SavePathFileName, ticketflag, ticketnum, ticketend, place)) {
            out.print("添加成功"+"ticketflag"+ticketflag+"ticketnum"+ticketnum);
        } else {
            out.println("添加失败"+"flase"+org_id+" "+type+" "+dep+" "+name+" "+level+" "+begindate+" "+enddate+" "+begintime+" "+endtime+" "+content+" "+actflag+" "+add+" "+ D_Picture+" "+D_OriginalFileName+D_SaveFileName+D_SavePathFileName+ticketflag+ticketnum+ticketend+place);
        }
    }
}
