package com.javabean.news;

import com.dao.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * news表的相关操作
 *
 * @author slv
 */
public class GetNews {

    private Connection c = null;
    private PreparedStatement ps = null;
    private Statement st = null;
    private ResultSet rs = null;
    private DB cd = null;

    //获取新闻的条数
    public int getAllNewsCount() {
        int allnewscount = 0;
        cd = new DB();
        c = cd.getConn();
        String sql = "select * from o_article";
        try {
            st = c.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                allnewscount++;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            this.close();
        }
        return allnewscount;
    }

    //获取指定id的新闻的题目
    public String getTitle(int id) {
        String title = "";
        cd = new DB();
        c = cd.getConn();
        String sql = "select * from o_article";
        try {
            st = c.createStatement();
            rs = st.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getInt("article_id") == id) {
                    title = rs.getString("article_title");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            this.close();
        }

        return title;
    }

    //获取所有新闻的id
    public int[] getId() {
        int[] id = new int[this.getAllNewsCount()];
        cd = new DB();
        c = cd.getConn();
        String sql = "select * from o_article";
        try {
            st = c.createStatement();
            rs = st.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                id[i] = rs.getInt("article_id");
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            this.close();
        }

        return id;

    }

    //获取指定id的新闻的内容
    public String getContent(int id) {
        String content = "";
        cd = new DB();
        c = cd.getConn();
        String sql = "select * from o_article";
        try {
            st = c.createStatement();
            rs = st.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getInt("article_id") == id) {
                    content = rs.getString("article_content");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            this.close();
        }

        return content;
    }

    //获取指定id的新闻的时间
    public String getTime(int id) {
        String time = "";
        cd = new DB();
        c = cd.getConn();
        String sql = "select * from o_article";
        try {
            st = c.createStatement();
            rs = st.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getInt("article_id") == id) {
                    time = rs.getString("article_date");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            this.close();
        }

        return time;
    }

    //管理员添加新闻时调用的方法
    public boolean addnews(String title, String add, String dep, String content, String data, String time, String click, String D_Picture, String D_OriginalFileName, String D_SaveFileName, String D_SavePathFileName, String article_flag) {
        boolean flag = false;
        cd = new DB();
        c = cd.getConn();
        String sql = "insert into o_article values(" + null + ",'" + title + "','" + add + "','" + dep + "','" + content + "','" + data + "','" + time + "','" + click + "','" + D_Picture + "','" + D_OriginalFileName + "','" + D_SaveFileName + "','" + D_SavePathFileName + "','" + article_flag + "')";
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

//    public ArrayList getAl(String sql, String id) {
//        ArrayList al = new ArrayList();
//        cd = new DB();
//        c = cd.getConn();
//        try {
//            ps = c.prepareStatement(sql);
//            rs = ps.executeQuery();  //查询数据库并返回结果记录集合
//            ResultSetMetaData rsma = rs.getMetaData();
//            int culomn = rsma.getColumnCount();
//            while (rs.next()) {
//                for (int i = 1; i <= culomn; i++) {
//                    al.add(rs.getString(i));
//                }
//            }
//            String sql1 = "select user_dep from b_user where user_id = ?";
//            try {
//                ps = c.prepareStatement(sql1);
//                ps.setString(1, id);
//                rs = ps.executeQuery();  //查询数据库并返回结果记录集合
//                while (rs.next()) {
//                    String sql2 = "select article_title from o_article where article_dep = '"+rs.getInt("user_dep")+"' order by article_id desc limit 0,3";
//                    ps = c.prepareStatement(sql2);
//                    rs = ps.executeQuery();  //查询数据库并返回结果记录集合
//                    ResultSetMetaData rsma2 = rs.getMetaData();
//                    int culomn2 = rsma2.getColumnCount();
//                    for (int i = 1; i <= culomn2; i++) {
//                        al.add(rs.getString(i));
//                    }
//
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return al;
//    }

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
