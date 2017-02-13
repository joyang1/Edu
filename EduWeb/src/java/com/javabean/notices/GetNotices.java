package com.javabean.notices;
import com.dao.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *notice表的相关操作
 * 
 * @author slv
 */
public class GetNotices {

    private Connection c = null;
    private PreparedStatement ps = null;
    private Statement st = null;
    private ResultSet rs = null;
    private DB cd = null;

    //获取新闻的条数
    public int getAllNoticesCount() {
        int allnewscount = 0;
        cd = new DB();
        c = cd.getConn();
        String sql = "select * from o_notice";
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
        String sql = "select * from o_notice";
        try {
            st = c.createStatement();
            rs = st.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getInt("notice_id") == id) {
                    title = rs.getString("notice_title");
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
        int[] id = new int[this.getAllNoticesCount()];
        cd = new DB();
        c = cd.getConn();
        String sql = "select * from o_notice";
        try {
            st = c.createStatement();
            rs = st.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                id[i] = rs.getInt("notice_id");
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
        String sql = "select * from o_notice";
        try {
            st = c.createStatement();
            rs = st.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getInt("notice_id") == id) {
                    content = rs.getString("notice_content");
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
        String sql = "select * from o_notice";
        try {
            st = c.createStatement();
            rs = st.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getInt("notice_id") == id) {
                    time = rs.getString("notice_date");
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
    public boolean addnotices(String title, String add, String dep, String content,String indate, String data, String time, String click, String D_Picture, String D_OriginalFileName, String D_SaveFileName, String D_SavePathFileName, String article_flag) {
        boolean flag = false;
        cd = new DB();
        c = cd.getConn();
        String sql = "insert into o_notice values(" + null + ",'" + title + "','" + add + "','" + dep + "','" + content + "','" + indate + "','" + data + "','" + time + "','" + click + "','" + D_Picture + "','" + D_OriginalFileName + "','" + D_SaveFileName + "','" + D_SavePathFileName + "','" + article_flag + "')";
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

    //删除指定id的新闻记录
    public boolean delNotices(int id) {
        boolean flag = false;
        String a = "0";
        cd = new DB();
        c = cd.getConn();
        String sql = "update o_notice set notice_flag = '0' where notice_id = '"+id+"'";
        sql = "select * from o_notice";
        try {
            st = c.createStatement();
            rs = st.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getInt(id)==id) { 
                    if (a.equals(rs.getString("notice_flag"))) {
                        flag = true;
                    }
                } 
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            this.close();
        }

        return flag;
    }
//        public boolean delNotices(int id) {
//        boolean flag = false;
//        int a = 1;
//        cd = new DB();
//        c = cd.getConn();
//        String sql = "delete from o_notice where notice_id = '"+id+"'";
//        try {
//            st = c.createStatement();
//            rs = st.executeQuery(sql);
//            rs.beforeFirst();
//            if (a == 1) { 
//                    flag = true;
//                } 
//        } catch (SQLException e) {
//            e.printStackTrace(System.err);
//        } finally {
//            this.close();
//        }
//
//        return flag;
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

