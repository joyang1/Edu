/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.action;

import com.javabean.admin.GetAdmin;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 小小一
 */
public class ManaAdmin extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
                    PrintWriter out = response.getWriter();
        GetAdmin gn = new GetAdmin();
        String admin_id = "1";
        String admin_name = new String(request.getParameter("admin_name").getBytes("iso-8859-1"), "utf-8");
        String admin_password = new String(request.getParameter("admin_password").getBytes("iso-8859-1"), "utf-8");
        String admin_level = new String(request.getParameter("admin_level").getBytes("iso-8859-1"), "utf-8");
        String admin_dep = new String(request.getParameter("admin_dep").getBytes("iso-8859-1"), "utf-8");
        String admin_ip = new String(request.getParameter("admin_ip").getBytes("iso-8859-1"), "utf-8");
        String admin_pic = "0";
        String admin_mail = "0";
        String admin_tel = new String(request.getParameter("admin_tel").getBytes("iso-8859-1"), "utf-8");
        String admin_cellphone = "1";
        String admin_right = "0";
        String admin_flag = "1";
        String remark = "0";
        String bak_extend1 = "0";
        String bak_extend2 = "0";
        String bak_extend3 = "0";
        if (gn.addadmin(admin_id ,admin_name ,admin_password , admin_level ,admin_dep ,admin_ip ,admin_pic ,admin_mail ,admin_tel ,admin_cellphone ,admin_right ,admin_flag ,remark ,bak_extend1 ,bak_extend2 ,bak_extend3)) {
            out.print("添加成功"+"name"+admin_name);
        } else {
            out.println("添加失败"+admin_id+admin_name+admin_password+admin_level+admin_dep+admin_ip+admin_pic+admin_mail+admin_tel+admin_cellphone+admin_right+admin_flag+remark+bak_extend1+bak_extend2+bak_extend3);
            out.println(gn.addadmin(admin_id ,admin_name ,admin_password , admin_level ,admin_dep ,admin_ip ,admin_pic ,admin_mail ,admin_tel ,admin_cellphone ,admin_right ,admin_flag ,remark ,bak_extend1 ,bak_extend2 ,bak_extend3));
        }
    }
   
}
