/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author slv
 */
public class SqlHelper {

    private Connection c = null;
    private PreparedStatement ps = null;
    private Statement st = null;
    private ResultSet rs = null;
    private DB cd = null;

    public ArrayList getArrayList(String sql, int id) {
        ArrayList al = new ArrayList();
        cd = new DB();
        c = cd.getConn();
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();  //查询数据库并返回结果记录集合
            ResultSetMetaData rsma = rs.getMetaData();
            int culomn = rsma.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= culomn; i++) {
                    al.add(rs.getString(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
    }

    public ArrayList getArrayLists(String sql, int id) {
        ArrayList al = new ArrayList();
        cd = new DB();
        c = cd.getConn();
        int id1 = 0;
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();  //查询数据库并返回结果记录集合
            ResultSetMetaData rsma = rs.getMetaData();
            int culomn = rsma.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= culomn; i++) {
                    al.add(rs.getString(i));
                    if (i == 7) {
                        id1 = Integer.parseInt(rs.getString(i));
                    }
                }
            }
            String sql1 = "select * from b_user where user_s_no = ?";
            try {
                ps = c.prepareStatement(sql1);
                ps.setInt(1, id1);
                rs = ps.executeQuery();  //查询数据库并返回结果记录集合
                ResultSetMetaData rsma1 = rs.getMetaData();
                int culomn1 = rsma1.getColumnCount();
                while (rs.next()) {
                    for (int i = 1; i <= culomn1; i++) {
                        al.add(rs.getString(i));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
    }

    public ResultSet getRs(String sql, int id) {
//        String Id = "";
        cd = new DB();
        c = cd.getConn();
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("操作失败!");
            e.printStackTrace(System.err);
        }
        return rs;
    }

    public ResultSet getRs(String sql) {
//        String Id = "";
        cd = new DB();
        c = cd.getConn();
        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("操作失败!");
            e.printStackTrace(System.err);
        }
        return rs;
    }

    /*插入数据方法*/
    public boolean Inserter(String sql) {
        boolean flag = true;
        cd = new DB();
        c = cd.getConn();
        try {
            ps = c.prepareStatement(sql);
            int a = ps.executeUpdate();
            /*executeUpdate返回值为更新数据库记录的个数*/
            if (a != 0) {
                flag = true;
            }
        } catch (SQLException e) {
            System.out.println("操作失败!");
            e.printStackTrace(System.err);
        } finally {
            this.close();
        }
        return flag;
    }

    /*inserter语句，update语句和delete语句公用方法，返回受影响的行数*/
    public int SqlQuery(String sql) {
        int a = 0;
        boolean flag = false;
        cd = new DB();
        c = cd.getConn();
        try {
            ps = c.prepareStatement(sql);
            a = ps.executeUpdate();
            /*a表示受影响的行数*/
        } catch (SQLException e) {
            flag = false;
            System.out.println("操作失败! 错误代码：UD0001");
            e.printStackTrace(System.err);
        } finally {
            this.close();
        }
        return a;
    }

    /*满足条件的的行数*/
    public int SqlCmd(String sql) {
        int total = 0;
        boolean flag = false;
        cd = new DB();
        c = cd.getConn();
        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.last();
            total = rs.getRow();
            /*a表示受影响的行数*/
        } catch (SQLException e) {
            flag = false;
            System.out.println("操作失败! 错误代码：UD0001");
            e.printStackTrace(System.err);
        } finally {
            this.close();
        }
        return total;
    }

    /**
     * 关闭数据库的连接
     *
     */
    public void close() {
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
        if (c != null) {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
    }

    /**
     *
     * 事物处理
     *
     * @param sql1
     * @param sql2
     * @return
     * @throws java.sql.SQLException
     */
    public int delete(String sql1, String sql2) throws SQLException {
        int a = 0;
        int b = 0;
        cd = new DB();
        c = cd.getConn();
        try {
            c.setAutoCommit(false);// 更改JDBC事务的默认提交方式 
            ps = c.prepareStatement(sql1);
            a = ps.executeUpdate();
            ps = c.prepareStatement(sql2);
            b = ps.executeUpdate();
            c.commit();//提交JDBC事务 
            c.setAutoCommit(true);// 恢复JDBC事务的默认提交方式 
            c.close();
            if (a != 0 && b != 0) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            c.rollback();//回滚JDBC事务 
            ex.printStackTrace(System.err);
            c.close();
            return -1;
        }
    }

    

}
