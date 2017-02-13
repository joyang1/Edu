package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {

    private Connection con;

    private PreparedStatement pstm;

    private String user = "root";

    private String password = "yt19940124";

    //private String password = "123456";
    String dbName = "edu";

    private String className = "com.mysql.jdbc.Driver";

    private String url = "jdbc:mysql://localhost:3306/" + dbName;

    public DB() {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            System.out.println("加载数据库驱动失败！");
            e.printStackTrace(System.err);
        }
    }

    /**
     * 创建数据库连接
     * @return  Connection
     */
    public Connection getConn() {
        try {
            if (con == null) {
                con = DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException e) {
            System.out.println("创建数据库连接失败！");
            con = null;
            e.printStackTrace(System.err);
        }
        return con;
    }

    public void doPstm(String sql, Object[] params) {
        if (sql != null && !sql.equals("")) {
            if (params == null) {
                params = new Object[0];
            }

            getConn();
            if (con != null) {
                try {
                    System.out.println(sql);
                    pstm = con.prepareStatement(sql,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
                    for (int i = 0; i < params.length; i++) {
                        pstm.setObject(i + 1, params[i]);
                    }
                    pstm.execute();
                } catch (SQLException e) {
                    System.out.println("doPstm()方法出错！");
                    e.printStackTrace();
                }
            }
        }
    }

    public ResultSet getRs() throws SQLException {
        return pstm.getResultSet();
    }

    public int getCount() throws SQLException {
        return pstm.getUpdateCount();
    }

    public void closed() {
        try {
            if (pstm != null) {
                pstm.close();
            }
        } catch (SQLException e) {
            System.out.println("关闭pstm对象失败！");
            e.printStackTrace();
        }
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("关闭con对象失败！");
            e.printStackTrace();
        }
    }
}
