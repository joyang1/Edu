/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.javabean.admin;

import com.dao.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author 小小一
 */
public class GetAdmin {
    
    private Connection c = null;
    private PreparedStatement ps = null;
    private Statement st = null;
    private ResultSet rs = null;
    private DB cd = null;
    
    
    public boolean addadmin(String admin_id, String admin_name, String admin_password, String admin_level, String admin_dep, String admin_ip, String admin_pic, String admin_mail, String admin_tel, String admin_cellphone, String admin_right, String admin_flag, String remark, String bak_extend1, String bak_extend2, String bak_extend3) {
        boolean flag = false;
        cd = new DB();
        c = cd.getConn();
        String sql = "insert into b_admin values(" + null + ",'" + admin_id + "','" + admin_name + "','" + admin_password + "','" + admin_level + "','" + admin_dep + "','" + admin_ip + "','" + admin_pic + "','" + admin_mail + "','" + admin_tel + "','" + admin_cellphone + "','" + admin_right + "','" + admin_flag + "','" + remark + "','" + bak_extend1 + "','" + bak_extend2 + "','" + bak_extend3 + "')";
        try {
            ps = c.prepareStatement(sql);
            int a = ps.executeUpdate();
            if (a != 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            this.close();
        }
        return flag;
    }
    
        //关闭数据库链接的方法
    private void close() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
        if (c != null) {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
    }
}
