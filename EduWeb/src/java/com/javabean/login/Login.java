package com.javabean.login;


import com.dao.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author slv
 */
public class Login {

    private Connection c = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private DB cd = null;

    //添加管理员到数据库
    public boolean addadmin(String name, String password) {
        boolean flag = false;
        cd = new DB();
        c = cd.getConn();
        String sql = "insert into B_admin values(" + null + ",'" + null + "','" + name + "','" + password + "','" + null + "','" + null + "','" + null + "','" + null + "','" + null + "','" + null + "','" + null + "','" + null + "','" + null + "','" + null + "','" + null + "','" + null + "','" + null + "')";
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

    //判断登陆的用户是否管理员
    public boolean checkadmin(String admin_id, String admin_pass) {
        boolean flag = false;
        cd = new DB();
        c = cd.getConn();
        String sql = "select admin_password from B_admin where admin_id=?";
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, admin_id);
            rs = ps.executeQuery();

            if (rs.next()) {
                String dbpassword = rs.getString(1);
                if (dbpassword.equals(admin_pass)) {
                    flag = true;
                }
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
